package com.julia.laba.web;

import com.julia.laba.config.BeanFactory;
import com.julia.laba.exception.ValidationException;
import com.julia.laba.service.SubjectService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SubjectServlet extends HttpServlet {
    private SubjectService service = (SubjectService) BeanFactory.getBean(SubjectService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("subjects", service.findAll());
        req.getRequestDispatcher("subject.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("subject_name");
        String description = req.getParameter("subject_description");
        String sGroup = req.getParameter("subject_group");
        String sCredits = req.getParameter("subject_credits");


        if (!ValidationUtils.validateString(name)) {
            throw new ValidationException("Subject name is not valid");
        } else if (!ValidationUtils.validateString(description)) {
            throw new ValidationException("Subject description is not valid");
        } else if (!ValidationUtils.validateInt(sGroup)) {
            throw new ValidationException("Group name is not valid");
        } else if (!ValidationUtils.validateInt(sCredits)) {
            throw new ValidationException("Credits are not valid");
        } else {
            Long group = Long.parseLong(sGroup);
            Long credits = Long.parseLong(sCredits);
            service.createSubject(description, name, group, credits);
            req.setAttribute("subjects", service.findAll());
            req.getRequestDispatcher("subject.jsp").forward(req, resp);
        }
    }
}
