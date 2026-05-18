package com.Ornexa.controller;

import java.io.IOException;

import com.Ornexa.model.Order;
import com.Ornexa.service.OrderManagementService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Invoice")
public class InvoiceServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private OrderManagementService service = new OrderManagementService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int orderId = Integer.parseInt(request.getParameter("orderId"));

            Order order = service.getOrderWithItems(orderId);

            if (order == null) {
                response.sendRedirect("adminOrderL.jsp");
                return;
            }

            // 3. SEND DATA TO JSP
            request.setAttribute("order", order);
            request.setAttribute("items", order.getItems());

            // 4. FORWARD TO INVOICE PAGE
            request.getRequestDispatcher("/WEB-INF/pages/Invoice.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("/WEB-INF/pages/OrderManagement.jsp")
            .forward(request, response);
        }
    }
}