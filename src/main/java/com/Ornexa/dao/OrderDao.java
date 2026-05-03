package com.Ornexa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.Ornexa.model.Order;
import com.Ornexa.model.OrderItem;
import com.Ornexa.utils.DBconfig;

public class OrderDao {
	public List<Order> getAllOrders() throws Exception{
		Connection conn =DBconfig.getConnection();
		String sql = "SELECT O.Order_Id, O.Destination, O.Total_Amount, O.Order_Status, U.userName "
		           + "FROM order_table O "
		           + "JOIN users U ON O.user_id = U.user_id "
		           + "ORDER BY O.Order_Id DESC";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Order> ordersList = new ArrayList <>();
		
		while (rs.next()) {
			Order orders = new Order();
			orders.setId(rs.getInt("Order_Id"));
			orders.setUserName(rs.getString("userName"));
			orders.setDestination(rs.getString("Destination"));
			orders.setAmount(rs.getDouble("Total_Amount"));
			orders.setStatus(rs.getString("Order_Status"));
			ordersList.add(orders);
			
			
		}
		rs.close();
		ps.close();
		conn.close();
		return ordersList;

	}
	public double getTotalSales() throws Exception {

	    Connection conn = DBconfig.getConnection();

	    String sql = "SELECT SUM(Total_Amount) FROM order_table";

	    PreparedStatement ps = conn.prepareStatement(sql);
	    ResultSet rs = ps.executeQuery();

	    double total = 0;

	    if (rs.next()) {
	        total = rs.getDouble(1);
	    }

	    rs.close();
	    ps.close();
	    conn.close();

	    return total;
	}
	 public List<Order> getAllOrdersA() {

	        List<Order> list = new ArrayList<>();

	        String sql = "SELECT o.*, u.userName, u.email " +
	                     "FROM order_table o " +
	                     "JOIN users u ON o.user_id = u.user_id " +
	                     "ORDER BY o.Order_Id DESC";

	        try (Connection con = DBconfig.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql);
	             ResultSet rs = ps.executeQuery()) {

	            while (rs.next()) {

	                Order o = new Order();

	                o.setId(rs.getInt("Order_Id"));
	                o.setOrderDate(rs.getString("Order_Date"));
	                o.setDestination(rs.getString("Destination"));
	                o.setAmount(rs.getDouble("Total_Amount"));
	                o.setStatus(rs.getString("Order_Status"));

	                o.setUserName(rs.getString("userName"));
	                o.setEmail(rs.getString("email"));

	                list.add(o);
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return list;
	    }


	    public boolean updateOrderStatus(int orderId, String status) {

	        String sql = "UPDATE order_table SET Order_Status=? WHERE Order_Id=?";

	        try (Connection con = DBconfig.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql)) {

	            ps.setString(1, status);
	            ps.setInt(2, orderId);

	            return ps.executeUpdate() > 0;

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return false;
	    }


	    public List<Order> getFilteredOrders(String status, String fromDate, String toDate) {

	        List<Order> list = new ArrayList<>();

	        String sql = "SELECT o.*, u.userName, u.email " +
	                     "FROM order_table o " +
	                     "JOIN users u ON o.user_id = u.user_id " +
	                     "WHERE 1=1";

	        if (status != null && !status.isEmpty()) {
	            sql += " AND o.Order_Status = ?";
	        }

	        if (fromDate != null && !fromDate.isEmpty()) {
	            sql += " AND DATE(o.Order_Date) >= ?";
	        }

	        if (toDate != null && !toDate.isEmpty()) {
	            sql += " AND DATE(o.Order_Date) <= ?";
	        }

	        sql += " ORDER BY o.Order_Id DESC";

	        try (Connection con = DBconfig.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql)) {

	            int i = 1;

	            if (status != null && !status.isEmpty()) {
	                ps.setString(i++, status);
	            }

	            if (fromDate != null && !fromDate.isEmpty()) {
	                ps.setString(i++, fromDate);
	            }

	            if (toDate != null && !toDate.isEmpty()) {
	                ps.setString(i++, toDate);
	            }

	            ResultSet rs = ps.executeQuery();

	            while (rs.next()) {

	                Order o = new Order();

	                o.setId(rs.getInt("Order_Id"));
	                o.setOrderDate(rs.getString("Order_Date"));
	                o.setDestination(rs.getString("Destination"));
	                o.setAmount(rs.getDouble("Total_Amount"));
	                o.setStatus(rs.getString("Order_Status"));

	                o.setUserName(rs.getString("userName"));
	                o.setEmail(rs.getString("email"));

	                list.add(o);
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return list;
	    }


	    public double getTotalRevenue() {

	        double total = 0;

	        String sql = "SELECT SUM(Total_Amount) FROM order_table WHERE Order_Status='DELIVERED'";

	        try (Connection con = DBconfig.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql);
	             ResultSet rs = ps.executeQuery()) {

	            if (rs.next()) {
	                total = rs.getDouble(1);
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return total;
	    }

	 
	    public double getMonthlyRevenue(int monthOffset) {

	        double total = 0;

	        String sql = "SELECT SUM(Total_Amount) FROM order_table " +
	                     "WHERE MONTH(Order_Date) = MONTH(CURDATE() - INTERVAL ? MONTH) " +
	                     "AND Order_Status='DELIVERED'";

	        try (Connection con = DBconfig.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql)) {

	            ps.setInt(1, monthOffset);

	            ResultSet rs = ps.executeQuery();

	            if (rs.next()) {
	                total = rs.getDouble(1);
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return total;
	    }
	    
	   
	    
	    public List<OrderItem> getItemsByOrderId(int orderId) {

	        List<OrderItem> list = new ArrayList<>();

	        String sql = "SELECT oi.*, p.Product_Name " +
	                     "FROM orderitem oi " +
	                     "JOIN product p ON oi.Product_Id = p.Product_Id " +
	                     "WHERE oi.Order_Id = ?";

	        try (Connection con = DBconfig.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql)) {

	            ps.setInt(1, orderId);
	            ResultSet rs = ps.executeQuery();

	            while (rs.next()) {

	                OrderItem item = new OrderItem();

	                item.setOrderId(rs.getInt("Order_Id"));
	                item.setProductId(rs.getInt("Product_Id"));
	                item.setQuantity(rs.getInt("Quantity"));
	                item.setPrice(rs.getDouble("Price")); 
	                item.setProductName(rs.getString("Product_Name")); 

	                list.add(item);
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return list;
	    }
	    
	    public Order getSingleOrder(int orderId) {

	        String sql = "SELECT o.*, u.userName, u.email " +
	                     "FROM order_table o " +
	                     "JOIN users u ON o.user_id = u.user_id " +
	                     "WHERE o.Order_Id = ?";

	        try (Connection con = DBconfig.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql)) {

	            ps.setInt(1, orderId);
	            ResultSet rs = ps.executeQuery();

	            if (rs.next()) {

	                Order o = new Order();

	                o.setId(rs.getInt("Order_Id"));
	                o.setOrderDate(rs.getString("Order_Date"));
	                o.setDestination(rs.getString("Destination"));
	                o.setAmount(rs.getDouble("Total_Amount"));
	                o.setStatus(rs.getString("Order_Status"));

	                o.setUserName(rs.getString("userName"));
	                o.setEmail(rs.getString("email"));

	                return o;
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return null;
	    }

}
