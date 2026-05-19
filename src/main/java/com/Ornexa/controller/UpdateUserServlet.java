package com.Ornexa.controller;

import java.io.File;
import java.io.IOException;

import com.Ornexa.model.User;
import com.Ornexa.service.RegisterService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/UpdateUserServlet")
@MultipartConfig
public class UpdateUserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        int userId = Integer.parseInt(request.getParameter("userId"));

        String userName = request.getParameter("userName");
        String phone_num = request.getParameter("phoneNumber");

        Part imagePart = request.getPart("image");

        String fileName = null;

      
        if (imagePart != null && imagePart.getSize() > 0) {

            fileName = System.currentTimeMillis() + "_" +
                       imagePart.getSubmittedFileName();

            String uploadPath = getServletContext().getRealPath("") + "uploads";

            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdir();

            imagePart.write(uploadPath + File.separator + fileName);
        }

 
        User user = new User();
        user.setId(userId);   
        user.setUserName(userName);
        user.setPhoneNumber(phone_num);

       
        if (fileName != null) {
            user.setImage("uploads/" + fileName);
        } else {
            user.setImage(request.getParameter("oldImage"));
        }

   
        RegisterService service = new RegisterService();
        service.updateUser(user);
        request.getSession().setAttribute("user", user); 

        response.sendRedirect("userServlet");
    }
}
