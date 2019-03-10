package com.alex.laba.web;

import com.alex.laba.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderServlet extends HttpServlet {
    private OrderService service = OrderService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("orders", service.findAll());
        req.getRequestDispatcher("order.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String sUserId = req.getParameter("user_id");
        String sAgentId = req.getParameter("agent_id");
        String sTourId = req.getParameter("tour_id");
        String sCost = req.getParameter("cost");

        if (!ValidationUtils.validateInt(sUserId)) {
            req.setAttribute("field", "User id");
            req.getRequestDispatcher("error.jsp").forward(req, resp);

        } else if (!ValidationUtils.validateInt(sAgentId)) {
            req.setAttribute("field", "Agency Id");
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        } else if (!ValidationUtils.validateInt(sTourId)) {
            req.setAttribute("field", "Tour id");
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        } else if (!ValidationUtils.validateInt(sCost)) {
            req.setAttribute("field", "Cost");
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        } else {
            Long userId = Long.parseLong(sUserId);
            Long agentId = Long.parseLong(sAgentId);
            Long tourId = Long.parseLong(sTourId);
            Long cost = Long.parseLong(sCost);

            service.createOrder(userId, agentId, tourId, cost);

            req.setAttribute("orders", service.findAll());
            req.getRequestDispatcher("order.jsp").forward(req, resp);
        }
    }
}
