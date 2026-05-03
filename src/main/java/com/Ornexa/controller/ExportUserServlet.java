package com.Ornexa.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.Ornexa.dao.UserDao;
import com.Ornexa.model.User;

/**
 * Servlet implementation class ExportUserServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/ExportUserServlet" })
public class ExportUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExportUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        UserDao dao = new UserDao();
        try {
            List<User> users = dao.getAllUsers();

            response.setContentType("text/csv");
            
            String fileName = "User_Directory_" + System.currentTimeMillis() + ".csv";
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

            PrintWriter writer = response.getWriter();
            
            // Header Row
            writer.println("User ID,Username,Email,Role,Status");

            // Data Rows
            for (User u : users) {
                writer.println(
                    u.getId() + "," +
                    u.getUserName() + "," +
                    u.getEmail() + "," +
                    u.getRole() + "," +
                    u.getStatus()
                );
            }
            
            writer.flush();
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
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
