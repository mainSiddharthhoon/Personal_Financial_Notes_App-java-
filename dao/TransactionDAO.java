package dao;

import model.Transaction;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {
    private static final String FILE_PATH = "data/transactions.txt";

    public boolean saveTransaction(Transaction transaction) {
        FileUtil.writeToFile(FILE_PATH, transaction.toString(), true);
        return true;
    }

    public List<Transaction> getTransactionsByUsername(String username) {
        List<String> lines = FileUtil.readFromFile(FILE_PATH);
        List<Transaction> transactions = new ArrayList<>();
        
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length == 5 && parts[0].equals(username)) {
                Transaction transaction = new Transaction(
                    parts[0], parts[1], parts[2], 
                    Double.parseDouble(parts[3]), parts[4]
                );
                transactions.add(transaction);
            }
        }
        
        return transactions;
    }
}