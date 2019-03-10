package com.alex.laba.web;

import com.alex.laba.service.TourService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TourServlet extends HttpServlet {
    private TourService service = TourService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("tours", service.findAll());
        req.getRequestDispatcher("tour.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("tour_name");
        String description = req.getParameter("tour_description");
        String sAgency = req.getParameter("tour_agency");
        String sCost = req.getParameter("tour_cost");


        if (!ValidationUtils.validateString(name)) {
            req.setAttribute("field", "User Name");
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        } else if (!ValidationUtils.validateString(description)) {
            req.setAttribute("field", "Description");
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        } else if (!ValidationUtils.validateInt(sAgency)) {
            req.setAttribute("field", "Agency");
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        } else if (!ValidationUtils.validateInt(sCost)) {
            req.setAttribute("field", "Cost");
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        } else {
            Long agency = Long.parseLong(sAgency);
            Long cost = Long.parseLong(sCost);
            service.createTour(description, name, agency, cost);
            req.setAttribute("tours", service.findAll());
            req.getRequestDispatcher("tour.jsp").forward(req, resp);
        }
    }
}
