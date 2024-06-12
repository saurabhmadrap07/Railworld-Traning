package com.banking.service;

import com.banking.dao.AccountDAO;
import com.banking.model.Account;

import java.sql.SQLException;
//import java.sql.Timestamp;
import java.util.Random;

public class AccountService {
    private final AccountDAO accountDAO = new AccountDAO();
    private final Random random = new Random();

    public boolean authenticateUser(String accountNumber, String pin) {
        try {
            Account account = accountDAO.getAccountByAccountNumber(accountNumber);
            if (account != null && account.getPin().equals(pin)) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void changePIN(String accountNumber, String currentPin, String newPin) {
        try {
            Account account = accountDAO.getAccountByAccountNumber(accountNumber);
            if (account != null && account.getPin().equals(currentPin)) {
                accountDAO.updatePIN(account.getId(), newPin);
                System.out.println("PIN changed successfully.");
            } else {
                System.out.println("Invalid current PIN.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deposit(String accountNumber, double amount) {
        try {
            Account account = accountDAO.getAccountByAccountNumber(accountNumber);
            if (account != null) {
                double newBalance = account.getBalance() + amount;
                accountDAO.updateBalance(account.getAccountNumber(), newBalance);

                System.out.println("Deposit successful. New balance: " + newBalance);
            } else {
                System.out.println("Account not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void withdraw(String accountNumber, double amount) {
        try {
            Account account = accountDAO.getAccountByAccountNumber(accountNumber);
            if (account != null) {
                if (account.getBalance() >= amount) {
                    double newBalance = account.getBalance() - amount;
                    accountDAO.updateBalance(account.getAccountNumber(), newBalance);
                    System.out.println("Withdrawal successful. New balance: " + newBalance);
                } else {
                    System.out.println("Insufficient balance.");
                }
            } else {
                System.out.println("Account not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String generateAccountNumber() {
        StringBuilder accountNumber = new StringBuilder();
        for (int i = 0; i < 11; i++) {
            accountNumber.append(random.nextInt(10));
        }
        return accountNumber.toString();
    }

    public void registerNewAccount(String name, String email, String phone) throws SQLException {
        String accountNumber = generateAccountNumber();
        String pin = String.format("%04d", random.nextInt(10000));
        double initialBalance = 0.0;

        Account account = new Account();
        account.setAccountNumber(accountNumber);
        account.setPin(pin);
        account.setBalance(initialBalance);
        account.setName(name);
        account.setEmail(email);
        account.setPhone(phone);

        accountDAO.insertAccount(account);

        System.out.println("Account created successfully!");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("PIN: " + pin);
    }

    public void transfer(String fromAccountNumber, String toAccountNumber, double transferAmount) {
        try {
            // Get account details for both sender and receiver
            Account fromAccount = accountDAO.getAccountByAccountNumber(fromAccountNumber);
            Account toAccount = accountDAO.getAccountByAccountNumber(toAccountNumber);

            // Check if both accounts exist
            if (fromAccount == null || toAccount == null) {
                System.out.println("Invalid account numbers.");
                return;
            }

            // Check if sender has enough balance for the transfer
            if (fromAccount.getBalance() < transferAmount) {
                System.out.println("Insufficient balance. Transfer failed.");
                return;
            }

            // Calculate new balances for sender and receiver
            double newFromBalance = fromAccount.getBalance() - transferAmount;
            double newToBalance = toAccount.getBalance() + transferAmount;

            // Update balances in the database
            accountDAO.updateBalance(fromAccount.getAccountNumber(), newFromBalance);
            accountDAO.updateBalance(toAccount.getAccountNumber(), newToBalance);

            // Print transfer success message
            System.out.println("Transfer successful.");

            // You can also log the transaction if required
            //transactionDAO.insertTransaction(new Transaction(fromAccount.getId(), toAccount.getId(), transferAmount, new Timestamp(System.currentTimeMillis())));
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Transfer failed. Please try again later.");
        }
    }

}
