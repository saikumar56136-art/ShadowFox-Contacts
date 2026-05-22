package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ContactAppGUI extends JFrame {

    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField nameField, phoneField,
            emailField, searchField;
    private ContactManager manager = new ContactManager();
    private ArrayList<Contact> contactList = new ArrayList<>();

    public ContactAppGUI() {
        setTitle("ShadowFox Contact Manager");
        setSize(650, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.decode("#f5f5f5"));

        // Table
        String[] columns = {"Name", "Phone", "Email"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        table.setRowHeight(25);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setBackground(
                Color.decode("#4CAF50"));
        table.getTableHeader().setForeground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(table);

        // Double click to edit
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = table.getSelectedRow();
                    if (row != -1) {
                        nameField.setText(
                                tableModel.getValueAt(row, 0).toString());
                        phoneField.setText(
                                tableModel.getValueAt(row, 1).toString());
                        emailField.setText(
                                tableModel.getValueAt(row, 2).toString());
                    }
                }
            }
        });

        // Search bar
        searchField = new JTextField(20);
        JButton searchBtn = new JButton("Search");
        searchBtn.setBackground(Color.decode("#FF9800"));
        searchBtn.setForeground(Color.WHITE);
        searchBtn.addActionListener(e -> searchContact());

        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(searchField);
        searchPanel.add(searchBtn);

        // Form fields
        nameField = new JTextField(15);
        phoneField = new JTextField(15);
        emailField = new JTextField(15);

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder(
                "Contact Details"));
        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Phone:"));
        formPanel.add(phoneField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);

        // Buttons
        JButton addBtn = new JButton("Add");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");
        JButton clearBtn = new JButton("Clear");
        JButton exportBtn = new JButton("📤 Export VCard");

        addBtn.setBackground(Color.decode("#4CAF50"));
        addBtn.setForeground(Color.WHITE);
        updateBtn.setBackground(Color.decode("#2196F3"));
        updateBtn.setForeground(Color.WHITE);
        deleteBtn.setBackground(Color.decode("#f44336"));
        deleteBtn.setForeground(Color.WHITE);
        clearBtn.setBackground(Color.decode("#9E9E9E"));
        clearBtn.setForeground(Color.WHITE);
        exportBtn.setBackground(Color.decode("#009688"));
        exportBtn.setForeground(Color.WHITE);

        addBtn.addActionListener(e -> addContact());
        updateBtn.addActionListener(e -> updateContact());
        deleteBtn.addActionListener(e -> deleteContact());
        clearBtn.addActionListener(e -> clearFields());
        exportBtn.addActionListener(e -> exportVCard());

        JPanel btnPanel = new JPanel();
        btnPanel.add(addBtn);
        btnPanel.add(updateBtn);
        btnPanel.add(deleteBtn);
        btnPanel.add(clearBtn);
        btnPanel.add(exportBtn);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(formPanel, BorderLayout.CENTER);
        bottomPanel.add(btnPanel, BorderLayout.SOUTH);

        add(searchPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void addContact() {
        String name = nameField.getText().trim();
        String phone = phoneField.getText().trim();
        String email = emailField.getText().trim();

        boolean added = manager.addContact(name, phone, email);
        if (added) {
            contactList.add(new Contact(name, phone, email));
            tableModel.addRow(new Object[]{name, phone, email});
            clearFields();
        }
    }

    private void updateContact() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this,
                    "Please select a contact to update!");
            return;
        }
        String phone = tableModel.getValueAt(row, 1).toString();
        String newName = nameField.getText().trim();
        String newEmail = emailField.getText().trim();

        manager.updateContact(phone, newName, newEmail);

        for (Contact c : contactList) {
            if (c.getPhone().equals(phone)) {
                c.setName(newName);
                c.setEmail(newEmail);
                break;
            }
        }

        tableModel.setValueAt(newName, row, 0);
        tableModel.setValueAt(newEmail, row, 2);
        clearFields();
        JOptionPane.showMessageDialog(this, "✅ Contact updated!");
    }

    private void deleteContact() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this,
                    "Please select a contact to delete!");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete this contact?",
                "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            String phone = tableModel.getValueAt(row, 1).toString();
            manager.deleteContact(phone);
            contactList.removeIf(c -> c.getPhone().equals(phone));
            tableModel.removeRow(row);
            clearFields();
            JOptionPane.showMessageDialog(this,
                    "✅ Contact deleted!");
        }
    }

    private void exportVCard() {
        if (contactList.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "❌ No contacts to export!");
            return;
        }
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(
                new java.io.File("contacts.vcf"));
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            String filename = fileChooser
                    .getSelectedFile().getAbsolutePath();
            boolean exported = manager.exportToVCard(filename);
            if (exported) {
                JOptionPane.showMessageDialog(this,
                        "✅ Contacts exported to:\n" + filename);
            } else {
                JOptionPane.showMessageDialog(this,
                        "❌ Export failed!");
            }
        }
    }

    private void searchContact() {
        String keyword = searchField.getText().toLowerCase();
        tableModel.setRowCount(0);
        for (Contact c : contactList) {
            if (c.getName().toLowerCase().contains(keyword)) {
                tableModel.addRow(new Object[]{
                        c.getName(), c.getPhone(), c.getEmail()});
            }
        }
        if (keyword.isEmpty()) refreshTable();
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        for (Contact c : contactList) {
            tableModel.addRow(new Object[]{
                    c.getName(), c.getPhone(), c.getEmail()});
        }
    }

    private void clearFields() {
        nameField.setText("");
        phoneField.setText("");
        emailField.setText("");
        searchField.setText("");
        refreshTable();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ContactAppGUI::new);
    }
}