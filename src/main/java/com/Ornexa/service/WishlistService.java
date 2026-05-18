package com.Ornexa.service;

import java.util.List;
import com.Ornexa.dao.WishlistDao;
import com.Ornexa.model.WishlistItem;

public class WishlistService {
    private WishlistDAO WishlistDao = new WishlistDAO();

    // Get all wishlist items for a user
    public List<WishlistItem> getWishlistItems(int userId) {
        return WishlistDao.getWishlistItems(userId);
    }

    // Add item to wishlist
    public void addItem(int userId, int productId) {
        WishlistDao.addItem(userId, productId);
    }

    // Remove item from wishlist
    public void removeItem(int userId, int productId) {
        WishlistDao.removeItem(userId, productId);
    }
}