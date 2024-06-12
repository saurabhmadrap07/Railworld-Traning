package com.banking.dao;

import com.banking.model.Card;
import com.banking.util.DatabaseUtil;

import java.sql.*;
import java.util.Date;

public class CardDAO {
    private static final String INSERT_CARD = "INSERT INTO cards (card_number, account_number, pin, expiry_date, blocked) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_PIN = "UPDATE cards SET pin = ? WHERE card_number = ?";
    private static final String BLOCK_CARD = "UPDATE cards SET blocked = ? WHERE card_number = ?";
    private static final String SELECT_CARD_BY_NUMBER = "SELECT * FROM cards WHERE card_number = ?";

    public void insertCard(Card card) throws SQLException {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CARD)) {
            preparedStatement.setString(1, card.getCardNumber());
            preparedStatement.setString(2, card.getAccountNumber());
            preparedStatement.setString(3, card.getPin());
            preparedStatement.setDate(4, new java.sql.Date(card.getExpiryDate().getTime()));
            preparedStatement.setBoolean(5, card.isBlocked());
            preparedStatement.executeUpdate();
        }
    }

    public void updatePin(String cardNumber, String newPin) throws SQLException {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PIN)) {
            preparedStatement.setString(1, newPin);
            preparedStatement.setString(2, cardNumber);
            preparedStatement.executeUpdate();
        }
    }

    public void blockCard(String cardNumber, boolean blocked) throws SQLException {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(BLOCK_CARD)) {
            preparedStatement.setBoolean(1, blocked);
            preparedStatement.setString(2, cardNumber);
            preparedStatement.executeUpdate();
        }
    }

    public Card getCardByNumber(String cardNumber) throws SQLException {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CARD_BY_NUMBER)) {
            preparedStatement.setString(1, cardNumber);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Card card = new Card();
                card.setId(resultSet.getInt("id"));
                card.setCardNumber(resultSet.getString("card_number"));
                card.setAccountNumber(resultSet.getString("account_number"));
                card.setPin(resultSet.getString("pin"));
                card.setExpiryDate(resultSet.getDate("expiry_date"));
                card.setBlocked(resultSet.getBoolean("blocked"));
                return card;
            }
        }
        return null;
    }
}
