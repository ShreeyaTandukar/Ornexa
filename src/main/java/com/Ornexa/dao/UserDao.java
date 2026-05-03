package com.Ornexa.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Ornexa.model.User;
import com.Ornexa.utils.DBconfig;

public class UserDao {
	
	public boolean insertusers(String firstName,String lastname,String username, String email,String Phonenum,Date Dob, String Password, Date joinat,String image) throws Exception{
		Connection con = DBconfig.getConnection();
		String sql = "INSERT INTO users(firstName,lastName,userName,email,phoneNumber,password,role,status,image,join_at,dob)"
		           + " VALUES (?,?,?,?,?,?,?,?,?,?,?)";



		PreparedStatement customer = con.prepareStatement(sql);
		customer.setString(1, firstName);
		customer.setString(2, lastname);
		customer.setString(3, username);
		customer.setString(4, email);
		customer.setString(5, Phonenum);
		customer.setString(6, Password);
		customer.setString(7, "customer"); 
		customer.setString(8, "pending"); 
		customer.setString(9, image);
		customer.setDate(10, joinat);
		customer.setDate(11, Dob); 


		int rows = customer.executeUpdate();
		System.out.println("Rows inserted: " + rows);

		customer.close();
		con.close();
		return rows > 0;
	}
	public boolean UserExists(String email, String username) throws Exception {
		Connection con =DBconfig.getConnection();
		String sql ="SELECT * FROM users WHERE email=? OR userName=?";
		PreparedStatement exists =con.prepareStatement(sql);
		exists.setString(1, email);
		exists.setString(2, username);
		
		ResultSet result = exists.executeQuery();
		boolean exist =result.next();
		result.close();
		exists.close();
		con.close();
		return exist;
	}
	public User getUserByUsername(String userName) throws Exception{
		Connection conn = DBconfig.getConnection();
		String sql ="SELECT * FROM users WHERE userName = ?";
		PreparedStatement username = conn.prepareStatement(sql);
		username.setString(1, userName);
		ResultSet rs =username.executeQuery();
		
		if(rs.next()) {
			User user =new User();
			user.setId(rs.getInt("user_id"));
			user.setUserName(rs.getString("userName"));
			user.setPassword(rs.getString("password"));
			user.setStatus(rs.getString("status"));
			user.setRole(rs.getString("role"));
			user.setImage(rs.getString("image"));
			return user;
		}
		return null;
	}
	public void updateStatus(String username,String status) throws Exception{
		Connection con = DBconfig.getConnection();
		String sql ="UPDATE users SET status =? WHERE userName=?";
		PreparedStatement prepare = con.prepareStatement(sql);
		prepare.setString(1, status);
		prepare.setString(2, username);
		prepare.executeUpdate();
	}
	public void updateUser(User user) throws Exception {
	    String sql = "UPDATE users SET userName=?, phoneNumber=?, image=? WHERE user_id=?";

	    try (Connection con = DBconfig.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setString(1, user.getUserName());
	        ps.setString(2, user.getPhoneNumber());
	        ps.setString(3, user.getImage());
	        ps.setInt(4, user.getId());

	        ps.executeUpdate();
	    }
	}

	
	public List<User> getAllUsers() throws Exception{
		
		Connection con = DBconfig.getConnection();
		String sql ="Select * FROM users";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		List<User> usersList = new ArrayList<>();
		
		while (rs.next()) {
			User users = new User();
			users.setId(rs.getInt("user_id"));
			 users.setUserName(rs.getString("userName"));
		     users.setEmail(rs.getString("email"));
		     users.setRole(rs.getString("role"));
		     users.setStatus(rs.getString("status"));
		     users.setImage(rs.getString("image"));
		     usersList.add(users);
		}
		rs.close();
		ps.close();
		con.close();
		return usersList;
		
	}
	public int getTotalUsers() throws Exception{
		Connection conn = DBconfig.getConnection();
		String sql ="SELECT COUNT(*) FROM users";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		int count =0;
		if(rs.next()) {
			count=rs.getInt(1);
		}
		conn.close();
		return count;
	}
	public int getPendingUsers() throws Exception{
		Connection conn = DBconfig.getConnection();
		String sql = "SELECT COUNT(*) FROM users WHERE LOWER(status) = 'pending'";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		int count = 0;
		if(rs.next()) {
			count = rs.getInt(1);
		}
		conn.close();
		return count;
	}
	public int getNewUsers() throws Exception {

	    Connection conn = DBconfig.getConnection();

	    String sql = "SELECT COUNT(*) FROM users WHERE status='pending'";

	    PreparedStatement ps = conn.prepareStatement(sql);
	    ResultSet rs = ps.executeQuery();

	    int count = 0;

	    if (rs.next()) {
	        count = rs.getInt(1);
	    }

	    rs.close();
	    ps.close();
	    conn.close();

	    return count;
	}
	public List<User> selectUsersByStatus(String status) {
	    List<User> users = new java.util.ArrayList<>();
	    // SQL query to filter by the status column
	    String sql = "SELECT * FROM users WHERE status = ?";

	    try (Connection conn = DBconfig.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        
	        ps.setString(1, status);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            User user = new User();
	            user.setUserName(rs.getString("username"));
	            user.setEmail(rs.getString("email"));
	            user.setRole(rs.getString("role"));
	            user.setStatus(rs.getString("status"));
	            // Set other fields as necessary
	            users.add(user);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return users;
	}
	


}
