package service;

import dao.TransactionDAO;
import model.Transaction;
import java.util.List;

public class TransactionService {
    private TransactionDAO transactionDAO;

    public TransactionService() {
        this.transactionDAO = new TransactionDAO();
    }

    public boolean addTransaction(String username, String date, String time, double amount, String purpose) {
        if (amount <= 0) {
            return false;
        }
        Transaction transaction = new Transaction(username, date, time, amount, purpose);
        return transactionDAO.saveTransaction(transaction);
    }

    public List<Transaction> getUserTransactions(String username) {
        return transactionDAO.getTransactionsByUsername(username);
    }
}