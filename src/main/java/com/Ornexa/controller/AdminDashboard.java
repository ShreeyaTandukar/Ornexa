package com.Ornexa.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.Ornexa.model.Order;
import com.Ornexa.service.RegisterService;
import com.Ornexa.service.OrderService;

/**
 * Servlet implementation class AdminDashboard
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/AdminDashboard" })
public class AdminDashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDashboard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		OrderService service = new OrderService();
		
		 RegisterService registerService = new RegisterService();

		    // ORDER DATA
		    int totalOrders = service.getTotalOrders();
		    List<Order> orders = service.getOrders();

		    // USER DATA
		    int newUsers = registerService.getNewUsers();

		    // SALES 
		    double totalSales = service.getTotalSales();

		    request.setAttribute("totalOrders", totalOrders);
		    request.setAttribute("totalSales", totalSales);
		    request.setAttribute("newUsers", newUsers);
		    request.setAttribute("orders", orders);

		    request.getRequestDispatcher("/WEB-INF/pages/adminDashboard.jsp")
		           .forward(request, response);
		}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
