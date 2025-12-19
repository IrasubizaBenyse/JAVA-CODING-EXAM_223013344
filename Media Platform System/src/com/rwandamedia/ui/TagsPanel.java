package com.rwandamedia.ui;

import javax.swing.*;
import java.awt.*;

public class TagsPanel extends JPanel {
    public TagsPanel() {
        initializeUI();
    }
    
    private void initializeUI() {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 245, 240));
        
        JLabel titleLabel = new JLabel("Tags Management", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(new Color(0, 122, 51));
        
        JTextArea contentArea = new JTextArea();
        contentArea.setText("Tags Management Panel\n\n" +
                           "Popular Rwandan Tags:\n" +
                           "• Kigali\n" +
                           "• Agriculture\n" +
                           "• Umuganda\n" +
                           "• Gorillas\n" +
                           "• ICT\n\n" +
                           "Manage tags for better content organization.");
        contentArea.setEditable(false);
        contentArea.setFont(new Font("Arial", Font.PLAIN, 12));
        
        add(titleLabel, BorderLayout.NORTH);
        add(new JScrollPane(contentArea), BorderLayout.CENTER);
    }
}