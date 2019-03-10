package com.alex.laba.service;

import com.alex.laba.dao.UserDAO;
import com.alex.laba.data.User;

import java.util.List;

public class UserService {
    private UserDAO dao = UserDAO.getInstance();
    private static final UserService instance = new UserService();

    public static UserService getInstance() {
        return instance;
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
