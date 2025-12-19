package com.rwandamedia.ui;

import com.rwandamedia.dao.UserDAO;
import com.rwandamedia.models.User;
import com.rwandamedia.system.UserSession;
import com.rwandamedia.system.PasswordHasher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton cancelButton;
    
    private final Color PRIMARY_GREEN = new Color(0, 122, 51);
    private final Color BACKGROUND = new Color(240, 245, 240);
    
    public LoginFrame() {
        initializeUI();
    }
    
    private void initializeUI() {
        setTitle("Rwanda Media Platform - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BACKGROUND);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Header
        JLabel headerLabel = new JLabel("RWANDA MEDIA PLATFORM", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerLabel.setForeground(PRIMARY_GREEN);
        headerLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        // Login form panel
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        formPanel.setBackground(BACKGROUND);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 12));
        usernameField = new JTextField();
        
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 12));
        passwordField = new JPasswordField();
        
        formPanel.add(usernameLabel);
        formPanel.add(usernameField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);
        
        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(BACKGROUND);
        
        loginButton = new JButton("Login");
        loginButton.setBackground(PRIMARY_GREEN);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        
        cancelButton = new JButton("Exit");
        cancelButton.setBackground(Color.GRAY);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFocusPainted(false);
        
        buttonPanel.add(loginButton);
        buttonPanel.add(cancelButton);
        
        // Add components to main panel
        mainPanel.add(headerLabel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
        
        setupEventListeners();
        
        // Auto-fill for testing
        usernameField.setText("admin");
        passwordField.setText("password");
        usernameField.requestFocus();
    }
    
    private void setupEventListeners() {
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performLogin();
            }
        });
        
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        passwordField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performLogin();
            }
        });
    }
    
    private void performLogin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());
        
        if (username.isEmpty() || password.isEmpty()) {
            showError("Please enter both username and password");
            return;
        }
        
        // Hash the password
        String hashedPassword = PasswordHasher.hashPassword(password);
        System.out.println("Attempting login for user: " + username);
        
        UserDAO userDAO = new UserDAO();
        User user = userDAO.authenticate(username, hashedPassword);
        
        if (user != null) {
            // Update last login
            userDAO.updateLastLogin(user.getUserID());
            
            // Set user session
            UserSession session = UserSession.getInstance();
            session.setCurrentUser(user);
            
            showSuccess("Welcome, " + user.getFullName() + "!\nRole: " + user.getRole());
            
            // Open dashboard
            openDashboard(user);
            
        } else {
            showError("Invalid username or password!\n\nTry these credentials:\n" +
                     "• admin / password (Admin)\n" +
                     "• editor1 / password (Editor)\n" +
                     "• viewer1 / password (Viewer)");
            passwordField.setText("");
            passwordField.requestFocus();
        }
    }
    
    private void openDashboard(User user) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new DashboardFrame().setVisible(true);
            }
        });
        dispose();
    }
    
    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Login Error", JOptionPane.ERROR_MESSAGE);
    }
    
    private void showSuccess(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}