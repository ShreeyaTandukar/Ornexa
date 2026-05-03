package com.Ornexa.service;

import com.Ornexa.dao.UserDao;
import com.Ornexa.model.User;
import com.Ornexa.utils.passwordUtils;

public class LoginService {
	private UserDao dao = new UserDao();
	
	public User login(String username, String password) throws Exception{
		User user = dao.getUserByUsername(username);
		
		if (user == null) {
			return null;
		}
		if (!passwordUtils.checkPassword(password, user.getPassword())) {
			return null;
		}
		return user;
	}
	public void approve(String username) throws Exception{
		dao.updateStatus(username,"Approved");
	}

}
