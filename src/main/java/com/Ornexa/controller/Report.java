package com.Ornexa.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.Ornexa.service.ListUserService;

import com.Ornexa.model.Order;
import com.Ornexa.model.User;
import com.Ornexa.service.OrderManagementService;

@WebServlet("/Report")
public class Report extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        OrderManagementService orderService = new OrderManagementService();

        // total order
        List<Order> orders = orderService.getAllOrders();

        // total revenue
        double totalSales = orderService.getTotalRevenue();
        int totalOrders = orders.size();
        
        //get user which are approved
        ListUserService userService = new ListUserService();
        List<User> users = null;

        try {
            users = userService.getAllUsers();
        } catch (Exception e) {
            e.printStackTrace();
            users = new ArrayList<>(); // fallback to avoid crash
        }
        
        int totalUsers = users.size();

        int pendingUsers = 0;
        for (User u : users) {
            if ("PENDING".equalsIgnoreCase(u.getStatus())) {
                pendingUsers++;
            }
        }

        int activeUsers = totalUsers - pendingUsers;

        
        //pending order( orders which are not delivered or cancelled
        int pendingOrders = 0;


        for (Order o : orders) {
            String status = o.getOrderStatus();

            if (!"DELIVERED".equalsIgnoreCase(status)
                    && !"CANCELED".equalsIgnoreCase(status)) {
                pendingOrders++;
            }
        }

        // SET ATTRIBUTES
        request.setAttribute("totalUsers", totalUsers);
        request.setAttribute("pendingUsers", pendingUsers);
        request.setAttribute("activeUsers", activeUsers);
        request.setAttribute("orders", orders);
        request.setAttribute("totalSales", totalSales);
        request.setAttribute("totalOrders", totalOrders);
        request.setAttribute("pendingOrders", pendingOrders);

        request.getRequestDispatcher("/WEB-INF/pages/Report.jsp")
               .forward(request, response);
    }
}