package com.Ornexa.controller;

import java.io.IOException;

import com.Ornexa.model.Product;
import com.Ornexa.service.ProductService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DescriptionServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/DescriptionServlet" })
public class DescriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DescriptionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductService productService = new ProductService();

		try {
            int id = Integer.parseInt(request.getParameter("id"));

            Product product = productService.getProductById(id);

            request.setAttribute("product", product);

            request.getRequestDispatcher("/WEB-INF/pages/description.jsp")
                   .forward(request, response);

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
