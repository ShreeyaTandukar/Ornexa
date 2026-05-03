package com.Ornexa.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.Ornexa.dao.UserDao;
import com.Ornexa.model.User;
import com.Ornexa.service.LoginService;

/**
 * Servlet implementation class userServlet
 */
@WebServlet("/userServlet")
public class userServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
     * @see HttpServlet#HttpServlet()
     */
    public userServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    private UserDao service = new UserDao();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 request.getRequestDispatcher("/WEB-INF/pages/user.jsp")
         .forward(request, response);
		try {
	        String username = request.getParameter("userName");

	        User user = service.getUserByUsername(username);

	        request.setAttribute("user", user);

	        request.getRequestDispatcher("/WEB-INF/pages/user.jsp")
	               .forward(request, response);
	        HttpSession session = request.getSession(false);
			if(session == null || session.getAttribute("userName") == null) {
			response.sendRedirect(request.getContextPath() + "/loginServlet");
			return;
			}
		}
			catch (Exception e) {
	        e.printStackTrace();
	        throw new ServletException(e);
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
