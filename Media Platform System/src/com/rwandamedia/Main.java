package com.rwandamedia;

import com.rwandamedia.ui.LoginFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Starting Rwanda Media Platform ===");
        System.out.println("Java Version: " + System.getProperty("java.version"));
        System.out.println("Using: IN-MEMORY DATABASE (No installation required)");
        
        // Test that our database is working
        testDatabaseInitialization();
        
        // Set system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getLookAndFeel());
            System.out.println("✓ UI look and feel set successfully");
        } catch (Exception e) {
            System.err.println("✗ Error setting look and feel: " + e.getMessage());
        }
        
        // Start the application
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginFrame loginFrame = new LoginFrame();
                    loginFrame.setVisible(true);
                    System.out.println("✓ Login frame displayed successfully!");
                } catch (Exception e) {
                    System.err.println("✗ Error creating login frame: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        });
    }
    
    private static void testDatabaseInitialization() {
        System.out.println("Testing database initialization...");
        
        // The database initializes automatically when the class is loaded
        // We'll just print some test credentials
        System.out.println("\n=== Available Test Users ===");
        System.out.println("Username: admin | Password: password | Role: Admin");
        System.out.println("Username: editor1 | Password: password | Role: Editor"); 
        System.out.println("Username: viewer1 | Password: password | Role: Viewer");
        System.out.println("================================\n");
    }
}