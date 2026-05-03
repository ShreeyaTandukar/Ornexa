package com.Ornexa.controller;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import com.Ornexa.model.User;
import com.Ornexa.service.RegisterService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;




/**
 * Servlet implementation class ProfileServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/Profile_image" })
public class Profile_image extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RegisterService service = new RegisterService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profile_image() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String userName = request.getParameter("userName");

            User user = service.getUserByUsername(userName);

            if (user == null || user.getImage() == null) {
                return;
            }

            // file path in server
            String imagePath = getServletContext().getRealPath("/uploads/" + user.getImage());

            File imageFile = new File(imagePath);

            if (!imageFile.exists()) {
                return;
            }

            response.setContentType("image/jpeg");

            java.io.FileInputStream fis = new java.io.FileInputStream(imageFile);
            java.io.OutputStream os = response.getOutputStream();

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }

            fis.close();
            os.close();

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
