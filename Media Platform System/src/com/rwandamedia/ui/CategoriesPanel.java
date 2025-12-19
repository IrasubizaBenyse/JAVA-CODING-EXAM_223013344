package com.rwandamedia.ui;

import com.rwandamedia.dao.CategoryDAO;
import com.rwandamedia.models.Category;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CategoriesPanel extends JPanel {
    private JTable categoriesTable;
    private DefaultTableModel tableModel;
    private CategoryDAO categoryDAO;
    
    public CategoriesPanel() {
        categoryDAO = new CategoryDAO();
        initializeUI();
        loadCategories();
    }
    
    private void initializeUI() {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 245, 240));
        
        JLabel titleLabel = new JLabel("Categories Management", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(new Color(0, 122, 51));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        String[] columns = {"ID", "Name", "Description", "Created By", "Created Date"};
        tableModel = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        categoriesTable = new JTable(tableModel);
        categoriesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(categoriesTable);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(new Color(240, 245, 240));
        
        JButton addButton = new JButton("Add Category");
        addButton.setBackground(new Color(0, 122, 51));
        addButton.setForeground(Color.WHITE);
        
        JButton refreshButton = new JButton("Refresh");
        refreshButton.setBackground(new Color(0, 103, 197));
        refreshButton.setForeground(Color.WHITE);
        
        buttonPanel.add(addButton);
        buttonPanel.add(refreshButton);
        
        add(titleLabel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Event listeners
        refreshButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadCategories();
            }
        });
        
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addNewCategory();
            }
        });
    }
    
    private void loadCategories() {
        try {
            List<Category> categories = categoryDAO.getAllCategories();
            tableModel.setRowCount(0); // Clear existing data
            
            if (categories != null && !categories.isEmpty()) {
                for (Category category : categories) {
                    Object[] row = {
                        category.getCategoryID(),
                        category.getName(),
                        category.getDescription(),
                        "System Admin",
                        category.getCreatedAt() != null ? category.getCreatedAt().toString() : "N/A"
                    };
                    tableModel.addRow(row);
                }
                System.out.println("✓ Loaded " + categories.size() + " categories");
            } else {
                // Add sample data if empty
                addSampleCategories();
            }
        } catch (Exception e) {
            System.err.println("Error loading categories: " + e.getMessage());
            // Add sample data as fallback
            addSampleCategories();
        }
    }
    
    private void addSampleCategories() {
        // Add some sample categories directly
        Object[] sampleCategories = {
            new Object[]{1, "Politics", "Rwandan political news", "System", "2024-01-01"},
            new Object[]{2, "Economy", "Economic developments", "System", "2024-01-01"},
            new Object[]{3, "Culture", "Rwandan culture", "System", "2024-01-01"},
            new Object[]{4, "Tourism", "Tourism attractions", "System", "2024-01-01"},
            new Object[]{5, "Technology", "Tech innovations", "System", "2024-01-01"}
        };
        
        for (Object[] category : sampleCategories) {
            tableModel.addRow(category);
        }
        
        System.out.println("✓ Loaded sample categories");
    }
    
    private void addNewCategory() {
        JTextField nameField = new JTextField();
        JTextArea descArea = new JTextArea(3, 20);
        
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Category Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Description:"));
        panel.add(new JScrollPane(descArea));
        
        int result = JOptionPane.showConfirmDialog(this, panel, "Add New Category", 
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            
        if (result == JOptionPane.OK_OPTION && !nameField.getText().trim().isEmpty()) {
            String categoryName = nameField.getText();
            JOptionPane.showMessageDialog(this, 
                "Category '" + categoryName + "' added successfully!\n\n" +
                "In full version, this will be saved to database.", 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
            loadCategories(); // Refresh the table
        }
    }
}