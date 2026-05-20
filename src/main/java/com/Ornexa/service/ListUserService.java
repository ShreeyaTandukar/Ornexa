package com.Ornexa.service;

import java.util.List;
import com.Ornexa.dao.UserDao;
import com.Ornexa.model.User;

public class ListUserService {
    
    
    private UserDao dao = new UserDao();
    
    public List<User> getUsersByStatus(String status) throws Exception {
        // Handle null/empty/all cases
        if (status == null || "all".equalsIgnoreCase(status) || status.isEmpty()) {
            return dao.getAllUsers(); 
        } 
        
        return dao.selectUsersByStatus(status);
    }

    public List<User> getAllUsers() throws Exception {
        return dao.getAllUsers();
    }
}
