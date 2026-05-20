package com.Ornexa.controller.UserController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.Ornexa.model.User;
import com.Ornexa.service.LoginService;
import com.Ornexa.utils.CookieUtil;
import com.Ornexa.utils.SessionUtils;

@WebServlet(asyncSupported = true, urlPatterns = { "/loginServlet" })
public class loginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LoginService service = new LoginService();

    public loginServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/Users/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            User user = service.login(username, password);

            if (user == null) {
                request.setAttribute("errors", "Invalid credentials");
                request.getRequestDispatcher("/WEB-INF/pages/Users/login.jsp").forward(request, response);
                return; 
            }
           
            if (!"admin".equalsIgnoreCase(user.getRole())) {
                if (!"APPROVED".equalsIgnoreCase(user.getStatus())) {
                    request.setAttribute("errors", "Account not approved yet. Please contact the administrator.");
                    request.getRequestDispatcher("/WEB-INF/pages/Users/login.jsp").forward(request, response);
                    return;
                }
            }

            SessionUtils.setAttribute(request, "user", user);
          
            CookieUtil.addCookie(response, "username", username, 60 * 60 * 24 * 7);
           
            if ("admin".equalsIgnoreCase(user.getRole())) {
              
                response.sendRedirect(request.getContextPath() + "/UserManagementServlet");
            } else {
                response.sendRedirect(request.getContextPath() + "/Home_pageServlet");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Error during login process", e);
        }
    }
}