package com.julia.laba.web;

import com.julia.laba.config.BeanFactory;
import com.julia.laba.exception.ValidationException;
import com.julia.laba.service.GroupService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GroupServlet extends HttpServlet {
    private GroupService service = (GroupService) BeanFactory.getBean(GroupService.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("groups", service.findAllgroups());
        req.getRequestDispatcher("group.jsp").forward(req, resp);
    }


    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("group_name");
        if (!ValidationUtils.validateString(name)) {
            throw new ValidationException("Group name is not valid");

        } else {
            service.createGroup(name);
            req.setAttribute("groups", service.findAllgroups());
            req.getRequestDispatcher("group.jsp").forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
