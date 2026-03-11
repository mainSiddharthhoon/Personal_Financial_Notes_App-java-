package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardScreen extends JFrame {
    private String username;

    public DashboardScreen(String username) {
        this.username = username;
        setTitle("Personal Finance Manager - Dashboard");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        
        // Welcome message
        JLabel welcomeLabel = new JLabel("Welcome, " + username + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(welcomeLabel, BorderLayout.NORTH);
        
        // Buttons panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(5, 1, 10, 10));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        
        JButton viewTransactionsButton = new JButton("View Transactions");
        JButton addTransactionButton = new JButton("Add Transaction");
        JButton budgetButton = new JButton("Budget Management");
        JButton reportButton = new JButton("Generate Reports");
        JButton logoutButton = new JButton("Logout");
        
        buttonsPanel.add(viewTransactionsButton);
        buttonsPanel.add(addTransactionButton);
        buttonsPanel.add(budgetButton);
        buttonsPanel.add(reportButton);
        buttonsPanel.add(logoutButton);
        
        panel.add(buttonsPanel, BorderLayout.CENTER);
        
        add(panel);
        
        // Add action listeners
        viewTransactionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewTransactionsScreen viewScreen = new ViewTransactionsScreen(username);
                viewScreen.setVisible(true);
                dispose();
            }
        });
        
        addTransactionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddTransactionScreen addScreen = new AddTransactionScreen(username);
                addScreen.setVisible(true);
                dispose();
            }
        });
        
        budgetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BudgetScreen budgetScreen = new BudgetScreen(username);
                budgetScreen.setVisible(true);
                dispose();
            }
        });
        
        reportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReportScreen reportScreen = new ReportScreen(username);
                reportScreen.setVisible(true);
                dispose();
            }
        });
        
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginScreen loginScreen = new LoginScreen();
                loginScreen.setVisible(true);
                dispose();
            }
        });
    }
}