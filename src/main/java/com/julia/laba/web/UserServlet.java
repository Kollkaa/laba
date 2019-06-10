package com.julia.laba.web;

import com.julia.laba.config.BeanFactory;
import com.julia.laba.exception.ValidationException;
import com.julia.laba.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {
    private UserService service = (UserService) BeanFactory.getBean(UserService.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", service.findUsers());
        req.getRequestDispatcher("user.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("user_name");
        if (!ValidationUtils.validateString(userName)) {
            throw new ValidationException("User name is not valid");
        } else {
            service.createUser(userName, null);
            req.setAttribute("users", service.findUsers());
            req.getRequestDispatcher("user.jsp").forward(req, resp);
        }
    }


}
