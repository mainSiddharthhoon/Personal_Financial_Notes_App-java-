package ui;

import model.Transaction;
import service.ReportService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportScreen extends JFrame {
    private JTextArea categoryReportArea, monthlyReportArea;
    private JButton backButton;
    private String username;
    private ReportService reportService;

    public ReportScreen(String username) {
        this.username = username;
        this.reportService = new ReportService();
        
        setTitle("Financial Reports");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Reports display
        JPanel reportsPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        
        categoryReportArea = new JTextArea();
        categoryReportArea.setEditable(false);
        JScrollPane categoryScroll = new JScrollPane(categoryReportArea);
        categoryScroll.setBorder(BorderFactory.createTitledBorder("Spending by Category"));
        reportsPanel.add(categoryScroll);
        
        monthlyReportArea = new JTextArea();
        monthlyReportArea.setEditable(false);
        JScrollPane monthlyScroll = new JScrollPane(monthlyReportArea);
        monthlyScroll.setBorder(BorderFactory.createTitledBorder("Monthly Summary"));
        reportsPanel.add(monthlyScroll);
        
        mainPanel.add(reportsPanel, BorderLayout.CENTER);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        backButton = new JButton("Back to Dashboard");
        buttonPanel.add(backButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
        
        // Load reports
        loadCategoryReport();
        loadMonthlyReport();
        
        // Add action listeners
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DashboardScreen dashboard = new DashboardScreen(username);
                dashboard.setVisible(true);
                dispose();
            }
        });
    }
    
    private void loadCategoryReport() {
        Map<String, Double> categorySpending = reportService.getSpendingByCategory(username);
        StringBuilder sb = new StringBuilder();
        
        if (categorySpending.isEmpty()) {
            sb.append("No transaction data available.");
        } else {
            sb.append("Category\t\tTotal Spending\n");
            sb.append("----------------------------------\n");
            
            double total = 0;
            for (Map.Entry<String, Double> entry : categorySpending.entrySet()) {
                sb.append(entry.getKey()).append("\t\t$");
                sb.append(String.format("%.2f", entry.getValue())).append("\n");
                total += entry.getValue();
            }
            
            sb.append("----------------------------------\n");
            sb.append("Total\t\t$").append(String.format("%.2f", total));
        }
        
        categoryReportArea.setText(sb.toString());
    }
    
    private void loadMonthlyReport() {
        // Get current month in format YYYY-MM
        String currentMonth = java.time.LocalDate.now().toString().substring(0, 7);
        List<Transaction> monthlyTransactions = reportService.getMonthlySummary(username, currentMonth);
        
        // Convert to map for display
        Map<String, Double> monthlySummary = new HashMap<>();
        double monthlyTotal = 0;
        for (Transaction t : monthlyTransactions) {
            monthlyTotal += t.getAmount();
        }
        monthlySummary.put(currentMonth, monthlyTotal);
        StringBuilder sb = new StringBuilder();
        
        if (monthlySummary.isEmpty()) {
            sb.append("No transaction data available.");
        } else {
            sb.append("Month\t\tTotal Spending\n");
            sb.append("----------------------------------\n");
            
            double total = 0;
            for (Map.Entry<String, Double> entry : monthlySummary.entrySet()) {
                sb.append(entry.getKey()).append("\t\t$");
                sb.append(String.format("%.2f", entry.getValue())).append("\n");
                total += entry.getValue();
            }
            
            sb.append("----------------------------------\n");
            sb.append("Total\t\t$").append(String.format("%.2f", total));
        }
        
        monthlyReportArea.setText(sb.toString());
    }
}