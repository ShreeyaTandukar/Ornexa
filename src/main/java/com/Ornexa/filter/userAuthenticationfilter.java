package com.Ornexa.filter;

import java.io.IOException;

import com.Ornexa.model.User;
import com.Ornexa.utils.SessionUtils;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = {
	    "/userServlet",
	    "/wishlist",
	    "/CartServlet"
	})
	public class userAuthenticationfilter implements Filter {

	    @Override
	    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	            throws IOException, ServletException {

	        HttpServletRequest req = (HttpServletRequest) request;
	        HttpServletResponse res = (HttpServletResponse) response;

	        String contextPath = req.getContextPath();

	        User user = (User) SessionUtils.getAttribute(req, "user");

	        // Not logged in
	        if (user == null) {
	            res.sendRedirect(contextPath + "/loginServlet");
	            return;
	        }

	        // Admin trying to access user pages
	        if ("admin".equalsIgnoreCase(user.getRole())) {
	            res.sendRedirect(contextPath + "/AdminDashboard");
	            return;
	        }

	        // Normal user allowed
	        chain.doFilter(request, response);
	    }
	}