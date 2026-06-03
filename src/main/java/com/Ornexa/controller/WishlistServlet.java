package com.Ornexa.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

import com.Ornexa.service.WishlistService;

@WebServlet("/WishListServlet")
public class WishlistServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private WishlistService wishlistService = new WishlistService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        // Check if user is logged in
        if (session == null || session.getAttribute("userId") == null) {
            resp.sendRedirect("login.jsp"); // redirect to login page
            return;
        }

        int userId = (Integer) session.getAttribute("userId");

        // Call service to fetch wishlist items
        req.setAttribute("wishlistItems", wishlistService.getWishlistItems(userId));

        // Forward to JSP
        req.getRequestDispatcher("/WEB-INF/pages/wishlist.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("userId") == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        int userId = (Integer) session.getAttribute("userId");
        int productId = Integer.parseInt(req.getParameter("productId"));
        String action = req.getParameter("action");

        // Call service methods
        if ("add".equals(action)) {
            wishlistService.addItem(userId, productId);
        } else if ("remove".equals(action)) {
            wishlistService.removeItem(userId, productId);
        }

        // Redirect back to servlet (refresh list)
        resp.sendRedirect("WishListServlet");
    }
}
