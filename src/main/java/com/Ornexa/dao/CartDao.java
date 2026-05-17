package com.Ornexa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.Ornexa.model.CartItem;
import com.Ornexa.utils.DBconfig;

public class CartDao {

	public int getOrCreateCart(int userId) throws Exception {
		Connection conn = DBconfig.getConnection();

		String selectSql = "SELECT Cart_Id FROM cart " +
				"WHERE user_id = ? AND Cart_Status = 'active' " +
				"ORDER BY created_at DESC LIMIT 1";
		PreparedStatement ps = conn.prepareStatement(selectSql);
		ps.setInt(1, userId);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			int cartId = rs.getInt("Cart_Id");
			rs.close();
			ps.close();
			conn.close();
			return cartId;
		}

		rs.close();
		ps.close();

		String reactivateSql = "SELECT Cart_Id FROM cart " +
				"WHERE user_id = ? " +
				"ORDER BY created_at DESC LIMIT 1";
		PreparedStatement ps2 = conn.prepareStatement(reactivateSql);
		ps2.setInt(1, userId);
		ResultSet rs2 = ps2.executeQuery();

		if (rs2.next()) {
			int existingCartId = rs2.getInt("Cart_Id");
			rs2.close();
			ps2.close();

			String updateSql = "UPDATE cart SET Cart_Status = 'active' WHERE Cart_Id = ?";
			PreparedStatement ps3 = conn.prepareStatement(updateSql);
			ps3.setInt(1, existingCartId);
			ps3.executeUpdate();
			ps3.close();
			conn.close();
			return existingCartId;
		}

		rs2.close();
		ps2.close();

		String insertSql = "INSERT INTO cart (Cart_Status, user_id) VALUES ('active', ?)";
		PreparedStatement ps4 = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
		ps4.setInt(1, userId);
		ps4.executeUpdate();

		ResultSet keys = ps4.getGeneratedKeys();
		int newCartId = 0;
		if (keys.next()) {
			newCartId = keys.getInt(1);
		}

		keys.close();
		ps4.close();
		conn.close();
		return newCartId;
	}

	public List<CartItem> getCartItems(int cartId) throws Exception {
		Connection conn = DBconfig.getConnection();

		String sql = "SELECT ci.Cart_item_Id, ci.Item_Name, ci.price_at_time, " +
				"ci.Product_Quantity, ci.SubTotal, ci.Cart_Id, p.img_url " +
				"FROM cart_item ci " +
				"JOIN product p ON p.Product_Name = ci.Item_Name " +
				"WHERE ci.Cart_Id = ?";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, cartId);
		ResultSet rs = ps.executeQuery();

		List<CartItem> items = new ArrayList<>();

		while (rs.next()) {
			CartItem item = new CartItem();
			item.setCartItemId(rs.getInt("Cart_item_Id"));
			item.setItemName(rs.getString("Item_Name"));
			item.setPriceAtTime(rs.getDouble("price_at_time"));
			item.setProductQuantity(rs.getInt("Product_Quantity"));
			item.setSubTotal(rs.getDouble("SubTotal"));
			item.setCartId(rs.getInt("Cart_Id"));
			item.setImgUrl(rs.getString("img_url"));
			items.add(item);
		}

		rs.close();
		ps.close();
		conn.close();
		return items;
	}

	public void addItem(int cartId, String productName, double price) throws Exception {
		Connection conn = DBconfig.getConnection();

		String checkSql = "SELECT Cart_item_Id, Product_Quantity FROM cart_item " +
				"WHERE Cart_Id = ? AND Item_Name = ?";
		PreparedStatement ps = conn.prepareStatement(checkSql);
		ps.setInt(1, cartId);
		ps.setString(2, productName);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			int existingId = rs.getInt("Cart_item_Id");
			int newQty = rs.getInt("Product_Quantity") + 1;
			double newTotal = price * newQty;

			rs.close();
			ps.close();

			String updateSql = "UPDATE cart_item SET Product_Quantity = ?, SubTotal = ? " +
					"WHERE Cart_item_Id = ?";
			PreparedStatement ps2 = conn.prepareStatement(updateSql);
			ps2.setInt(1, newQty);
			ps2.setDouble(2, newTotal);
			ps2.setInt(3, existingId);
			ps2.executeUpdate();
			ps2.close();

		} else {
			rs.close();
			ps.close();

			String insertSql = "INSERT INTO cart_item " +
					"(Item_Name, price_at_time, Product_Quantity, SubTotal, Cart_Id) " +
					"VALUES (?, ?, 1, ?, ?)";
			PreparedStatement ps2 = conn.prepareStatement(insertSql);
			ps2.setString(1, productName);
			ps2.setDouble(2, price);
			ps2.setDouble(3, price);
			ps2.setInt(4, cartId);
			ps2.executeUpdate();
			ps2.close();
		}

		conn.close();
	}

	public void updateQuantity(int cartItemId, int newQty, double priceAtTime) throws Exception {
		Connection conn = DBconfig.getConnection();

		String sql = "UPDATE cart_item SET Product_Quantity = ?, SubTotal = ? " +
				"WHERE Cart_item_Id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, newQty);
		ps.setDouble(2, priceAtTime * newQty);
		ps.setInt(3, cartItemId);
		ps.executeUpdate();
		ps.close();
		conn.close();
	}

	public void removeItem(int cartItemId) throws Exception {
		Connection conn = DBconfig.getConnection();

		String sql = "DELETE FROM cart_item WHERE Cart_item_Id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, cartItemId);
		ps.executeUpdate();
		ps.close();
		conn.close();
	}

	public void closeCart(int cartId, int userId) throws Exception {
		Connection conn = DBconfig.getConnection();

		
		String sql = "UPDATE cart SET Cart_Status = 'completed' WHERE Cart_Id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, cartId);
		ps.executeUpdate();
		ps.close();

		
		String insertSql = "INSERT INTO cart (Cart_Status, user_id) VALUES ('active', ?)";
		PreparedStatement ps2 = conn.prepareStatement(insertSql);
		ps2.setInt(1, userId);
		ps2.executeUpdate();
		ps2.close();
		conn.close();
	}
}