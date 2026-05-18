package com.Ornexa.controller;

import java.io.IOException;
import java.util.List;

import com.Ornexa.model.Order;
import com.Ornexa.service.OrderManagementService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/OrderManagement")
public class OrderManagement extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	OrderManagementService service = new OrderManagementService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String status = request.getParameter("status");
        String fromDate = request.getParameter("fromDate");
        String toDate = request.getParameter("toDate");

        List<Order> orders;

        if ((status != null && !status.isEmpty()) ||
            (fromDate != null && !fromDate.isEmpty()) ||
            (toDate != null && !toDate.isEmpty())) {

            orders = service.getFilteredOrders(status, fromDate, toDate);

        } else {
            orders = service.getAllOrders();
        }

        double revenue = service.getTotalRevenue();
        double growth = service.getGrowthPercent();

        request.setAttribute("orders", orders);
        request.setAttribute("revenue", revenue);
        request.setAttribute("growth", growth);

        request.getRequestDispatcher("/WEB-INF/pages/OrderManagement.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("updateStatus".equals(action)) {

            int orderId = Integer.parseInt(request.getParameter("orderId"));
            String status = request.getParameter("orderStatus");

            System.out.println("ORDER ID: " + orderId);
            System.out.println("STATUS: " + status);

            boolean result = service.updateOrderStatus(orderId, status);

            System.out.println("UPDATE RESULT: " + result);
        }


        response.sendRedirect(request.getContextPath()+"OrderManagement");
    }
}