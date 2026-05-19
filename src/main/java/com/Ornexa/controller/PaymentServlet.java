package com.Ornexa.controller;

import java.io.IOException;

import com.Ornexa.model.User;
import com.Ornexa.service.PaymentService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class PaymentServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/payment" })
public class PaymentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	PaymentService paymentService = new PaymentService();

	public PaymentServlet() {
		super();
	}

	// GET — show payment page
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("user") == null) {
			response.sendRedirect(request.getContextPath() + "/loginServlet");
			return;
		}

		request.getRequestDispatcher("/WEB-INF/pages/payment.jsp")
				.forward(request, response);
	}

	// POST — process payment
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
			// Buy Now — single product
			String productName = request.getParameter("productName");
			double price = Double.parseDouble(request.getParameter("price"));
			int quantity = Integer.parseInt(request.getParameter("quantity"));

			success = paymentService.processBuyNowPayment(
					userId, productName, price, quantity, paymentMethod, destination);

		} else {
			// Cart checkout — selected items only
			int    cartId = Integer.parseInt(request.getParameter("cartId"));

			String itemNamesStr = request.getParameter("itemNames");
			String itemQtysStr = request.getParameter("itemQtys");
			String itemPricesStr = request.getParameter("itemPrices");

			String[] itemNames = (itemNamesStr  != null && !itemNamesStr.isEmpty())
								  ? itemNamesStr.split("\\|")  : new String[0];
			String[] itemQtys = (itemQtysStr   != null && !itemQtysStr.isEmpty())
								  ? itemQtysStr.split("\\|")   : new String[0];
			String[] itemPrices = (itemPricesStr != null && !itemPricesStr.isEmpty())
								  ? itemPricesStr.split("\\|") : new String[0];

			success = paymentService.processCartPayment(
					userId, cartId, paymentMethod, destination,
					itemNames, itemQtys, itemPrices);
		}

		if (success) {
			// PRG pattern — redirect so back button never re-submits
			response.sendRedirect(request.getContextPath() + "/payment?status=success");
		} else {
			response.sendRedirect(request.getContextPath() + "/payment?status=failed");
		}
	}
}