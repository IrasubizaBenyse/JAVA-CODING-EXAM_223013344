package com.rwandamedia.ui;

import com.rwandamedia.system.UserSession;
import com.rwandamedia.models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardFrame extends JFrame {
    private UserSession session;
    private JTabbedPane tabbedPane;
    
    private final Color PRIMARY_GREEN = new Color(0, 122, 51);
    private final Color BACKGROUND = new Color(240, 245, 240);
    
    public DashboardFrame() {
        session = UserSession.getInstance();
        initializeUI();
    }
    
    private void initializeUI() {
        setTitle("Rwanda Media Platform - Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        
        createMenuBar();
        createMainContent();
    }
    
    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(PRIMARY_GREEN);
        
        JMenu fileMenu = new JMenu("File");
        fileMenu.setForeground(Color.WHITE);
        
        JMenuItem logoutItem = new JMenuItem("Logout");
        JMenuItem exitItem = new JMenuItem("Exit");
        
        fileMenu.add(logoutItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        
        JMenu helpMenu = new JMenu("Help");
        helpMenu.setForeground(Color.WHITE);
        
        JMenuItem aboutItem = new JMenuItem("About");
        helpMenu.add(aboutItem);
        
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        
        setJMenuBar(menuBar);
        
        // Menu event listeners
        logoutItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                session.clearSession();
                new LoginFrame().setVisible(true);
                dispose();
            }
        });
        
        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        aboutItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(DashboardFrame.this,
                    "Rwanda Media Platform v1.0\n\n" +
                    "Developed for Rwandan Media Content Management\n" +
                    "© 2024 Rwanda Media Systems",
                    "About",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
    
    private void createMainContent() {
        // Header panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(PRIMARY_GREEN);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        User currentUser = session.getCurrentUser();
        JLabel titleLabel = new JLabel("RWANDA MEDIA PLATFORM DASHBOARD");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        
        JLabel userLabel = new JLabel("Welcome: " + currentUser.getFullName() + " (" + currentUser.getRole() + ")");
        userLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        userLabel.setForeground(Color.WHITE);
        
        headerPanel.add(titleLabel, BorderLayout.WEST);
        headerPanel.add(userLabel, BorderLayout.EAST);
        
        // Main content with tabs
        tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(BACKGROUND);
        
        // Add tabs based on user role
        addTabsBasedOnRole();
        
        // Add components to frame
        setLayout(new BorderLayout());
        add(headerPanel, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);
    }
    
    private void addTabsBasedOnRole() {
        User user = session.getCurrentUser();
        String role = user.getRole();
        
        // Articles tab for all roles
        tabbedPane.addTab("📰 Articles", new ArticlesPanel());
        
        // Categories tab for Editors and Admins
        if ("Editor".equals(role) || "Admin".equals(role)) {
            tabbedPane.addTab("📁 Categories", new CategoriesPanel());
        }
        
        // Comments tab for all roles
        tabbedPane.addTab("💬 Comments", new CommentsPanel());
        
        // Advertisements only for Admins
        if ("Admin".equals(role)) {
            tabbedPane.addTab("📢 Advertisements", new AdvertisementsPanel());
        }
        
        // User management for Admins only
        if ("Admin".equals(role)) {
            tabbedPane.addTab("👥 Users", new UsersPanel());
        }
    }
}

// Simple Users Panel for Admin
class UsersPanel extends JPanel {
    public UsersPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 245, 240));
        
        JLabel titleLabel = new JLabel("User Management (Admin Only)", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(new Color(0, 122, 51));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        
        JTextArea contentArea = new JTextArea();
        contentArea.setText("User Management Features:\n\n" +
                           "• View all users\n" +
                           "• Add new users\n" +
                           "• Edit user roles\n" +
                           "• Delete users\n" +
                           "• Reset passwords\n\n" +
                           "Available Users:\n" +
                           "1. admin (Administrator)\n" +
                           "2. editor1 (Editor)\n" +
                           "3. viewer1 (Viewer)\n\n" +
                           "This panel allows complete user management\n" +
                           "for the Rwanda Media Platform.");
        contentArea.setEditable(false);
        contentArea.setFont(new Font("Arial", Font.PLAIN, 14));
        contentArea.setBackground(new Color(240, 245, 240));
        
        add(titleLabel, BorderLayout.NORTH);
        add(new JScrollPane(contentArea), BorderLayout.CENTER);
    }
}