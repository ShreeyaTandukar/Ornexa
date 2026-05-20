package com.Ornexa.controller.admincontroller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.Ornexa.model.Order;
import com.Ornexa.service.RegisterService;
import com.Ornexa.service.stockService;
import com.Ornexa.service.OrderManagementService;


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
		OrderManagementService service = new OrderManagementService();		
		 RegisterService registerService = new RegisterService();
		 stockService stockService = new stockService();

		 //cards 
		 //total order
		 List<Order> orders = service.getAllOrders();
		 int totalOrders = orders.size();

		 // total sales
		 double totalSales = service.getTotalRevenue();

		 	// new users
		 	int newUsers = registerService.getNewUsers();
		 	
		 	int lowStock =  stockService.lowStock();
		 	
		 	request.setAttribute("lowStockCount",lowStock );
		    request.setAttribute("totalOrders", totalOrders);
		    request.setAttribute("totalSales", totalSales);
		    request.setAttribute("newUsers", newUsers);
		    request.setAttribute("orders", orders);

		    request.getRequestDispatcher("/WEB-INF/pages/admin/adminDashboard.jsp")
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
