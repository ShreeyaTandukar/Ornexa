package com.Ornexa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.Ornexa.model.WishlistItem;
import com.Ornexa.utils.DBconfig;

public class WishlistDao {

	public List<WishlistItem> getWishlistItems(int userId) {

	    List<WishlistItem> items = new ArrayList<>();

	    String sql =
	    "SELECT w.id, w.user_id, w.product_id, " +
	    "p.Product_Name, p.Product_Price, p.img_url, p.Stock_Quantity " +
	    "FROM wishlist_items w " +
	    "JOIN product p ON w.product_id = p.Product_Id " +
	    "WHERE w.user_id = ?";

	    try (Connection conn = DBconfig.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setInt(1, userId);

	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {

	            WishlistItem item = new WishlistItem();

	            item.setId(rs.getInt("id"));
	            item.setUserId(rs.getInt("user_id"));
	            item.setProductId(rs.getInt("product_id"));

	            item.setName(rs.getString("Product_Name"));
	            item.setPrice(rs.getDouble("Product_Price"));
	            item.setImgUrl(rs.getString("img_url"));

	            int qty = rs.getInt("Stock_Quantity");

	            if (qty > 5) {
	                item.setStockStatus("IN STOCK");
	            } else if (qty > 0) {
	                item.setStockStatus("LOW STOCK");
	            } else {
	                item.setStockStatus("OUT OF STOCK");
	            }

	            items.add(item);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return items;
	}


    public void addItem(int userId, int productId) {

        String sql = "INSERT INTO wishlist_items(user_id, product_id) VALUES (?, ?)";

        try (Connection conn = DBconfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, productId);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

  
    public void removeItem(int userId, int productId) {

        String sql = "DELETE FROM wishlist_items WHERE user_id = ? AND product_id = ?";

        try (Connection conn = DBconfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, productId);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

  
    public List<Integer> getWishlistProductIds(int userId) {

        List<Integer> productIds = new ArrayList<>();

        String sql = "SELECT product_id FROM wishlist_items WHERE user_id = ?";

        try (Connection conn = DBconfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                productIds.add(rs.getInt("product_id"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return productIds;
    }
}