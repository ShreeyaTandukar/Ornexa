package com.Ornexa.service;

import java.util.List;

import com.Ornexa.dao.CartDao;
import com.Ornexa.dao.PaymentDao;
import com.Ornexa.model.CartItem;

public class PaymentService {

	PaymentDao paymentDao = new PaymentDao();
	CartDao cartDao = new CartDao();

	
	public boolean processCartPayment(int userId, int cartId,
			String paymentMethod, String destination,
			String[] itemNames, String[] itemQtys, String[] itemPrices) {
		try {
			if (itemNames == null || itemNames.length == 0) {
				return false;
			}

			// it calculates total from selected items only
			double total = 0;
			for (int i = 0; i < itemNames.length; i++) {
				double price = Double.parseDouble(itemPrices[i]);
				int    qty   = Integer.parseInt(itemQtys[i]);
				total += price * qty;
			}
			total += 100; // shipping fee

			// inserting payment
			int paymentId = paymentDao.insertPayment(paymentMethod, total);

			// inserting order
			int orderId = paymentDao.insertOrder(userId, destination, total);

			// inserts each selected item into orderitem
			for (int i = 0; i < itemNames.length; i++) {
				int productId = paymentDao.getProductIdByName(itemNames[i]);
				int qty = Integer.parseInt(itemQtys[i]);
				double price = Double.parseDouble(itemPrices[i]);
				paymentDao.insertOrderItem(orderId, paymentId, productId, qty, price);
			}

			// it removes the ones which are selecting in the checkbox
			List<CartItem> allItems = cartDao.getCartItems(cartId);
			boolean anyRemaining = false;

			for (CartItem cartItem : allItems) {
				boolean isSelected = false;
				for (String selectedName : itemNames) {
					if (selectedName.equals(cartItem.getItemName())) {
						isSelected = true;
						break;
					}
				}
				if (isSelected) {
					cartDao.removeItem(cartItem.getCartItemId());
				} else {
					anyRemaining = true;
				}
			}

			// this close cart if all items were checked out otherwise the items remains the same
			if (!anyRemaining) {
				cartDao.closeCart(cartId, userId);
			}

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// buy now (description page)
	public boolean processBuyNowPayment(int userId, String productName,
			double price, int quantity, String paymentMethod, String destination) {
		try {
			double total = price * quantity + 100; // add shipping

			// inserts payment
			int paymentId = paymentDao.insertPayment(paymentMethod, total);

			// inserts order
			int orderId = paymentDao.insertOrder(userId, destination, total);

			// inserts single item into orderitem
			int productId = paymentDao.getProductIdByName(productName);
			paymentDao.insertOrderItem(orderId, paymentId, productId, quantity, price);

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}