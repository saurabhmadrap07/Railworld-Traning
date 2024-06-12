package com.banking.service;

import com.banking.dao.CardDAO;
import com.banking.model.Card;

import java.sql.SQLException;
import java.util.Date;
import java.util.Random;

public class CardService {
    private final CardDAO cardDAO = new CardDAO();
    private final Random random = new Random();

    public void createNewCard(String accountNumber) {
        String cardNumber = generateCardNumber();
        String pin = String.format("%04d", random.nextInt(10000));
        Date expiryDate = new Date(System.currentTimeMillis() + (5L * 365 * 24 * 60 * 60 * 1000)); // 5 years from now

        Card card = new Card();
        card.setCardNumber(cardNumber);
        card.setAccountNumber(accountNumber);
        card.setPin(pin);
        card.setExpiryDate(expiryDate);
        card.setBlocked(false);

        try {
            cardDAO.insertCard(card);
            System.out.println("Card created successfully!");
            System.out.println("Card Number: " + cardNumber);
            System.out.println("PIN: " + pin);
            System.out.println("Expiry Date: " + expiryDate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void changeCardPIN(String cardNumber, String currentPin, String newPin) {
        try {
            Card card = cardDAO.getCardByNumber(cardNumber);
            if (card != null && card.getPin().equals(currentPin)) {
                cardDAO.updatePin(cardNumber, newPin);
                System.out.println("Card PIN changed successfully.");
            } else {
                System.out.println("Invalid current PIN.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void blockCard(String cardNumber) {
        try {
            Card card = cardDAO.getCardByNumber(cardNumber);
            if (card != null) {
                cardDAO.blockCard(cardNumber, true);
                System.out.println("Card blocked successfully.");
            } else {
                System.out.println("Card not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String generateCardNumber() {
        StringBuilder cardNumber = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            cardNumber.append(random.nextInt(10));
        }
        return cardNumber.toString();
    }
}
