package com.banking.service;

import com.banking.dao.TransactionDAO;
import com.banking.model.Transaction;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class TransactionService {
    private final TransactionDAO transactionDAO = new TransactionDAO();

    public void viewMiniStatement(String accountNumber) {
        int numberOfTransactions = 5; // Default to the last 5 transactions
        try {
            List<Transaction> transactions = transactionDAO.getLastNTransactions(accountNumber, numberOfTransactions);
            if (transactions.isEmpty()) {
                System.out.println("No transactions found.");
            } else {
                System.out.println("Last " + numberOfTransactions + " transactions:");
                for (Transaction transaction : transactions) {
                    System.out.println(transaction);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deposit(String accountNumber, double amount) {
        try {
            // Insert transaction record
            Transaction transaction = new Transaction(accountNumber, "DEPOSIT", amount, new Timestamp(System.currentTimeMillis()));
            transactionDAO.insertTransaction(transaction);
            System.out.println("Deposit successful.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void withdraw(String accountNumber, double amount) {
        try {
            // Insert transaction record
            Transaction transaction = new Transaction(accountNumber, "WITHDRAW", amount, new Timestamp(System.currentTimeMillis()));
            transactionDAO.insertTransaction(transaction);
//            System.out.println("Withdrawal successful.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void transfer(String fromAccountNumber, String toAccountNumber, double amount) {
        try {
            // Insert transaction records
            Transaction fromTransaction = new Transaction(fromAccountNumber, "TRANSFER_OUT", amount, new Timestamp(System.currentTimeMillis()));
            Transaction toTransaction = new Transaction(toAccountNumber, "TRANSFER_IN", amount, new Timestamp(System.currentTimeMillis()));
            transactionDAO.insertTransaction(fromTransaction);
            transactionDAO.insertTransaction(toTransaction);

//            System.out.println("Transfer successful.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
