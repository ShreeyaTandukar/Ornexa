package com.Ornexa.controller.UserController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.Ornexa.model.User;
import com.Ornexa.model.WishlistItem;
import com.Ornexa.service.WishlistService;

@WebServlet("/wishlist")
public class wishlist extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private WishlistService wishlistService = new WishlistService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);

     
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/loginServlet");
            return;
        }

  
        User user = (User) session.getAttribute("user");
        int userId = user.getId();

       
        List<WishlistItem> wishlistItems = wishlistService.getWishlistItems(userId);

        req.setAttribute("wishlistItems", wishlistItems);

       
        req.getRequestDispatcher("/WEB-INF/pages/Users/wishlist.jsp")
                .forward(req, resp);
        System.out.println("WISHLIST USER ID: " + userId);
    }
    

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);

       
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/loginServlet");
            return;
        }

        User user = (User) session.getAttribute("user");
        int userId = user.getId();

        String action = req.getParameter("action");
        String productIdStr = req.getParameter("productId");

        
        if (productIdStr == null || productIdStr.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/wishlist");
            return;
        }

        int productId = Integer.parseInt(productIdStr);

        
        if ("add".equalsIgnoreCase(action)) {
            wishlistService.addItem(userId, productId);
        } 
        else if ("remove".equalsIgnoreCase(action)) {
            wishlistService.removeItem(userId, productId);
        }

        // refresh page
        resp.sendRedirect(req.getContextPath() + "/wishlist");
    }
}