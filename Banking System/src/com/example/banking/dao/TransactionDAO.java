package com.example.banking.dao;

import com.example.banking.model.Transaction;
import com.example.banking.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionDAO {

    public void createTransaction(Transaction transaction) throws SQLException {
        String query = "INSERT INTO transactions (account_number, amount, type, transaction_date) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, transaction.getAccountNumber());
            statement.setDouble(2, transaction.getAmount());
            statement.setString(3, transaction.getType());
            statement.setTimestamp(4, transaction.getTransactionDate());

            int rowsInserted = statement.executeUpdate();
            System.out.println("Transaction created: " + transaction + " (Rows affected: " + rowsInserted + ")");
        }
    }
}
