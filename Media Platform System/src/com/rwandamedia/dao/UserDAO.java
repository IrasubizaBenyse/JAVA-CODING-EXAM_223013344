package com.rwandamedia.dao;

import com.rwandamedia.system.InMemoryDatabase;
import com.rwandamedia.models.User;
import java.util.List;

public class UserDAO {
    
    public User authenticate(String username, String passwordHash) {
        return InMemoryDatabase.authenticate(username, passwordHash);
    }
    
    public List<User> getAllUsers() {
        return InMemoryDatabase.getAllUsers();
    }
    
    public boolean updateLastLogin(int userID) {
        return InMemoryDatabase.updateLastLogin(userID);
    }
    
    public boolean addUser(User user) {
        System.out.println("User added: " + user.getUsername());
        return true;
    }
    
    public User getUserById(int userID) {
        List<User> users = getAllUsers();
        for (User user : users) {
            if (user.getUserID() == userID) {
                return user;
            }
        }
        return null;
    }
}