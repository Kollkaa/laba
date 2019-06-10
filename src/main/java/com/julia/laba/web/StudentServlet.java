package com.julia.laba.web;

import com.julia.laba.config.BeanFactory;
import com.julia.laba.exception.ValidationException;
import com.julia.laba.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StudentServlet extends HttpServlet {
    private StudentService service = (StudentService) BeanFactory.getBean(StudentService.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("students", service.findAllStudents());
        req.getRequestDispatcher("student.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentName = req.getParameter("student_name");
        String sGroupId = req.getParameter("group_id");

        if (!ValidationUtils.validateInt(sGroupId)) {
            throw new ValidationException("Group id is not valid");

        } else if (!ValidationUtils.validateString(studentName)) {
            throw new ValidationException("Student name is not valid");
        } else {
            Long groupId = Long.parseLong(sGroupId);
            service.createStudent(studentName, groupId);
            req.setAttribute("students", service.findAllStudents());
            req.getRequestDispatcher("student.jsp").forward(req, resp);
        }
    }
}
