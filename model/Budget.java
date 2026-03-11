package model;

public class Budget {
    private String username;
    private String category;
    private double limit;

    public Budget(String username, String category, double limit) {
        this.username = username;
        this.category = category;
        this.limit = limit;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getLimit() {
        return limit;
    }
    
    public double getBudgetLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return username + "," + category + "," + limit;
    }
}