package com.alex.laba.service;

import com.alex.laba.dao.AgencyDAO;
import com.alex.laba.data.Agency;

import java.util.List;

public class AgencyService {
    private AgencyDAO dao;
    private static final AgencyService instance = new AgencyService();

    private AgencyService() {
        this.dao = AgencyDAO.getInstance();
    }

    public static AgencyService getInstance() {
        return instance;
    }

    public Agency createAgency(String name) {
        Agency agency = new Agency();
        agency.setAgencyName(name);
        dao.save(agency);
        return agency;
    }

    public List<Agency> findAllAgencies() {
        return dao.findAll();
    }
}
