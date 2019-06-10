package com.julia.laba.dao;

import com.julia.laba.config.DBConnectionPool;
import com.julia.laba.data.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentDAO implements DAO<Student, Long> {

    private DBConnectionPool connectionPool;

    public StudentDAO() {
        try {
            connectionPool = DBConnectionPool.getInstance();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Student save(Student student) {
        Connection connection = connectionPool.getConnection();
        try {
            String query = String.format("insert into %s (%s, %s) value (?, ?)", Student.DB_NAME, Student.Columns.NAME, Student.Columns.AGENCY_ID);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, student.getStudentName());
            statement.setLong(2, student.getGroupId());
            statement.executeUpdate();
            statement.close();
            return student;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Student> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        Connection connection = connectionPool.getConnection();
        try {
            String query = String.format(DAOUtils.FIND_ALL_QUERY, Student.DB_NAME);
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                students.add(map(res));
            }
            statement.close();
            return students;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    private Student map(ResultSet resultSet) throws SQLException {
        if (resultSet == null) {
            return null;
        }
        Student agency = new Student();
        agency.setId(resultSet.getLong(Student.Columns.ID));
        agency.setStudentName(resultSet.getString(Student.Columns.NAME));
        agency.setGroupId(resultSet.getLong(Student.Columns.AGENCY_ID));
        return agency;
    }

}
