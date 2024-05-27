package com.example.banking.dao;

import com.example.banking.model.Account;
import com.example.banking.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {
    public void createAccount(Account account) {
        String query = "INSERT INTO accounts (holder_name, balance, created_at) VALUES (?, ?, NOW())";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, account.getHolderName());
            preparedStatement.setDouble(2, account.getBalance());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Account getAccount(int accountNumber) {
        String query = "SELECT * FROM accounts WHERE account_number = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, accountNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Account(
                        resultSet.getInt("account_number"),
                        resultSet.getString("holder_name"),
                        resultSet.getDouble("balance")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateAccount(Account account) {
        String query = "UPDATE accounts SET holder_name = ?, balance = ? WHERE account_number = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, account.getHolderName());
            preparedStatement.setDouble(2, account.getBalance());
            preparedStatement.setInt(3, account.getAccountNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAccount(int accountNumber) {
        String query = "DELETE FROM accounts WHERE account_number = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, accountNumber);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        String query = "SELECT * FROM accounts";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                accounts.add(new Account(
                        resultSet.getInt("account_number"),
                        resultSet.getString("holder_name"),
                        resultSet.getDouble("balance")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    public void updateBalance(int accountNumber, double amount) {
        String query = "UPDATE accounts SET balance = balance + ? WHERE account_number = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDouble(1, amount);
            preparedStatement.setInt(2, accountNumber);
            int rowsUpdated = preparedStatement.executeUpdate();
            System.out.println("Balance updated for account " + accountNumber + ": " + amount + " (Rows affected: " + rowsUpdated + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
