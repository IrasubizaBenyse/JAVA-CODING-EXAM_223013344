package com.rwandamedia.ui;

import com.rwandamedia.dao.ArticleDAO;
import com.rwandamedia.models.Article;
import com.rwandamedia.system.UserSession;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ArticlesPanel extends JPanel {
    private JTable articlesTable;
    private DefaultTableModel tableModel;
    private ArticleDAO articleDAO;
    private JButton refreshButton;
    
    public ArticlesPanel() {
        articleDAO = new ArticleDAO();
        initializeUI();
        loadArticles();
    }
    
    private void initializeUI() {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 245, 240));
        
        // Title panel
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(new Color(240, 245, 240));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel titleLabel = new JLabel("Rwandan Articles Management", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(new Color(0, 122, 51));
        
        refreshButton = new JButton("Refresh");
        refreshButton.setBackground(new Color(0, 122, 51));
        refreshButton.setForeground(Color.WHITE);
        
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        titlePanel.add(refreshButton, BorderLayout.EAST);
        
        // Table
        String[] columns = {"ID", "Title", "Category", "Status", "Author", "Created Date"};
        tableModel = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        articlesTable = new JTable(tableModel);
        articlesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        articlesTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        articlesTable.setRowHeight(25);
        
        JScrollPane scrollPane = new JScrollPane(articlesTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(new Color(240, 245, 240));
        
        JButton viewButton = new JButton("View Details");
        viewButton.setBackground(new Color(0, 103, 197));
        viewButton.setForeground(Color.WHITE);
        
        JButton addButton = new JButton("Add New Article");
        addButton.setBackground(new Color(0, 122, 51));
        addButton.setForeground(Color.WHITE);
        
        buttonPanel.add(viewButton);
        buttonPanel.add(addButton);
        
        add(titlePanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Event listeners
        refreshButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadArticles();
            }
        });
        
        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewArticleDetails();
            }
        });
        
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addNewArticle();
            }
        });
    }
    
    private void loadArticles() {
        List<Article> articles = articleDAO.getAllArticles();
        tableModel.setRowCount(0); // Clear existing data
        
        for (Article article : articles) {
            Object[] row = {
                article.getArticleID(),
                article.getTitle(),
                article.getCategoryName(),
                article.getStatus(),
                article.getAuthorName(),
                article.getCreatedAt()
            };
            tableModel.addRow(row);
        }
        
        JOptionPane.showMessageDialog(this, 
            "Loaded " + articles.size() + " articles from database!", 
            "Success", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void viewArticleDetails() {
        int selectedRow = articlesTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Please select an article first!", 
                "No Selection", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int articleId = (Integer) tableModel.getValueAt(selectedRow, 0);
        String title = (String) tableModel.getValueAt(selectedRow, 1);
        String category = (String) tableModel.getValueAt(selectedRow, 2);
        String status = (String) tableModel.getValueAt(selectedRow, 3);
        
        JTextArea textArea = new JTextArea(10, 40);
        textArea.setText("Article Details:\n\n" +
                        "ID: " + articleId + "\n" +
                        "Title: " + title + "\n" +
                        "Category: " + category + "\n" +
                        "Status: " + status + "\n\n" +
                        "This feature will show complete article content,\n" +
                        "comments, and editing options in the full version.");
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.PLAIN, 12));
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        JOptionPane.showMessageDialog(this, scrollPane, "Article Details: " + title, JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void addNewArticle() {
        JTextField titleField = new JTextField();
        JTextArea descArea = new JTextArea(3, 20);
        JComboBox<String> categoryBox = new JComboBox<>(new String[]{"Politics", "Economy", "Culture", "Tourism", "Technology"});
        JComboBox<String> statusBox = new JComboBox<>(new String[]{"Draft", "Published"});
        
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Title:"));
        panel.add(titleField);
        panel.add(new JLabel("Description:"));
        panel.add(new JScrollPane(descArea));
        panel.add(new JLabel("Category:"));
        panel.add(categoryBox);
        panel.add(new JLabel("Status:"));
        panel.add(statusBox);
        
        int result = JOptionPane.showConfirmDialog(this, panel, "Add New Article", 
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            
        if (result == JOptionPane.OK_OPTION) {
            JOptionPane.showMessageDialog(this, 
                "Article '" + titleField.getText() + "' added successfully!\n\n" +
                "In full version, this will be saved to database.", 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
}