package com.alex.laba.dao;

import com.alex.laba.config.DBConfig;
import com.alex.laba.data.Tour;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TourDAO implements DAO<Tour, Long> {
    private Connection connection;

    public TourDAO() {
        this.connection = DBConfig.getInstance().getConnection();
    }


    @Override
    public Tour save(Tour tour) {
        try {
            String query = String.format("insert into %s (%s, %s, %s, %s) value (?, ?, ?, ?)", Tour.DB_NAME, Tour.Columns.NAME, Tour.Columns.DESCRIPTION, Tour.Columns.AGENCY_ID, Tour.Columns.COST);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, tour.getName());
            statement.setString(2, tour.getDescription());
            statement.setLong(3, tour.getAgency());
            statement.setLong(4, tour.getCost());
            statement.executeUpdate();
            statement.close();
            return tour;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<Tour> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public List<Tour> findAll() {
        List<Tour> tours = new ArrayList<>();
        try {
            String query = String.format(DAOUtils.FIND_ALL_QUERY, Tour.DB_NAME);
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                tours.add(map(res));
            }
            statement.close();
            return tours;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    private Tour map(ResultSet resultSet) throws SQLException {
        if (resultSet == null) {
            return null;
        }
        Tour tour = new Tour();
        tour.setId(resultSet.getLong(Tour.Columns.ID));
        tour.setAgency(resultSet.getLong(Tour.Columns.AGENCY_ID));
        tour.setCost(resultSet.getLong(Tour.Columns.COST));
        tour.setName(resultSet.getString(Tour.Columns.NAME));
        tour.setDescription(resultSet.getString(Tour.Columns.DESCRIPTION));
        return tour;
    }
}
