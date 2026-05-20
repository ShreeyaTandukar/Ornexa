package com.Ornexa.service;

import java.util.List;
import com.Ornexa.dao.WishlistDao;
import com.Ornexa.model.WishlistItem;

public class WishlistService {

    private WishlistDao wishlistDAO = new WishlistDao();

    public List<WishlistItem> getWishlistItems(int userId) {
        return wishlistDAO.getWishlistItems(userId);
    }

    public void addItem(int userId, int productId) {
        wishlistDAO.addItem(userId, productId);
    }

    public void removeItem(int userId, int productId) {
        wishlistDAO.removeItem(userId, productId);
    }
    public List<Integer> getWishlistProductIds(int userId) {
        return wishlistDAO.getWishlistProductIds(userId);
    }
}