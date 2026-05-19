package com.Ornexa.controller;

import java.io.IOException;

import com.Ornexa.dao.UserDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class updateUserStatus
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/updateUserStatus" })
public class updateUserStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateUserStatus() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username"); 
        String newStatus = request.getParameter("status");

        UserDao dao = new UserDao();
        try {
            dao.updateStatus(username, newStatus);
            // Redirect back to the user list to see changes
            response.sendRedirect("UserManagementServlet");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
