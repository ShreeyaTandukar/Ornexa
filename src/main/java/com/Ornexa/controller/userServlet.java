package com.Ornexa.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.Ornexa.dao.CartDao;
import com.Ornexa.dao.OrderDao;
import com.Ornexa.model.CartItem;
import com.Ornexa.model.Order;
import com.Ornexa.model.User;

@WebServlet("/userServlet")
public class userServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	OrderDao orderDao = new OrderDao();
	CartDao cartDao = new CartDao();

	public userServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// check login first
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("user") == null) {
			response.sendRedirect(request.getContextPath() + "/loginServlet");
			return;
		}

		// get user from session
		User user = (User) session.getAttribute("user");
		int userId = user.getId();

		// fetch orders for this user
		List<Order> orders = orderDao.getOrdersByUserId(userId);
		request.setAttribute("orders", orders);
		request.setAttribute("user", user);

		// get cart item count for the cart stat card
		try {
			int cartId = cartDao.getOrCreateCart(userId);
			List<CartItem> cartItems = cartDao.getCartItems(cartId);
			request.setAttribute("cartCount", cartItems.size());
		} catch (Exception e) {
			request.setAttribute("cartCount", 0);
			e.printStackTrace();
		}

		request.getRequestDispatcher("/WEB-INF/pages/user.jsp")
				.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}