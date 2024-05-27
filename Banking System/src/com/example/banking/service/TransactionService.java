package com.example.banking.service;

import com.example.banking.dao.AccountDAO;
import com.example.banking.dao.TransactionDAO;
import com.example.banking.model.Transaction;

import java.sql.SQLException;
import java.sql.Timestamp;

public class TransactionService {
    private final AccountDAO accountDAO;
    private final TransactionDAO transactionDAO;

    public TransactionService() {
        this.accountDAO = new AccountDAO();
        this.transactionDAO = new TransactionDAO();
    }

    public void deposit(int accountNumber, double amount) throws SQLException {
        accountDAO.updateBalance(accountNumber, amount); // Assuming this method adds amount to balance
        System.out.println("Depositing " + amount + " to account " + accountNumber);

        Transaction transaction = new Transaction(accountNumber, amount, "Deposit", new Timestamp(System.currentTimeMillis()));
        transactionDAO.createTransaction(transaction);
    }

    public void withdraw(int accountNumber, double amount) throws SQLException {
        accountDAO.updateBalance(accountNumber, -amount); // Assuming this method subtracts amount from balance
        System.out.println("Withdrawing " + amount + " from account " + accountNumber);

        Transaction transaction = new Transaction(accountNumber, amount, "Withdraw", new Timestamp(System.currentTimeMillis()));
        transactionDAO.createTransaction(transaction);
    }

    public void transfer(int fromAccountNumber, int toAccountNumber, double amount) throws SQLException {
        accountDAO.updateBalance(fromAccountNumber, -amount); // Subtract from source account
        accountDAO.updateBalance(toAccountNumber, amount); // Add to destination account
        System.out.println("Transferring " + amount + " from account " + fromAccountNumber + " to account " + toAccountNumber);

        Transaction withdrawTransaction = new Transaction(fromAccountNumber, amount, "Transfer Out", new Timestamp(System.currentTimeMillis()));
        transactionDAO.createTransaction(withdrawTransaction);

        Transaction depositTransaction = new Transaction(toAccountNumber, amount, "Transfer In", new Timestamp(System.currentTimeMillis()));
        transactionDAO.createTransaction(depositTransaction);
    }
}
