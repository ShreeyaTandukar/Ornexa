package com.Ornexa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.Ornexa.model.User;
import com.Ornexa.utils.DBconfig;

public class TableDao {
	public ArrayList<User> getCustomer() {
		Connection conn = DBconfig.getConnection();
		String query ="Select firstName,lastName,userName,email,phoneNumber,password,role,status,image,join_at,dob from users";
		PreparedStatement stat =conn.prepareStatement(query);
		ResultSet result= stat.executeQuery();
		ArrayList <User> userss = new ArrayList<>();
		while(result.next()) {
			String firstName = result.getFirstName("firstName");
			String lastName = result.getLastName("lastName");
			String userName = result.getUserName("userName");
			User user= new User(firstName,lastName,userName,email,phoneNumber,password,role,status,image,join_at,dob);
			userss.add(user);
			
		}
		return userss;
	}

}
