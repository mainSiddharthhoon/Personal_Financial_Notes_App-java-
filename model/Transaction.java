package model;

public class Transaction {
    private String username;
    private String date;
    private String time;
    private double amount;
    private String purpose;

    public Transaction(String username, String date, String time, double amount, String purpose) {
        this.username = username;
        this.date = date;
        this.time = time;
        this.amount = amount;
        this.purpose = purpose;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    @Override
    public String toString() {
        return username + "," + date + "," + time + "," + amount + "," + purpose;
    }
}