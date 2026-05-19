package com.Ornexa.controller;

import java.io.IOException;
import java.util.List;

import com.Ornexa.dao.UserDao;
import com.Ornexa.model.User;
import com.Ornexa.service.ListUserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserManagementServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/UserManagementServlet" })
public class UserManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserManagementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("UserManagementServlet called");
		UserDao dao = new UserDao();


		try {
			ListUserService listing = new ListUserService();
			List<User> listUser = listing.getAllUsers();
			int total = dao.getTotalUsers();
			int pending = dao.getPendingUsers();
			String status = request.getParameter("status");
			if (status == null || status.isEmpty()) {
	            status = "all"; 
			}
			if ("all".equals(status)) {
	            listUser = listing.getAllUsers();
	        } else {
	            
	            listUser = listing.getUsersByStatus(status); 
	        }
			request.setAttribute("totalCommunity", total);
			request.setAttribute("pendingApprovals", pending);
			request.setAttribute("listUser", listUser);
			request.setAttribute("currentFilter", status);
			request.getRequestDispatcher("/WEB-INF/pages/AdminUserManagement.jsp").forward(request, response);	
		
	} catch (Exception e) {
		e.printStackTrace();
		
		throw new ServletException ("Error while fetching users", e);
	}

		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

