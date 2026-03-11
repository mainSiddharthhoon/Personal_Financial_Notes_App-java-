package ui;

import model.Budget;
import service.BudgetService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class BudgetScreen extends JFrame {
    private JTextField categoryField, limitField;
    private JTextArea budgetsArea, alertsArea;
    private JButton saveButton, backButton;
    private String username;
    private BudgetService budgetService;

    public BudgetScreen(String username) {
        this.username = username;
        this.budgetService = new BudgetService();
        
        setTitle("Budget Management");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Input panel
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.add(new JLabel("Category:"));
        categoryField = new JTextField();
        inputPanel.add(categoryField);
        
        inputPanel.add(new JLabel("Budget Limit:"));
        limitField = new JTextField();
        inputPanel.add(limitField);
        
        saveButton = new JButton("Save Budget");
        inputPanel.add(saveButton);
        
        backButton = new JButton("Back to Dashboard");
        inputPanel.add(backButton);
        
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        
        // Budgets display
        JPanel displayPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        
        budgetsArea = new JTextArea();
        budgetsArea.setEditable(false);
        JScrollPane budgetsScroll = new JScrollPane(budgetsArea);
        budgetsScroll.setBorder(BorderFactory.createTitledBorder("Your Budgets"));
        displayPanel.add(budgetsScroll);
        
        alertsArea = new JTextArea();
        alertsArea.setEditable(false);
        JScrollPane alertsScroll = new JScrollPane(alertsArea);
        alertsScroll.setBorder(BorderFactory.createTitledBorder("Budget Alerts"));
        displayPanel.add(alertsScroll);
        
        mainPanel.add(displayPanel, BorderLayout.CENTER);
        
        add(mainPanel);
        
        // Load budgets and alerts
        loadBudgets();
        loadAlerts();
        
        // Add action listeners
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveBudget();
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
    
    private void saveBudget() {
        String category = categoryField.getText().trim();
        String limitStr = limitField.getText().trim();
        
        if (category.isEmpty() || limitStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Please enter both category and budget limit", 
                "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            double limit = Double.parseDouble(limitStr);
            if (limit <= 0) {
                JOptionPane.showMessageDialog(this, 
                    "Budget limit must be greater than zero", 
                    "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            budgetService.addBudget(username, category, limit);
            
            JOptionPane.showMessageDialog(this, 
                "Budget saved successfully!", 
                "Success", JOptionPane.INFORMATION_MESSAGE);
            
            // Clear fields and reload budgets
            categoryField.setText("");
            limitField.setText("");
            loadBudgets();
            loadAlerts();
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Please enter a valid number for budget limit", 
                "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void loadBudgets() {
        List<Budget> budgets = budgetService.getUserBudgets(username);
        StringBuilder sb = new StringBuilder();
        sb.append("Category\t\tBudget Limit\n");
        sb.append("----------------------------------\n");
        
        for (Budget budget : budgets) {
            sb.append(budget.getCategory()).append("\t\t$");
            sb.append(budget.getLimit()).append("\n");
        }
        
        budgetsArea.setText(sb.toString());
    }
    
    private void loadAlerts() {
        Map<String, Double> alerts = budgetService.getBudgetAlerts(username);
        StringBuilder sb = new StringBuilder();
        
        if (alerts.isEmpty()) {
            sb.append("No budget alerts at this time.");
        } else {
            sb.append("Category\t\tStatus\n");
            sb.append("----------------------------------\n");
            
            for (Map.Entry<String, Double> entry : alerts.entrySet()) {
                sb.append(entry.getKey()).append("\t\t");
                double percentageUsed = entry.getValue();
                
                if (percentageUsed > 100) {
                    sb.append("EXCEEDED: ").append(String.format("%.1f", percentageUsed)).append("% used");
                } else {
                    sb.append(String.format("%.1f", percentageUsed)).append("% used");
                }
                sb.append("\n");
            }
        }
        
        alertsArea.setText(sb.toString());
    }
}