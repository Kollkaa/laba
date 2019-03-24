package com.alex.laba.service;

import com.alex.laba.dao.AgentDAO;
import com.alex.laba.data.Agent;

import java.util.List;

public class AgentService {
    private final AgentDAO agentDAO;

    public AgentService(AgentDAO agentDAO) {
        this.agentDAO = agentDAO;
    }

    public Agent createAgent(String name, Long agencyId) {
        Agent agent = new Agent();

        agent.setAgencyId(agencyId);
        agent.setAgentName(name);
        agentDAO.save(agent);
        return agent;
    }

    public List<Agent> findAllAgents() {
        return agentDAO.findAll();
    }
}
