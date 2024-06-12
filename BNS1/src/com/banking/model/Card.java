package com.banking.model;

import java.util.Date;

public class Card {
    private int id;
    private String cardNumber;
    private String accountNumber;
    private String pin;
    private Date expiryDate;
    private boolean blocked;

    // Constructors, getters, and setters

    public Card() {
    }

    public Card(int id, String cardNumber, String accountNumber, String pin, Date expiryDate, boolean blocked) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.expiryDate = expiryDate;
        this.blocked = blocked;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
}
