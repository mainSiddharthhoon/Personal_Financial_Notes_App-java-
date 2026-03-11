package service;

import dao.BudgetDAO;
import dao.TransactionDAO;
import model.Budget;
import model.Transaction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BudgetService {
    private BudgetDAO budgetDAO;
    private TransactionDAO transactionDAO;

    public BudgetService() {
        this.budgetDAO = new BudgetDAO();
        this.transactionDAO = new TransactionDAO();
    }

    public boolean addBudget(String username, String category, double budgetLimit) {
        Budget budget = new Budget(username, category, budgetLimit);
        return budgetDAO.saveBudget(budget);
    }

    public List<Budget> getUserBudgets(String username) {
        return budgetDAO.getBudgetsByUsername(username);
    }

    public Map<String, Double> getBudgetAlerts(String username) {
        List<Budget> budgets = budgetDAO.getBudgetsByUsername(username);
        List<Transaction> transactions = transactionDAO.getTransactionsByUsername(username);
        Map<String, Double> alerts = new HashMap<>();

        for (Budget budget : budgets) {
            double totalSpent = 0;
            for (Transaction transaction : transactions) {
                if (transaction.getPurpose().equals(budget.getCategory())) {
                    totalSpent += transaction.getAmount();
                }
            }
            
            // Calculate percentage used (totalSpent / budgetLimit * 100)
            double percentageUsed = (totalSpent / budget.getBudgetLimit()) * 100;
            
            // Add all budgets to alerts with their percentage used
            alerts.put(budget.getCategory(), percentageUsed);
        }
        
        return alerts;
    }
}