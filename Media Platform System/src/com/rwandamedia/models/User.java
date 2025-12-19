package com.rwandamedia.models;

import java.util.Date;

public class User {
    private int userID;
    private String username;
    private String passwordHash;
    private String email;
    private String fullName;
    private String role;
    private Date createdAt;
    private Date lastLogin;
    
    public User() {}
    
    public User(int userID, String username, String passwordHash, String email, 
                String fullName, String role, Date createdAt, Date lastLogin) {
        this.userID = userID;
        this.username = username;
        this.passwordHash = passwordHash;
        this.email = email;
        this.fullName = fullName;
        this.role = role;
        this.createdAt = createdAt;
        this.lastLogin = lastLogin;
    }
    
    // Getters and Setters
    public int getUserID() { return userID; }
    public void setUserID(int userID) { this.userID = userID; }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
    
    public Date getLastLogin() { return lastLogin; }
    public void setLastLogin(Date lastLogin) { this.lastLogin = lastLogin; }
    
    @Override
    public String toString() {
        return "User{userID=" + userID + ", username='" + username + "', fullName='" + fullName + "', role='" + role + "'}";
    }
}