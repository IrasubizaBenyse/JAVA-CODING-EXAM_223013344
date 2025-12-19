package com.rwandamedia.ui;

import com.rwandamedia.dao.UserDAO;
import com.rwandamedia.models.User;
import com.rwandamedia.system.PasswordHasher;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserPanel extends JPanel {
    // Colors
    private final Color PRIMARY_COLOR = new Color(44, 62, 80);    // #2C3E50
    private final Color SECONDARY_COLOR = new Color(52, 152, 219); // #3498DB
    private final Color ACCENT_COLOR = new Color(231, 76, 60);     // #E74C3C
    private final Color BACKGROUND_COLOR = new Color(236, 240, 241); // #ECF0F1
    private final Color TEXT_PRIMARY = new Color(44, 62, 80);      // #2C3E50
    private final Color TEXT_SECONDARY = new Color(127, 140, 141); // #7F8C8D
    
    // Components
    private JTextField searchField;
    private JButton searchButton;
    private JButton addUserButton;
    private JTable userTable;
    private DefaultTableModel tableModel;
    private JPanel userFormPanel;
    private JTextField usernameField;
    private JTextField fullNameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JComboBox<String> roleComboBox;
    private JButton saveButton;
    private JButton cancelButton;
    
    // Data
    private UserDAO userDAO;
    private User currentEditingUser;
    private boolean isEditing = false;
    
    public UserPanel() {
        this.userDAO = new UserDAO();
        initializePanel();
        loadUserData();
    }
    
    private void initializePanel() {
        setLayout(new BorderLayout(10, 10));
        setBackground(BACKGROUND_COLOR);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Create components
        createSearchPanel();
        createTablePanel();
        createUserFormPanel();
        
        // Add components to main panel
        add(searchPanel, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);
        add(userFormPanel, BorderLayout.SOUTH);
    }
    
    private void createSearchPanel() {
        JPanel searchPanel = new JPanel(new BorderLayout(10, 0));
        searchPanel.setBackground(BACKGROUND_COLOR);
        searchPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        
        // Search label and field
        JPanel searchInputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        searchInputPanel.setBackground(BACKGROUND_COLOR);
        
        JLabel searchLabel = new JLabel("Search:");
        searchLabel.setForeground(TEXT_PRIMARY);
        searchLabel.setFont(new Font("Arial", Font.BOLD, 12));
        
        searchField = new JTextField(20);
        searchField.setPreferredSize(new Dimension(200, 30));
        
        searchButton = new JButton("Search");
        styleButton(searchButton, SECONDARY_COLOR);
        searchButton.addActionListener(new SearchButtonListener());
        
        searchInputPanel.add(searchLabel);
        searchInputPanel.add(searchField);
        searchInputPanel.add(searchButton);
        
        // Add User button
        addUserButton = new JButton("Add New User");
        styleButton(addUserButton, SECONDARY_COLOR);
        addUserButton.addActionListener(new AddUserButtonListener());
        
        searchPanel.add(searchInputPanel, BorderLayout.WEST);
        searchPanel.add(addUserButton, BorderLayout.EAST);
        
        this.searchPanel = searchPanel;
    }
    
    private void createTablePanel() {
        // Table model
        String[] columnNames = {"ID", "Username", "Email", "Full Name", "Role", "Actions"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5; // Only Actions column is editable
            }
        };
        
        userTable = new JTable(tableModel);
        userTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        userTable.getTableHeader().setBackground(PRIMARY_COLOR);
        userTable.getTableHeader().setForeground(Color.WHITE);
        userTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        
        // Add action buttons to table
        userTable.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());
        userTable.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor(new JCheckBox()));
        
        JScrollPane scrollPane = new JScrollPane(userTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(PRIMARY_COLOR), "User List"));
        scrollPane.setPreferredSize(new Dimension(800, 300));
        
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(BACKGROUND_COLOR);
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        
        this.tablePanel = tablePanel;
    }
    
    private void createUserFormPanel() {
        userFormPanel = new JPanel();
        userFormPanel.setLayout(new GridBagLayout());
        userFormPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(PRIMARY_COLOR), "User Form (Add/Edit)"));
        userFormPanel.setBackground(Color.WHITE);
        userFormPanel.setPreferredSize(new Dimension(800, 150));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Row 1: Username and Full Name
        gbc.gridx = 0; gbc.gridy = 0;
        userFormPanel.add(createLabel("Username:"), gbc);
        gbc.gridx = 1;
        usernameField = new JTextField(15);
        userFormPanel.add(usernameField, gbc);
        
        gbc.gridx = 2; gbc.gridy = 0;
        userFormPanel.add(createLabel("Full Name:"), gbc);
        gbc.gridx = 3;
        fullNameField = new JTextField(15);
        userFormPanel.add(fullNameField, gbc);
        
        // Row 2: Email and Role
        gbc.gridx = 0; gbc.gridy = 1;
        userFormPanel.add(createLabel("Email:"), gbc);
        gbc.gridx = 1;
        emailField = new JTextField(15);
        userFormPanel.add(emailField, gbc);
        
        gbc.gridx = 2; gbc.gridy = 1;
        userFormPanel.add(createLabel("Role:"), gbc);
        gbc.gridx = 3;
        String[] roles = {"Admin", "Editor", "Viewer"};
        roleComboBox = new JComboBox<>(roles);
        userFormPanel.add(roleComboBox, gbc);
        
        // Row 3: Password (only for new users)
        gbc.gridx = 0; gbc.gridy = 2;
        userFormPanel.add(createLabel("Password:"), gbc);
        gbc.gridx = 1;
        passwordField = new JPasswordField(15);
        userFormPanel.add(passwordField, gbc);
        
        // Row 4: Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Color.WHITE);
        
        saveButton = new JButton("Save User");
        styleButton(saveButton, SECONDARY_COLOR);
        saveButton.addActionListener(new SaveButtonListener());
        
        cancelButton = new JButton("Cancel");
        styleButton(cancelButton, TEXT_SECONDARY);
        cancelButton.addActionListener(new CancelButtonListener());
        
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        userFormPanel.add(buttonPanel, gbc);
        
        // Hide form initially
        userFormPanel.setVisible(false);
    }
    
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(TEXT_PRIMARY);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        return label;
    }
    
    private void styleButton(JButton button, Color backgroundColor) {
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        button.setFont(new Font("Arial", Font.BOLD, 12));
    }
    
    // Event Listeners
    private class SearchButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String searchTerm = searchField.getText().trim();
            if (!searchTerm.isEmpty()) {
                searchUsers(searchTerm);
            } else {
                loadUserData();
            }
        }
    }
    
    private class AddUserButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            isEditing = false;
            currentEditingUser = null;
            clearForm();
            userFormPanel.setVisible(true);
            passwordField.setVisible(true);
        }
    }
    
    private class SaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (validateForm()) {
                if (saveUser()) {
                    clearForm();
                    userFormPanel.setVisible(false);
                    loadUserData();
                    JOptionPane.showMessageDialog(UsersPanel.this, 
                        "User saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(UsersPanel.this, 
                        "Error saving user!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    private class CancelButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            clearForm();
            userFormPanel.setVisible(false);
        }
    }
    
    // Business Logic Methods
    private void loadUserData() {
        List<User> users = userDAO.getAllUsers();
        refreshTable(users);
    }
    
    private void refreshTable(List<User> users) {
        tableModel.setRowCount(0); // Clear existing data
        
        for (User user : users) {
            Object[] rowData = {
                user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.getFullName(),
                user.getRole(),
                "Edit"
            };
            tableModel.addRow(rowData);
        }
    }
    
    private void searchUsers(String searchTerm) {
        List<User> users = userDAO.searchUsers(searchTerm);
        refreshTable(users);
    }
    
    private boolean validateForm() {
        if (usernameField.getText().trim().isEmpty()) {
            showError("Username is required");
            return false;
        }
        if (emailField.getText().trim().isEmpty()) {
            showError("Email is required");
            return false;
        }
        if (fullNameField.getText().trim().isEmpty()) {
            showError("Full Name is required");
            return false;
        }
        if (!isEditing && passwordField.getPassword().length == 0) {
            showError("Password is required for new users");
            return false;
        }
        return true;
    }
    
    private boolean saveUser() {
        String username = usernameField.getText().trim();
        String email = emailField.getText().trim();
        String fullName = fullNameField.getText().trim();
        String role = (String) roleComboBox.getSelectedItem();
        
        if (isEditing && currentEditingUser != null) {
            // Update existing user
            currentEditingUser.setUsername(username);
            currentEditingUser.setEmail(email);
            currentEditingUser.setFullName(fullName);
            currentEditingUser.setRole(role);
            return userDAO.updateUser(currentEditingUser);
        } else {
            // Add new user
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setEmail(email);
            newUser.setFullName(fullName);
            newUser.setRole(role);
            
            // Hash password
            String password = new String(passwordField.getPassword());
            newUser.setPasswordHash(PasswordHasher.hashPassword(password));
            
            return userDAO.addUser(newUser);
        }
    }
    
    private void clearForm() {
        usernameField.setText("");
        emailField.setText("");
        fullNameField.setText("");
        passwordField.setText("");
        roleComboBox.setSelectedIndex(0);
        currentEditingUser = null;
        isEditing = false;
    }
    
    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    private void editUser(int userId) {
        currentEditingUser = userDAO.getUserById(userId);
        if (currentEditingUser != null) {
            isEditing = true;
            usernameField.setText(currentEditingUser.getUsername());
            emailField.setText(currentEditingUser.getEmail());
            fullNameField.setText(currentEditingUser.getFullName());
            roleComboBox.setSelectedItem(currentEditingUser.getRole());
            passwordField.setVisible(false);
            userFormPanel.setVisible(true);
        }
    }
    
    // Custom Table Cell Renderer and Editor for Action Buttons
    private class ButtonRenderer extends JButton implements javax.swing.table.TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }
        
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "Edit" : value.toString());
            setBackground(SECONDARY_COLOR);
            setForeground(Color.WHITE);
            setFocusPainted(false);
            setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            return this;
        }
    }
    
    private class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private String label;
        private boolean isPushed;
        
        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                }
            });
        }
        
        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            label = (value == null) ? "Edit" : value.toString();
            button.setText(label);
            button.setBackground(ACCENT_COLOR);
            button.setForeground(Color.WHITE);
            isPushed = true;
            
            // Get the user data from the row
            int userId = (int) table.getValueAt(row, 0);
            editUser(userId);
            
            return button;
        }
        
        public Object getCellEditorValue() {
            if (isPushed) {
                // Action is handled in getTableCellEditorComponent
            }
            isPushed = false;
            return label;
        }
        
        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }
        
        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }
    
    // Components (declare as instance variables)
    private JPanel searchPanel;
    private JPanel tablePanel;
}