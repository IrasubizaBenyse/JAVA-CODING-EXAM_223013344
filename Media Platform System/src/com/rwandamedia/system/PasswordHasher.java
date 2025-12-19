package com.rwandamedia.system;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHasher {
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // Fallback to simple hash if SHA-256 not available
            System.err.println("SHA-256 not available, using simple hash");
            return Integer.toString(password.hashCode());
        }
    }
    
    public static boolean verifyPassword(String password, String storedHash) {
        return hashPassword(password).equals(storedHash);
    }
}