package com.alex.laba.dao;

import com.alex.laba.config.DBConfig;
import com.alex.laba.data.Agency;
import com.alex.laba.data.Agent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AgentDAO implements DAO<Agent, Long> {

    private Connection connection;
    private static AgentDAO instance = new AgentDAO();

    private AgentDAO() {
        connection = DBConfig.getInstance().getConnection();
    }

    public static AgentDAO getInstance() {
        return instance;
    }

    @Override
    public Agent save(Agent agent) {
        try {
            String query = String.format("insert into %s (%s, %s) value (?, ?)", Agent.DB_NAME, Agent.Columns.NAME, Agent.Columns.AGENCY_ID);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, agent.getAgentName());
            statement.setLong(2, agent.getAgencyId());
            statement.executeUpdate();
            statement.close();
            return agent;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<Agent> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public List<Agent> findAll() {
        List<Agent> agents = new ArrayList<>();
        try {
            String query = String.format(DAOUtils.FIND_ALL_QUERY, Agent.DB_NAME);
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                agents.add(map(res));
            }
            statement.close();
            return agents;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    private Agent map(ResultSet resultSet) throws SQLException {
        if (resultSet == null) {
            return null;
        }
        Agent agency = new Agent();
        agency.setId(resultSet.getLong(Agent.Columns.ID));
        agency.setAgentName(resultSet.getString(Agent.Columns.NAME));
        agency.setAgencyId(resultSet.getLong(Agent.Columns.AGENCY_ID));
        return agency;
    }

}
