package com.Ornexa.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.Ornexa.model.Product;
import com.Ornexa.service.ProductService;

@WebServlet(asyncSupported = true, urlPatterns = { "/Home_pageServlet" })
public class Home_pageServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public Home_pageServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ProductService productService = new ProductService();

        try {
            List<Product> collectionProducts = productService.getCollectionProducts();
            request.setAttribute("collectionProducts", collectionProducts);
            request.getRequestDispatcher("/WEB-INF/pages/index.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Failed to load home page products", e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}