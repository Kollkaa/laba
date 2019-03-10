package com.alex.laba.dao;

import com.alex.laba.config.DBConfig;
import com.alex.laba.data.Tour;
import com.alex.laba.data.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAO implements DAO<User, Long> {

    private Connection connection;
    private static UserDAO instance = new UserDAO();

    public UserDAO() {
        this.connection = DBConfig.getInstance().getConnection();
    }

    public static UserDAO getInstance() {
        return instance;
    }

    @Override
    public User save(User user) {
        try {
            String query = String.format("insert into %s (%s) value (?)", User.DB_NAME, User.Columns.USER_NAME);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getUserName());
            statement.executeUpdate();
            statement.close();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<User> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try {
            String query = String.format(DAOUtils.FIND_ALL_QUERY, User.DB_NAME);
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                users.add(map(res));
            }
            statement.close();
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    private User map(ResultSet resultSet) throws SQLException {
        if (resultSet == null) {
            return null;
        }
        User user = new User();
        user.setId(resultSet.getLong(User.Columns.ID));
        user.setUserName(resultSet.getString(User.Columns.USER_NAME));
        return user;
    }
}
