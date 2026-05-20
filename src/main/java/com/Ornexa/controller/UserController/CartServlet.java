package com.Ornexa.controller.UserController;

import java.io.IOException;
import java.util.List;

import com.Ornexa.model.CartItem;
import com.Ornexa.model.User;
import com.Ornexa.service.CartService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(asyncSupported = true, urlPatterns = { "/CartServlet" })
public class CartServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	CartService cartService = new CartService();

	public CartServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// first check if user is logged in
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("user") == null) {
			response.sendRedirect(request.getContextPath() + "/loginServlet");
			return;
		}

		// get the logged in user and their cart
		User user = (User) session.getAttribute("user");
		int userId = user.getId();
		int cartId = cartService.getOrCreateCart(userId);

		// get all cart items and calculate total
		List<CartItem> cartItems = cartService.getCartItems(cartId);
		double grandTotal = cartService.getGrandTotal(cartItems);

		// send everything to the cart page
		request.setAttribute("cartItems", cartItems);
		request.setAttribute("cartTotal", grandTotal);
		request.setAttribute("cartId", cartId);
		request.getRequestDispatcher("/WEB-INF/pages/Users/cart.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// check login first
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("user") == null) {
			response.sendRedirect(request.getContextPath() + "/loginServlet");
			return;
		}

		User user = (User) session.getAttribute("user");
		int userId = user.getId();
		String action = request.getParameter("action");

		if (action == null) {
			response.sendRedirect(request.getContextPath() + "/CartServlet");
			return;
		}

		// add a new item to cart
		if (action.equals("add")) {
			String productName = request.getParameter("productName");
			double price = Double.parseDouble(request.getParameter("price"));
			int cartId = cartService.getOrCreateCart(userId);
			cartService.addItem(cartId, productName, price);

		// update quantity when user clicks + or -
		} else if (action.equals("update")) {
			int cartItemId = Integer.parseInt(request.getParameter("cartItemId"));
			int newQty = Integer.parseInt(request.getParameter("quantity"));
			double price = Double.parseDouble(request.getParameter("priceAtTime"));
			cartService.updateQuantity(cartItemId, newQty, price);

		// remove item when user clicks x button
		} else if (action.equals("remove")) {
			int cartItemId = Integer.parseInt(request.getParameter("cartItemId"));
			cartService.removeItem(cartItemId);

		// user clicked checkout - build selected items and go to payment
		} else if (action.equals("checkout")) {
			String[] selectedIds = request.getParameterValues("selectedItems");
			int cartId = Integer.parseInt(request.getParameter("cartId"));

			// if nothing selected just go back to cart
			if (selectedIds == null || selectedIds.length == 0) {
				response.sendRedirect(request.getContextPath() + "/CartServlet");
				return;
			}

			List<CartItem> allItems = cartService.getCartItems(cartId);

			// build pipe separated strings of only the selected item details
			StringBuilder names = new StringBuilder();
			StringBuilder qtys = new StringBuilder();
			StringBuilder prices = new StringBuilder();
			StringBuilder imgs = new StringBuilder();
			double total = 0;

			for (CartItem item : allItems) {
				for (String id : selectedIds) {
					if (String.valueOf(item.getCartItemId()).equals(id)) {
						if (names.length() > 0) {
							names.append("|");
							qtys.append("|");
							prices.append("|");
							imgs.append("|");
						}
						names.append(item.getItemName());
						qtys.append(item.getProductQuantity());
						prices.append(item.getPriceAtTime());
						imgs.append(item.getImgUrl());
						total += item.getSubTotal();
						break;
					}
				}
			}

			// redirect to payment page with all selected item info in URL
			String redirect = request.getContextPath() + "/payment"
					+ "?source=cart"
					+ "&cartId=" + cartId
					+ "&cartTotal=" + total
					+ "&itemNames=" + java.net.URLEncoder.encode(names.toString(), "UTF-8")
					+ "&itemQtys=" + java.net.URLEncoder.encode(qtys.toString(), "UTF-8")
					+ "&itemPrices=" + java.net.URLEncoder.encode(prices.toString(), "UTF-8")
					+ "&itemImgs=" + java.net.URLEncoder.encode(imgs.toString(), "UTF-8");

			response.sendRedirect(redirect);
			return;
		}

		// after add, update or remove just refresh the cart page
		response.sendRedirect(request.getContextPath() + "/CartServlet");
	}
}