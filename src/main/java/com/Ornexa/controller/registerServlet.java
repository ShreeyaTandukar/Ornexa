package com.Ornexa.controller;
import java.io.File;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

import org.mindrot.jbcrypt.BCrypt;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;

import com.Ornexa.model.User;
import com.Ornexa.service.RegisterService;
import com.Ornexa.utils.ImageUtils;
import com.Ornexa.utils.ValidationUtils;

/**
 * Servlet implementation class registerServlet
 */
@WebServlet("/registerServlet")
@MultipartConfig
public class registerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/register.jsp")
		.forward(request, response);
		}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException{
		System.out.println("DO POST CALLED");

		String firstName = request.getParameter("firstName");
	    String lastName = request.getParameter("lastName");
	    String username = request.getParameter("username");
	    String email = request.getParameter("email");
	    String phoneNum = request.getParameter("phoneNumber");
	    String dobStr1 = request.getParameter("dob");
	    String password = request.getParameter("password");
	    String validating=ValidationUtils.validationRegister(firstName, lastName, username, email, phoneNum, dobStr1, password);
	    if(validating !=null && !validating.trim().isEmpty()) {
	    	request.setAttribute("validating", validating);
	    	request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
	    	return;
	    }
	
		
		User customer = new User();
		try {
			Part imagePart = request.getPart("image");
			ImageUtils imagee = new ImageUtils();
			String imageName = imagee.getImageNameFromPart(imagePart);
			boolean Uploaded= imagee.uploadImage(imagePart,getServletContext().getRealPath(""),"uploads");
			if (!Uploaded) {
				System.out.println("Image not uploaded");
			}
			customer.setFirstName(request.getParameter("firstName"));
			customer.setLastName(request.getParameter("lastName"));
			customer.setUserName(request.getParameter("username"));
			customer.setEmail(request.getParameter("email"));
			customer.setPhoneNumber(request.getParameter("phoneNumber"));
			String dobStr = request.getParameter("dob");
			Date dob = Date.valueOf(dobStr);
			customer.setDob(dob);			
			customer.setPassword(request.getParameter("password"));
			customer.setImage(imageName);
			Date joinat = new Date(System.currentTimeMillis());
			
			RegisterService service = new RegisterService();
			boolean successful =service.addCustomer(customer);
			if (successful) {
				response.sendRedirect(request.getContextPath() + "/loginServlet");
			}else {
				request.setAttribute("validating", "User already exists!");
				request.getRequestDispatcher("/WEB-INF/pages/register.jsp")
						.forward(request, response);
			}
		} catch (Exception e) {
			    e.printStackTrace();
			    request.setAttribute("error", "Something went wrong");
			    request.getRequestDispatcher("/WEB-INF/pages/register.jsp")
			           .forward(request, response);
			    return;
		}
	}
}

