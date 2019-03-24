package com.alex.laba.web;

import com.alex.laba.dao.UserDAO;
import com.alex.laba.exception.ValidationException;
import com.alex.laba.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {
    private UserDAO dao = new UserDAO();
    private UserService service = new UserService(dao);

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
            service.createUser(userName);
            req.setAttribute("users", service.findUsers());
            req.getRequestDispatcher("user.jsp").forward(req, resp);
        }
    }


}
