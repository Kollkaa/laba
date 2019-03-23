package com.alex.laba.service;

import com.alex.laba.dao.UserDAO;
import com.alex.laba.data.User;

import java.util.List;

public class UserService {
    private UserDAO dao;

    public UserService(UserDAO dao) {
        this.dao = dao;
    }

    public void createUser(String name) {
        User user = new User();
        user.setUserName(name);
        dao.save(user);
    }

    public List<User> findUsers() {
        return dao.findAll();
    }
}
