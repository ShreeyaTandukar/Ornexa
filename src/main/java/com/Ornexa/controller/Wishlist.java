//controller
package com.Ornexa.controller;

import java.io.IOException;

import com.Ornexa.service.WishlistService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/WishListServlet")
public class WishlistServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private WishlistService wishlistService = new WishlistService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Get userId from session
        int userId = (int) req.getSession().getAttribute("userId");

        // Call service to fetch wishlist items
        req.setAttribute("wishlistItems", wishlistService.getWishlistItems(userId));

        // Forward to JSP
        req.getRequestDispatcher("/WEB-INF/pages/wishlist.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int userId = (int) req.getSession().getAttribute("userId");
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