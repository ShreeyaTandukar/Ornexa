package com.Ornexa.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

import com.Ornexa.model.User;
import com.Ornexa.service.PaymentService;

@WebServlet(asyncSupported = true, urlPatterns = { "/payment" })
public class PaymentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	PaymentService paymentService = new PaymentService();

	public PaymentServlet() {
		super();
	}

	// show payment page — all array splitting and calculation done here
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("user") == null) {
			response.sendRedirect(request.getContextPath() + "/loginServlet");
			return;
		}

		// split pipe separated item arrays from cart checkout
		String itemNamesStr = request.getParameter("itemNames");
		String itemQtysStr = request.getParameter("itemQtys");
		String itemPricesStr = request.getParameter("itemPrices");
		String itemImgsStr = request.getParameter("itemImgs");

		String[] itemNames = (itemNamesStr != null && !itemNamesStr.isEmpty())
							  ? itemNamesStr.split("\\|") : new String[0];
		String[] itemQtys = (itemQtysStr != null && !itemQtysStr.isEmpty())
							  ? itemQtysStr.split("\\|") : new String[0];
		String[] itemPrices = (itemPricesStr != null && !itemPricesStr.isEmpty())
							  ? itemPricesStr.split("\\|") : new String[0];
		String[] itemImgs = (itemImgsStr != null && !itemImgsStr.isEmpty())
							  ? itemImgsStr.split("\\|") : new String[0];

		// calculate subtotal and total
		double subtotal = 0;
		String productName = request.getParameter("productName");
		String priceParam  = request.getParameter("price");
		String qtyParam = request.getParameter("quantity");

		if (productName != null && !productName.isEmpty()) {
			// coming from buy now
			double price = (priceParam != null && !priceParam.isEmpty())
						   ? Double.parseDouble(priceParam) : 0;
			int qty = (qtyParam != null && !qtyParam.isEmpty())
						   ? Integer.parseInt(qtyParam) : 1;
			subtotal = price * qty;
		} else {
			// coming from cart checkout
			String cartTotalParam = request.getParameter("cartTotal");
			subtotal = (cartTotalParam != null && !cartTotalParam.isEmpty())
					   ? Double.parseDouble(cartTotalParam) : 0;
		}

		double shipping = 100;
		double total = subtotal + shipping;

		// set everything as request attributes so JSP can use JSTL only
		request.setAttribute("itemNames",itemNames);
		request.setAttribute("itemQtys",itemQtys);
		request.setAttribute("itemPrices", itemPrices);
		request.setAttribute("itemImgs",itemImgs);
		request.setAttribute("subtotal",subtotal);
		request.setAttribute("total",total);

		request.getRequestDispatcher("/WEB-INF/pages/payment.jsp")
				.forward(request, response);
	}

	// process payment when user clicks confirm
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("user") == null) {
			response.sendRedirect(request.getContextPath() + "/loginServlet");
			return;
		}

		User user = (User) session.getAttribute("user");
		int userId = user.getId();
		String paymentMethod = request.getParameter("paymentMethod");
		String destination = request.getParameter("destination");
		String source = request.getParameter("source");

		boolean success = false;

		if (source != null && source.equals("buynow")) {
			// coming from buy now button on description page
			String productName = request.getParameter("productName");
			double price = Double.parseDouble(request.getParameter("price"));
			int quantity = Integer.parseInt(request.getParameter("quantity"));

			success = paymentService.processBuyNowPayment(
					userId, productName, price, quantity, paymentMethod, destination);

		} else {
			// coming from cart checkout
			int cartId = Integer.parseInt(request.getParameter("cartId"));

			String itemNamesStr = request.getParameter("itemNames");
			String itemQtysStr = request.getParameter("itemQtys");
			String itemPricesStr = request.getParameter("itemPrices");

			String[] itemNames = (itemNamesStr != null && !itemNamesStr.isEmpty())
								  ? itemNamesStr.split("\\|") : new String[0];
			String[] itemQtys = (itemQtysStr != null && !itemQtysStr.isEmpty())
								  ? itemQtysStr.split("\\|") : new String[0];
			String[] itemPrices = (itemPricesStr != null && !itemPricesStr.isEmpty())
								  ? itemPricesStr.split("\\|") : new String[0];

			success = paymentService.processCartPayment(
					userId, cartId, paymentMethod, destination,
					itemNames, itemQtys, itemPrices);
		}

		// redirect so back button never re-submits the order
		if (success) {
			response.sendRedirect(request.getContextPath() + "/payment?status=success");
		} else {
			response.sendRedirect(request.getContextPath() + "/payment?status=failed");
		}
	}
}