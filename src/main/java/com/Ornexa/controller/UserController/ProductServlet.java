package com.Ornexa.controller.UserController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.Ornexa.model.Product;
import com.Ornexa.service.ProductService;
import com.Ornexa.service.WishlistService;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/ProductServlet" })
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductService productService = new ProductService();
	WishlistService wishlistService = new WishlistService();
       
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
		        String searchQuery = request.getParameter("searchQuery");
		        
		        List<Product> allProducts;

		        // Check if ANY criteria are present
		        boolean searching = (searchQuery != null && !searchQuery.trim().isEmpty());
		        boolean filtering = (Material != null && !Material.isEmpty()) || 
		                           (style != null && !style.isEmpty()) || 
		                           (gender != null && !gender.isEmpty());

		        if (searching) {
		            // If user searched for something specifically
		            allProducts = productService.searchProductsByName(searchQuery);
		        } else if (filtering) {
		            // If user used the sidebar filters
		            allProducts = productService.getFilteredProducts(Material, style, gender);
		        } else {
		            // No search or filter, show everything
		            allProducts = productService.getAllProducts();
		        }
		        if (allProducts == null) {
		            allProducts = new ArrayList<>();
		        }

		        int size = allProducts.size();

		        List<Product> rightProducts = new ArrayList<>();
		        List<Product> bottomProducts = new ArrayList<>();

		        int rightLimit = Math.min(6, size);

		        for (int i = 0; i < rightLimit; i++) {
		            rightProducts.add(allProducts.get(i));
		        }

		        for (int i = rightLimit; i < size; i++) {
		            bottomProducts.add(allProducts.get(i));
		        }
				HttpSession session = request.getSession(false);
				Integer userId = null;

				if (session != null) {
				    userId = (Integer) session.getAttribute("userId");
				}

				List<Integer> wishlistProductIds = new ArrayList<>();

				if (userId != null) {
				    wishlistProductIds = wishlistService.getWishlistProductIds(userId);
				}

				request.setAttribute("wishlistProductIds", wishlistProductIds);

			 request.setAttribute("rightProducts", rightProducts);
			 request.setAttribute("bottomProducts", bottomProducts);


	          
	            request.getRequestDispatcher("/WEB-INF/pages/Users/product.jsp")
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
