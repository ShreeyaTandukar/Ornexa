
package com.Ornexa.service;

import java.util.List;

import com.Ornexa.dao.CartDao;
import com.Ornexa.dao.ProductDao;
import com.Ornexa.model.CartItem;
import com.Ornexa.model.Product;

public class CartService {
	CartDao cartDao = new CartDao();
	
	ProductDao productDao = new ProductDao();

	public int getOrCreateCart(int userId) {
		try {
			return cartDao.getOrCreateCart(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public List<CartItem> getCartItems(int cartId) {
		try {
			return cartDao.getCartItems(cartId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void addItem(int cartId, String productName, double price) {
		try {
			cartDao.addItem(cartId, productName, price);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateQuantity(int cartItemId, int newQty, double priceAtTime) {
		try {
			if (newQty <= 0) {
				cartDao.removeItem(cartItemId);
			} else {
				cartDao.updateQuantity(cartItemId, newQty, priceAtTime);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void removeItem(int cartItemId) {
		try {
			cartDao.removeItem(cartItemId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void closeCart(int cartId, int userId) {
		try {
			cartDao.closeCart(cartId,userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public double getGrandTotal(List<CartItem> items) {
		if (items == null) {
			return 0;
		}
		double total = 0;
		for (CartItem item : items) {
			total += item.getSubTotal();
		}
		return total;
	}

	public Product getProductById(int productId) {
	    try {
	        return productDao.getProductById(productId);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
}