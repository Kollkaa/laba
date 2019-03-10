package com.alex.laba.service;

import com.alex.laba.dao.OrderDAO;
import com.alex.laba.data.Order;

import java.util.List;


public class OrderService {
    private final OrderDAO dao = OrderDAO.getInstance();
    private final static OrderService instance = new OrderService();

    public static OrderService getInstance() {
        return instance;
    }

    public void createOrder(Long userId, Long agentId, Long tourId, Long cost) {
        Order order = new Order();
        order.setCost(cost);
        order.setUserId(userId);
        order.setAgentId(agentId);
        order.setTourId(tourId);
        dao.save(order);
    }

    public List<Order> findAll() {
        return dao.findAll();
    }
}
