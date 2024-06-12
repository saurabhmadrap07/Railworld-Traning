package com.banking.dao;

import com.banking.model.Account;
import com.banking.util.DatabaseUtil;

import java.sql.*;

public class AccountDAO {
    private static final String INSERT_ACCOUNT = "INSERT INTO accounts (account_number, pin, balance, name, email, phone) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ACCOUNT_BY_NUMBER = "SELECT * FROM accounts WHERE account_number = ?";
    private static final String UPDATE_PIN = "UPDATE accounts SET pin = ? WHERE id = ?";
    private static final String UPDATE_BALANCE = "UPDATE accounts SET balance = ? WHERE id = ?";

    public void insertAccount(Account account) throws SQLException {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ACCOUNT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, account.getAccountNumber());
            preparedStatement.setString(2, account.getPin());
            preparedStatement.setDouble(3, account.getBalance());
            preparedStatement.setString(4, account.getName());
            preparedStatement.setString(5, account.getEmail());
            preparedStatement.setString(6, account.getPhone());
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    account.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public Account getAccountByAccountNumber(String accountNumber) throws SQLException {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ACCOUNT_BY_NUMBER)) {
            preparedStatement.setString(1, accountNumber);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Account account = new Account();
                    account.setId(resultSet.getInt("id"));
                    account.setAccountNumber(resultSet.getString("account_number"));
                    account.setPin(resultSet.getString("pin"));
                    account.setBalance(resultSet.getDouble("balance"));
                    account.setName(resultSet.getString("name"));
                    account.setEmail(resultSet.getString("email"));
                    account.setPhone(resultSet.getString("phone"));
                    return account;
                } else {
                    return null;
                }
            }
        }
    }

    public void updatePIN(int accountId, String newPin) throws SQLException {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PIN)) {
            preparedStatement.setString(1, newPin);
            preparedStatement.setInt(2, accountId);
            preparedStatement.executeUpdate();
        }
    }

    public void updateBalance(String accountNumber, double newBalance) throws SQLException {
        String UPDATE_BALANCE_QUERY = "UPDATE accounts SET balance = ? WHERE account_number = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BALANCE_QUERY)) {
            preparedStatement.setDouble(1, newBalance);
            preparedStatement.setString(2, accountNumber);
            preparedStatement.executeUpdate();
        }
    }

}
