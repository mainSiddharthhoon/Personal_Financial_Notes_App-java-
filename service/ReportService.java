package service;

import dao.TransactionDAO;
import model.Transaction;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportService {
    private TransactionDAO transactionDAO;

    public ReportService() {
        this.transactionDAO = new TransactionDAO();
    }

    public Map<String, Double> getSpendingByCategory(String username) {
        List<Transaction> transactions = transactionDAO.getTransactionsByUsername(username);
        Map<String, Double> categorySpending = new HashMap<>();
        
        for (Transaction transaction : transactions) {
            String category = transaction.getPurpose();
            double amount = transaction.getAmount();
            
            if (categorySpending.containsKey(category)) {
                categorySpending.put(category, categorySpending.get(category) + amount);
            } else {
                categorySpending.put(category, amount);
            }
        }
        
        return categorySpending;
    }

    public List<Transaction> getMonthlySummary(String username, String month) {
        List<Transaction> allTransactions = transactionDAO.getTransactionsByUsername(username);
        List<Transaction> monthlyTransactions = new java.util.ArrayList<>();
        
        for (Transaction transaction : allTransactions) {
            if (transaction.getDate().contains(month)) {
                monthlyTransactions.add(transaction);
            }
        }
        
        return monthlyTransactions;
    }
}