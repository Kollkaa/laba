package com.julia.laba.dao;

import com.julia.laba.config.DBConnectionPool;
import com.julia.laba.data.Group;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GroupDAO implements DAO<Group, Long> {
    private DBConnectionPool connectionPool;

    public GroupDAO() {
        try {
            this.connectionPool = DBConnectionPool.getInstance();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Group save(Group group) {
        Connection connection = connectionPool.getConnection();
        try {
            String query = String.format("insert into %s (%s)value (?)", Group.DB_NAME, Group.Columns.NAME);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, group.getGroupName());
            statement.executeUpdate();
            statement.close();
            return group;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    private Group map(ResultSet resultSet) throws SQLException {
        if (resultSet == null) {
            return null;
        }
        Group group = new Group();
        group.setId(resultSet.getLong(Group.Columns.ID));
        group.setGroupName(resultSet.getString(Group.Columns.NAME));
        System.out.println(group.getGroupName());
        return group;
    }

    @Override
    public Optional<Group> findById(Long aLong) {
        Connection connection = connectionPool.getConnection();
        try {
            String query = String.format(DAOUtils.FIND_BY_ID_QUERY, Group.DB_NAME);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, aLong);
            ResultSet res = statement.executeQuery();
            res.next();
            Group group = map(res);
            statement.close();
            return Optional.ofNullable(group);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public List<Group> findAll() {
        List<Group> groups = new ArrayList<>();
        Connection connection = connectionPool.getConnection();
        try {
            String query = String.format(DAOUtils.FIND_ALL_QUERY, Group.DB_NAME);
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                groups.add(map(res));
            }
            statement.close();
            System.out.println(groups);
            return groups;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }
}
