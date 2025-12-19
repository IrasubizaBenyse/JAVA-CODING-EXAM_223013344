package com.rwandamedia.ui;

import com.rwandamedia.dao.AdvertisementDAO;
import com.rwandamedia.models.Advertisement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AdvertisementsPanel extends JPanel {
    private JTable adsTable;
    private DefaultTableModel tableModel;
    private AdvertisementDAO adDAO;
    
    public AdvertisementsPanel() {
        adDAO = new AdvertisementDAO();
        initializeUI();
        loadAdvertisements();
    }
    
    private void initializeUI() {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 245, 240));
        
        JLabel titleLabel = new JLabel("Advertisements Management", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(new Color(0, 122, 51));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        String[] columns = {"ID", "Title", "Status", "Target URL", "Created Date"};
        tableModel = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        adsTable = new JTable(tableModel);
        adsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(adsTable);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(new Color(240, 245, 240));
        
        JButton addButton = new JButton("Add Advertisement");
        addButton.setBackground(new Color(0, 122, 51));
        addButton.setForeground(Color.WHITE);
        
        JButton toggleButton = new JButton("Toggle Status");
        toggleButton.setBackground(new Color(250, 202, 0));
        toggleButton.setForeground(Color.BLACK);
        
        JButton refreshButton = new JButton("Refresh");
        refreshButton.setBackground(new Color(0, 103, 197));
        refreshButton.setForeground(Color.WHITE);
        
        buttonPanel.add(addButton);
        buttonPanel.add(toggleButton);
        buttonPanel.add(refreshButton);
        
        add(titleLabel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Event listeners
        refreshButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadAdvertisements();
            }
        });
        
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addNewAdvertisement();
            }
        });
        
        toggleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                toggleAdStatus();
            }
        });
    }
    
    private void loadAdvertisements() {
        List<Advertisement> ads = adDAO.getAllAdvertisements();
        tableModel.setRowCount(0);
        
        for (Advertisement ad : ads) {
            Object[] row = {
                ad.getAdvertisementID(),
                ad.getTitle(),
                ad.getStatus(),
                ad.getTargetURL(),
                ad.getCreatedAt()
            };
            tableModel.addRow(row);
        }
    }
    
    private void addNewAdvertisement() {
        JTextField titleField = new JTextField();
        JTextArea descArea = new JTextArea(3, 20);
        JTextField urlField = new JTextField();
        JComboBox<String> statusBox = new JComboBox<>(new String[]{"Active", "Inactive"});
        
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Advertisement Title:"));
        panel.add(titleField);
        panel.add(new JLabel("Description:"));
        panel.add(new JScrollPane(descArea));
        panel.add(new JLabel("Target URL:"));
        panel.add(urlField);
        panel.add(new JLabel("Status:"));
        panel.add(statusBox);
        
        int result = JOptionPane.showConfirmDialog(this, panel, "Add New Advertisement", 
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            
        if (result == JOptionPane.OK_OPTION && !titleField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Advertisement '" + titleField.getText() + "' added successfully!", 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
            loadAdvertisements();
        }
    }
    
    private void toggleAdStatus() {
        int selectedRow = adsTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Please select an advertisement first!", 
                "No Selection", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int adId = (Integer) tableModel.getValueAt(selectedRow, 0);
        String currentStatus = (String) tableModel.getValueAt(selectedRow, 2);
        String newStatus = "Active".equals(currentStatus) ? "Inactive" : "Active";
        
        JOptionPane.showMessageDialog(this,
            "Advertisement #" + adId + " status changed from " + 
            currentStatus + " to " + newStatus + "!",
            "Status Updated",
            JOptionPane.INFORMATION_MESSAGE);
            
        loadAdvertisements();
    }
}