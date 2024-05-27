package com.example.banking.model;

import java.sql.Timestamp;

public class Transaction {
    private int transactionId;
    private int accountNumber;
    private double amount;
    private String type;
    private Timestamp transactionDate;

    // Constructor without transactionId
    public Transaction(int accountNumber, double amount, String type, Timestamp transactionDate) {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.type = type;
        this.transactionDate = transactionDate;
    }

    // Getter and Setter for transactionId
    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    // Getter and Setter for accountNumber
    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    // Getter and Setter for amount
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    // Getter and Setter for type
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // Getter and Setter for transactionDate
    public Timestamp getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Timestamp transactionDate) {
        this.transactionDate = transactionDate;
    }
}
