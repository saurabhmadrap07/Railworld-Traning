# Banking System

## Overview

This project is a simple console-based banking system implemented in Java. The system allows users to perform various banking operations such as registering new accounts, authenticating existing users, viewing mini statements, depositing and withdrawing money, transferring funds, and managing cards.

## Features

- **User Registration**: New users can register by providing their name, email, and phone number.
- **User Authentication**: Existing users can log in using their account number and PIN.
- **Change PIN**: Users can change their account PIN.
- **View Mini Statement**: Users can view the last 5 transactions.
- **Deposit**: Users can deposit money into their account.
- **Withdraw**: Users can withdraw money from their account.
- **Transfer**: Users can transfer funds to another account.
- **Card Operations**: Users can create new cards, change card PINs, and block cards.

```sql
#### Table Structure
### Accounts

CREATE TABLE accounts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    account_number VARCHAR(11) NOT NULL UNIQUE,
    pin VARCHAR(4) NOT NULL,
    balance DECIMAL(10, 2) NOT NULL,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone VARCHAR(15) NOT NULL
);

### Transactions
CREATE TABLE transactions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    account_number VARCHAR(11) NOT NULL,
    type VARCHAR(20) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (account_number) REFERENCES accounts(account_number)
);

### Cards
CREATE TABLE cards (
    id INT AUTO_INCREMENT PRIMARY KEY,
    account_number VARCHAR(11) NOT NULL,
    card_number VARCHAR(16) NOT NULL UNIQUE,
    pin VARCHAR(4) NOT NULL,
    blocked BOOLEAN NOT NULL DEFAULT FALSE,
    FOREIGN KEY (account_number) REFERENCES accounts(account_number)
);
