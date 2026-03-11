package ui;

import service.TransactionService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddTransactionScreen extends JFrame {
    private String username;
    private JTextField dateField, timeField, amountField;
    private JComboBox<String> purposeComboBox;
    private JButton saveButton, backButton;
    private TransactionService transactionService;

    public AddTransactionScreen(String username) {
        this.username = username;
        transactionService = new TransactionService();
        
        setTitle("Personal Finance Manager - Add Transaction");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Current date and time
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Date now = new Date();
        
        panel.add(new JLabel("Date (yyyy-MM-dd):"));
        dateField = new JTextField(dateFormat.format(now));
        panel.add(dateField);
        
        panel.add(new JLabel("Time (HH:mm:ss):"));
        timeField = new JTextField(timeFormat.format(now));
        panel.add(timeField);
        
        panel.add(new JLabel("Amount:"));
        amountField = new JTextField();
        panel.add(amountField);
        
        panel.add(new JLabel("Purpose:"));
        String[] purposes = {"Food", "Transportation", "Entertainment", "Bills", "Shopping", "Other"};
        purposeComboBox = new JComboBox<>(purposes);
        panel.add(purposeComboBox);
        
        saveButton = new JButton("Save Transaction");
        panel.add(saveButton);
        
        backButton = new JButton("Back to Dashboard");
        panel.add(backButton);
        
        add(panel);
        
        // Add action listeners
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String date = dateField.getText();
                String time = timeField.getText();
                String amountText = amountField.getText();
                String purpose = (String) purposeComboBox.getSelectedItem();
                
                if (date.isEmpty() || time.isEmpty() || amountText.isEmpty()) {
                    JOptionPane.showMessageDialog(AddTransactionScreen.this, 
                        "All fields are required", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                double amount;
                try {
                    amount = Double.parseDouble(amountText);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(AddTransactionScreen.this, 
                        "Amount must be a number", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (amount <= 0) {
                    JOptionPane.showMessageDialog(AddTransactionScreen.this, 
                        "Amount must be greater than 0", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (transactionService.addTransaction(username, date, time, amount, purpose)) {
                    JOptionPane.showMessageDialog(AddTransactionScreen.this, 
                        "Transaction saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    amountField.setText("");
                } else {
                    JOptionPane.showMessageDialog(AddTransactionScreen.this, 
                        "Failed to save transaction", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DashboardScreen dashboardScreen = new DashboardScreen(username);
                dashboardScreen.setVisible(true);
                dispose();
            }
        });
    }
}