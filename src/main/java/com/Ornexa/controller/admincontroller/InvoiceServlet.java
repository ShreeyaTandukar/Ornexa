package com.Ornexa.controller.admincontroller;

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

    private OrderManagementService service = new OrderManagementService(); //order object

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int orderId = Integer.parseInt(request.getParameter("orderId")); //get order id 

            Order order = service.getOrderWithItems(orderId); // get order item

            if (order == null) {
                response.sendRedirect("adminOrder.jsp");
                return;
            }

            request.setAttribute("order", order);
            request.setAttribute("items", order.getItems());

            request.getRequestDispatcher("/WEB-INF/pages/admin/Invoice.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("/WEB-INF/pages/admin/OrderManagement.jsp")
            .forward(request, response);
        }
    }
}