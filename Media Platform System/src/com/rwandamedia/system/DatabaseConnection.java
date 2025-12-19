package com.rwandamedia.system;

public class DatabaseConnection {
    
    public static boolean testConnection() {
        System.out.println("✓ Using In-Memory Database - Always Connected");
        return true;
    }
    
    public static void closeConnection() {
        System.out.println("In-Memory database connection closed");
    }
}