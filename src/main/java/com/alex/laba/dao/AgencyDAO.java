package com.alex.laba.dao;

import com.alex.laba.config.DBConfig;
import com.alex.laba.data.Agency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AgencyDAO implements DAO<Agency, Long> {
    private Connection connection;
    private static AgencyDAO instance = new AgencyDAO();

    private AgencyDAO() {
        this.connection = DBConfig.getInstance().getConnection();
    }

    public static AgencyDAO getInstance() {
        return instance;
    }

    @Override
    public Agency save(Agency agency) {
        try {
            String query = String.format("insert into %s (%s)value (?)", Agency.DB_NAME, Agency.Columns.NAME);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, agency.getAgencyName());
            statement.executeUpdate();
            statement.close();
            return agency;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    private Agency map(ResultSet resultSet) throws SQLException {
        if (resultSet == null) {
            return null;
        }
        Agency agency = new Agency();
        agency.setId(resultSet.getLong(Agency.Columns.ID));
        agency.setAgencyName(resultSet.getString(Agency.Columns.NAME));
        System.out.println(agency.getAgencyName());
        return agency;
    }

    @Override
    public Optional<Agency> findById(Long aLong) {
        try {
            String query = String.format(DAOUtils.FIND_BY_ID_QUERY, Agency.DB_NAME);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, aLong);
            ResultSet res = statement.executeQuery();
            res.next();
            Agency agency = map(res);
            statement.close();
            return Optional.ofNullable(agency);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Agency> findAll() {
        List<Agency> agencies = new ArrayList<>();
        try {
            String query = String.format(DAOUtils.FIND_ALL_QUERY, Agency.DB_NAME);
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                agencies.add(map(res));
            }
            statement.close();
            System.out.println(agencies);
            return agencies;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}
