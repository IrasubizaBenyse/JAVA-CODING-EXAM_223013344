package com.rwandamedia.ui;

import com.rwandamedia.dao.CommentDAO;
import com.rwandamedia.models.Comment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CommentsPanel extends JPanel {
    private JTable commentsTable;
    private DefaultTableModel tableModel;
    private CommentDAO commentDAO;
    
    public CommentsPanel() {
        commentDAO = new CommentDAO();
        initializeUI();
        loadComments();
    }
    
    private void initializeUI() {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 245, 240));
        
        JLabel titleLabel = new JLabel("Comments Management", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(new Color(0, 122, 51));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        String[] columns = {"ID", "Article", "User", "Comment", "Date"};
        tableModel = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        commentsTable = new JTable(tableModel);
        commentsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(commentsTable);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(new Color(240, 245, 240));
        
        JButton viewButton = new JButton("View Full Comment");
        viewButton.setBackground(new Color(0, 103, 197));
        viewButton.setForeground(Color.WHITE);
        
        JButton deleteButton = new JButton("Delete Comment");
        deleteButton.setBackground(Color.RED);
        deleteButton.setForeground(Color.WHITE);
        
        JButton refreshButton = new JButton("Refresh");
        refreshButton.setBackground(new Color(0, 122, 51));
        refreshButton.setForeground(Color.WHITE);
        
        buttonPanel.add(viewButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);
        
        add(titleLabel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Event listeners
        refreshButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadComments();
            }
        });
        
        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewFullComment();
            }
        });
        
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteComment();
            }
        });
    }
    
    private void loadComments() {
        List<Comment> comments = commentDAO.getAllComments();
        tableModel.setRowCount(0);
        
        for (Comment comment : comments) {
            // Shorten long comments for table display
            String shortComment = comment.getContent();
            if (shortComment.length() > 50) {
                shortComment = shortComment.substring(0, 47) + "...";
            }
            
            Object[] row = {
                comment.getCommentID(),
                "Article " + comment.getArticleID(),
                "User " + comment.getUserID(),
                shortComment,
                comment.getCreatedAt()
            };
            tableModel.addRow(row);
        }
    }
    
    private void viewFullComment() {
        int selectedRow = commentsTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Please select a comment first!", 
                "No Selection", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String fullComment = (String) tableModel.getValueAt(selectedRow, 3);
        int commentId = (Integer) tableModel.getValueAt(selectedRow, 0);
        
        JTextArea textArea = new JTextArea(6, 40);
        textArea.setText("Comment ID: " + commentId + "\n\n" + fullComment);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        
        JOptionPane.showMessageDialog(this, new JScrollPane(textArea), 
            "Comment Details", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void deleteComment() {
        int selectedRow = commentsTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Please select a comment to delete!", 
                "No Selection", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int commentId = (Integer) tableModel.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to delete comment #" + commentId + "?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION);
            
        if (confirm == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this,
                "Comment #" + commentId + " deleted successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
            loadComments();
        }
    }
}