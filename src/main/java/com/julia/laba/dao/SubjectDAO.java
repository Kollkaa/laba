package com.julia.laba.dao;

import com.julia.laba.config.DBConnectionPool;
import com.julia.laba.data.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SubjectDAO implements DAO<Subject, Long> {
    private DBConnectionPool connectionPool;

    public SubjectDAO() {
        try {
            this.connectionPool = DBConnectionPool.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Subject save(Subject subject) {
        Connection connection = connectionPool.getConnection();
        try {
            String query = String.format("insert into %s (%s, %s, %s, %s) value (?, ?, ?, ?)", Subject.DB_NAME, Subject.Columns.NAME, Subject.Columns.DESCRIPTION, Subject.Columns.GROUP_ID, Subject.Columns.CREDITS);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, subject.getName());
            statement.setString(2, subject.getDescription());
            statement.setLong(3, subject.getGroup());
            statement.setLong(4, subject.getCredits());
            statement.executeUpdate();
            statement.close();
            return subject;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Subject> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public List<Subject> findAll() {
        List<Subject> subjects = new ArrayList<>();
        Connection connection = connectionPool.getConnection();
        try {
            String query = String.format(DAOUtils.FIND_ALL_QUERY, Subject.DB_NAME);
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                subjects.add(map(res));
            }
            statement.close();
            return subjects;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    private Subject map(ResultSet resultSet) throws SQLException {
        if (resultSet == null) {
            return null;
        }
        Subject subject = new Subject();
        subject.setId(resultSet.getLong(Subject.Columns.ID));
        subject.setGroup(resultSet.getLong(Subject.Columns.GROUP_ID));
        subject.setCredits(resultSet.getLong(Subject.Columns.CREDITS));
        subject.setName(resultSet.getString(Subject.Columns.NAME));
        subject.setDescription(resultSet.getString(Subject.Columns.DESCRIPTION));
        return subject;
    }
}
