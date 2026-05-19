package com.Ornexa.filter;

import java.io.IOException;

import com.Ornexa.model.User;
import com.Ornexa.utils.SessionUtils;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;

@WebFilter(urlPatterns = {
	    "/UserManagementServlet",
	    "/Product",
	    "/StockManagement",
	    "/OrderManagement",
	    "/AdminDashboard"
	})
public class AdminAuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String contextPath = req.getContextPath();

        User user = (User) SessionUtils.getAttribute(req, "user");

        // 1. Not logged in
        if (user == null) {
            res.sendRedirect(contextPath + "/loginServlet");
            return;
        }

        // 2. Not admin
        if (!"admin".equalsIgnoreCase(user.getRole())) {
            res.sendRedirect(contextPath + "/Home_pageServlet");
            return;
        }

        // 3. Allow admin
        chain.doFilter(request, response);
    }
}