package com.Ornexa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.Ornexa.utils.DBconfig;

public class PaymentDao {

	// Step 1 — insert into payment table, return new Payment_Id
	public int insertPayment(String paymentMethod, double totalAmount) throws Exception {
		Connection conn = DBconfig.getConnection();

		String sql = "INSERT INTO payment (Payment_Method, Payment_Status, Total_Amount) " +
				"VALUES (?, 'completed', ?)";
		PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, paymentMethod);
		ps.setDouble(2, totalAmount);
		ps.executeUpdate();

		ResultSet keys = ps.getGeneratedKeys();
		int paymentId = 0;
		if (keys.next()) {
			paymentId = keys.getInt(1);
		}

		keys.close();
		ps.close();
		conn.close();
		return paymentId;
	}

	// Step 2 — insert into order_table, return new Order_Id
	public int insertOrder(int userId, String destination, double totalAmount) throws Exception {
		Connection conn = DBconfig.getConnection();

		String sql = "INSERT INTO order_table (Destination, Total_Amount, Order_Status, user_id) " +
	             "VALUES (?, ?, 'placed', ?)";
		PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, destination);
		ps.setDouble(2, totalAmount);
		ps.setInt(3, userId);
		ps.executeUpdate();

		ResultSet keys = ps.getGeneratedKeys();
		int orderId = 0;
		if (keys.next()) {
			orderId = keys.getInt(1);
		}

		keys.close();
		ps.close();
		conn.close();
		return orderId;
	}

	// Step 3 — insert each cart item into orderitem table
	public void insertOrderItem(int orderId, int paymentId, int productId,
			int quantity, double price) throws Exception {
		Connection conn = DBconfig.getConnection();

		String sql = "INSERT INTO orderitem (Quantity, Price, Order_Id, Payment_Id, Product_Id) " +
				"VALUES (?, ?, ?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, quantity);
		ps.setDouble(2, price);
		ps.setInt(3, orderId);
		ps.setInt(4, paymentId);
		ps.setInt(5, productId);
		ps.executeUpdate();
		ps.close();
		conn.close();
	}

	// Helper — get Product_Id from product name
	public int getProductIdByName(String productName) throws Exception {
		Connection conn = DBconfig.getConnection();

		String sql = "SELECT Product_Id FROM product WHERE Product_Name = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, productName);
		ResultSet rs = ps.executeQuery();

		int productId = 0;
		if (rs.next()) {
			productId = rs.getInt("Product_Id");
		}

		rs.close();
		ps.close();
		conn.close();
		return productId;
	}
}