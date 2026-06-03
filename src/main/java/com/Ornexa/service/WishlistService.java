package com.Ornexa.service;

import java.util.List;
import com.Ornexa.dao.WishlistDAO;
import com.Ornexa.model.WishlistItem;

public class WishlistService {
    private WishlistDAO wishlistDAO = new WishlistDAO();

    // Get all wishlist items for a user
    public List<WishlistItem> getWishlistItems(int userId) {
        return wishlistDAO.getWishlistItems(userId);
    }

    // Add item to wishlist
    public void addItem(int userId, int productId) {
        wishlistDAO.addItem(userId, productId);
    }

    // Remove item from wishlist
    public void removeItem(int userId, int productId) {
        wishlistDAO.removeItem(userId, productId);
    }
}
