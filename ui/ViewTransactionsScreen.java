package ui;

import model.Transaction;
import service.TransactionService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ViewTransactionsScreen extends JFrame {
    private JTextArea transactionsArea;
    private JButton refreshButton, backButton;
    private String username;
    private TransactionService transactionService;

    public ViewTransactionsScreen(String username) {
        this.username = username;
        this.transactionService = new TransactionService();
        
        setTitle("View Transactions");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Transactions display area
        transactionsArea = new JTextArea();
        transactionsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(transactionsArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        refreshButton = new JButton("Refresh");
        backButton = new JButton("Back to Dashboard");
        
        buttonPanel.add(refreshButton);
        buttonPanel.add(backButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
        
        // Load transactions
        loadTransactions();
        
        // Add action listeners
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadTransactions();
            }
        });
        
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DashboardScreen dashboard = new DashboardScreen(username);
                dashboard.setVisible(true);
                dispose();
            }
        });
    }
    
    private void loadTransactions() {
        List<Transaction> transactions = transactionService.getUserTransactions(username);
        StringBuilder sb = new StringBuilder();
        sb.append("Date\t\tTime\t\tAmount\t\tPurpose\n");
        sb.append("------------------------------------------------------------\n");
        
        for (Transaction transaction : transactions) {
            sb.append(transaction.getDate()).append("\t");
            sb.append(transaction.getTime()).append("\t");
            sb.append("$").append(transaction.getAmount()).append("\t\t");
            sb.append(transaction.getPurpose()).append("\n");
        }
        
        transactionsArea.setText(sb.toString());
    }
}