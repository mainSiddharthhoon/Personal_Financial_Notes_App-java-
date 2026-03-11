package dao;

import model.Budget;
import java.util.ArrayList;
import java.util.List;

public class BudgetDAO {
    private static final String FILE_PATH = "data/budgets.txt";

    public boolean saveBudget(Budget budget) {
        FileUtil.writeToFile(FILE_PATH, budget.toString(), true);
        return true;
    }

    public List<Budget> getBudgetsByUsername(String username) {
        List<String> lines = FileUtil.readFromFile(FILE_PATH);
        List<Budget> budgets = new ArrayList<>();
        
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length == 3 && parts[0].equals(username)) {
                Budget budget = new Budget(
                    parts[0], parts[1], Double.parseDouble(parts[2])
                );
                budgets.add(budget);
            }
        }
        
        return budgets;
    }
}