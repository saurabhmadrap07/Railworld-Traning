package com.banking.dao;

import com.banking.model.Transaction;
import com.banking.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {
    private static final String INSERT_TRANSACTION = "INSERT INTO transactions (account_number, type, amount, transaction_date) VALUES (?, ?, ?, ?)";
    private static final String SELECT_LAST_N_TRANSACTIONS = "SELECT * FROM transactions WHERE account_number = ? ORDER BY transaction_date DESC LIMIT ?";

    public void insertTransaction(Transaction transaction) throws SQLException {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TRANSACTION)) {
            preparedStatement.setString(1, transaction.getAccountNumber());
            preparedStatement.setString(2, transaction.getType());
            preparedStatement.setDouble(3, transaction.getAmount());
            preparedStatement.setTimestamp(4, transaction.getTransactionDate());
            preparedStatement.executeUpdate();
        }
    }

    public List<Transaction> getLastNTransactions(String accountNumber, int n) throws SQLException {
        List<Transaction> transactions = new ArrayList<>();
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LAST_N_TRANSACTIONS)) {
            preparedStatement.setString(1, accountNumber);
            preparedStatement.setInt(2, n);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Transaction transaction = new Transaction();
                    transaction.setId(resultSet.getInt("id"));
                    transaction.setAccountNumber(resultSet.getString("account_number"));
                    transaction.setType(resultSet.getString("type"));
                    transaction.setAmount(resultSet.getDouble("amount"));
                    transaction.setTransactionDate(resultSet.getTimestamp("transaction_date"));
                    transactions.add(transaction);
                }
            }
        }
        return transactions;
    }
}
