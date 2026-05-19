package com.Ornexa.service;

import java.sql.Date;

import org.mindrot.jbcrypt.BCrypt;

import com.Ornexa.dao.UserDao;
import com.Ornexa.model.User;

public class RegisterService {
	public boolean addCustomer(User customer) throws Exception{
		
		UserDao dao = new UserDao();
		if(dao.UserExists(customer.getEmail(), customer.getUserName())) {
			System.out.println("User already exists!");
			return false;
		}
		String hashedPassword = BCrypt.hashpw(customer.getPassword(), BCrypt.gensalt(12));


		boolean inserted=dao.insertusers(
				customer.getFirstName(),
				customer.getLastName(),
				customer.getUserName(),
				customer.getEmail(),
				customer.getPhoneNumber(),
				(Date) customer.getDob(),
				hashedPassword,
				new java.sql.Date(System.currentTimeMillis()),
				customer.getImage()
				);
		return inserted;
	}
	public User getUserByUsername(String userName) throws Exception {
	    UserDao dao = new UserDao();
	    return dao.getUserByUsername(userName);
	}
	public void updateUser(User user) {
	    try {
	        UserDao dao = new UserDao();
	        dao.updateUser(user);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	public int getNewUsers() {

	    try {
	        UserDao dao = new UserDao();
	        return dao.getNewUsers();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return 0;
	}



	

}
