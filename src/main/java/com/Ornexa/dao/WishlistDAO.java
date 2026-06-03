package com.Ornexa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.Ornexa.model.WishlistItem;
import com.Ornexa.utils.DBconfig;

public class WishlistDAO {

    // Get all wishlist items for a user
    public List<WishlistItem> getWishlistItems(int userId) {
        List<WishlistItem> items = new ArrayList<>();
        try (Connection conn = DBconfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                 "SELECT * FROM wishlist_items WHERE user_id = ?")) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                WishlistItem item = new WishlistItem();
                item.setId(rs.getInt("id"));
                item.setUserId(rs.getInt("user_id"));
                item.setProductId(rs.getInt("product_id"));
                items.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    // Add item to wishlist
    public void addItem(int userId, int productId) {
        try (Connection conn = DBconfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                 "INSERT INTO wishlist_items(user_id, product_id) VALUES (?, ?)")) {
            ps.setInt(1, userId);
            ps.setInt(2, productId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Remove item from wishlist
    public void removeItem(int userId, int productId) {
        try (Connection conn = DBconfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                 "DELETE FROM wishlist_items WHERE user_id = ? AND product_id = ?")) {
            ps.setInt(1, userId);
            ps.setInt(2, productId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
