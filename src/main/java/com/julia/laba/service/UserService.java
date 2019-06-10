package com.julia.laba.service;

import com.julia.laba.dao.UserDAO;
import com.julia.laba.data.User;
import com.julia.laba.exception.ValidationException;

import java.util.List;

public class UserService {
    private UserDAO dao;

    public UserService(UserDAO dao) {
        this.dao = dao;
    }

    public void createUser(String name, String pasword) {
        User user = new User();
        user.setUserName(name);
        user.setPassword(pasword);
        dao.save(user);
    }

    public User loginOrRegister(String name, String pwd) {
        User user = dao.findByName(name);
        if (user == null) {
            createUser(name, pwd);
            return dao.findByName(name);
        } else {
            if (pwd.isEmpty() || !pwd.equals(user.getPassword())) {
                throw new ValidationException("User password is not valid");
            }
            return user;
        }
    }

    public List<User> findUsers() {
        return dao.findAll();
    }
}
