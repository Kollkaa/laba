package com.julia.laba.dao;

import com.julia.laba.config.DBConnectionPool;
import com.julia.laba.data.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestDAO implements DAO<Test, Long> {

    private DBConnectionPool connectionPool;

    public TestDAO() {
        try {
            this.connectionPool = DBConnectionPool.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Test save(Test test) {
        Connection connection = connectionPool.getConnection();
        try {
            String query = String.format("insert into %s (%s, %s, %s, %s) value (?, ?, ?, ?)", Test.DB_NAME, Test.Columns.USER_ID, Test.Columns.SUBJECT_ID, Test.Columns.STUDENT_ID, Test.Columns.MARK);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, test.getUserId());
            statement.setLong(2, test.getSubjectId());
            statement.setLong(3, test.getStudentId());
            statement.setLong(4, test.getMark());
            statement.executeUpdate();
            statement.close();
            return test;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Test> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public List<Test> findAll() {
        List<Test> tests = new ArrayList<>();
        Connection connection = connectionPool.getConnection();
        try {
            String query = String.format(DAOUtils.FIND_ALL_QUERY, Test.DB_NAME);
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                tests.add(map(res));
            }
            statement.close();
            return tests;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    private Test map(ResultSet resultSet) throws SQLException {
        if (resultSet == null) {
            return null;
        }
        Test test = new Test();
        test.setStudentId(resultSet.getLong(Test.Columns.STUDENT_ID));
        test.setUserId(resultSet.getLong(Test.Columns.USER_ID));
        test.setSubjectId(resultSet.getLong(Test.Columns.SUBJECT_ID));
        test.setId(resultSet.getLong(Test.Columns.ID));
        test.setMark(resultSet.getLong(Test.Columns.MARK));
        return test;
    }
}
