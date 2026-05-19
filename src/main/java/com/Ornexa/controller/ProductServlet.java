package com.Ornexa.controller;

import java.io.IOException;
import java.util.List;

import com.Ornexa.model.Product;
import com.Ornexa.service.ProductService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/ProductServlet" })
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductService productService = new ProductService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 try {
			

		        String Material = request.getParameter("material");
		        String style = request.getParameter("style");
		        String gender = request.getParameter("gender");
		        boolean isFiltered =
		                (Material != null && !Material.isEmpty()) ||
		                (style != null && !style.isEmpty()) ||
		                (gender != null && !gender.isEmpty());

			 List<Product> allProducts = productService.getAllProducts();
			 if (isFiltered) {
		            allProducts = productService.getFilteredProducts(Material, style, gender);
		        } else {
		            allProducts = productService.getAllProducts();
		        }

			 int size = allProducts.size();

			 List<Product> rightProducts = allProducts.subList(0, Math.min(6, size));

			 List<Product> bottomProducts = allProducts.subList(
			     Math.min(6, size),
			     Math.min(9, size)
			 );


			 request.setAttribute("rightProducts", rightProducts);
			 request.setAttribute("bottomProducts", bottomProducts);


	          
	            request.getRequestDispatcher("/WEB-INF/pages/product.jsp")
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
