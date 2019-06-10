package com.julia.laba.web;

import com.julia.laba.config.BeanFactory;
import com.julia.laba.exception.ValidationException;
import com.julia.laba.service.TestService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TestServlet extends HttpServlet {
    private TestService service = (TestService) BeanFactory.getBean(TestService.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("tests", service.findAll());
        req.getRequestDispatcher("test.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String sUserId = req.getParameter("user_id");
        String sStudentId = req.getParameter("student_id");
        String sSubjectId = req.getParameter("subject_id");
        String sMark = req.getParameter("mark");

        if (!ValidationUtils.validateInt(sUserId)) {
            throw new ValidationException("User id is not valid");
        } else if (!ValidationUtils.validateInt(sStudentId)) {
            throw new ValidationException("Student id is not valid");
        } else if (!ValidationUtils.validateInt(sSubjectId)) {
            throw new ValidationException("Subject id is not valid");
        } else if (!ValidationUtils.validateInt(sMark)) {
            throw new ValidationException("Mark id is not valid");
        } else {
            Long userId = Long.parseLong(sUserId);
            Long studentId = Long.parseLong(sStudentId);
            Long subjectId = Long.parseLong(sSubjectId);
            Long mark = Long.parseLong(sMark);

            service.createTest(userId, studentId, subjectId, mark);

            req.setAttribute("tests", service.findAll());
            req.getRequestDispatcher("test.jsp").forward(req, resp);
        }
    }
}
