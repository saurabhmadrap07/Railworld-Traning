package com.banking;

import com.banking.service.AccountService;
import com.banking.service.CardService;
import com.banking.service.TransactionService;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AccountService accountService = new AccountService();
        TransactionService transactionService = new TransactionService();
        CardService cardService = new CardService();

        while (true) {
            System.out.println("Welcome to the Banking System");
            System.out.println("1. New User");
            System.out.println("2. Existing User");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    scanner.nextLine(); // Consume newline left-over
                    String name = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.next();
                    System.out.print("Enter phone: ");
                    String phone = scanner.next();

                    try {
                        accountService.registerNewAccount(name, email, phone);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    System.out.print("Enter account number: ");
                    String accountNumber = scanner.next();
                    System.out.print("Enter PIN: ");
                    String pin = scanner.next();

                    if (accountService.authenticateUser(accountNumber, pin)) {
                        System.out.println("1. Change PIN");
                        System.out.println("2. View Mini Statement");
                        System.out.println("3. Deposit");
                        System.out.println("4. Withdraw");
                        System.out.println("5. Transfer");
                        System.out.println("6. Card Operations");
                        System.out.print("Enter your choice: ");
                        int subChoice = scanner.nextInt();
                        String cardNumber; // Declare cardNumber here
                        switch (subChoice) {
                            case 1:
                                System.out.print("Enter new PIN: ");
                                String newPin = scanner.next();
                                accountService.changePIN(accountNumber, pin, newPin);
                                break;
                            case 2:
                                transactionService.viewMiniStatement(accountNumber);
                                break;
                            case 3:
                                System.out.print("Enter amount to deposit: ");
                                double depositAmount = scanner.nextDouble();
                                accountService.deposit(accountNumber, depositAmount);
                                transactionService.deposit(accountNumber, depositAmount);
                                break;
                            case 4:
                                System.out.print("Enter amount to withdraw: ");
                                double withdrawAmount = scanner.nextDouble();
                                accountService.withdraw(accountNumber, withdrawAmount);
                                transactionService.withdraw(accountNumber, withdrawAmount);
                                break;
                            case 5:
                                System.out.print("Enter destination account number: ");
                                String toAccountNumber = scanner.next();
                                System.out.print("Enter amount to transfer: ");
                                double transferAmount = scanner.nextDouble();
                                accountService.transfer(accountNumber, toAccountNumber, transferAmount);
                                transactionService.transfer(accountNumber, toAccountNumber, transferAmount);
                                break;
                            case 6:
                                System.out.println("1. Create New Card");
                                System.out.println("2. Change Card PIN");
                                System.out.println("3. Block Card");
                                System.out.print("Enter your choice: ");
                                int cardChoice = scanner.nextInt();

                                switch (cardChoice) {
                                    case 1:
                                        cardService.createNewCard(accountNumber);
                                        break;
                                    case 2:
                                        System.out.print("Enter card number: ");
                                        cardNumber = scanner.next();
                                        System.out.print("Enter current card PIN: ");
                                        String currentCardPin = scanner.next();
                                        System.out.print("Enter new card PIN: ");
                                        String newCardPin = scanner.next();
                                        cardService.changeCardPIN(cardNumber, currentCardPin, newCardPin);
                                        break;
                                    case 3:
                                        System.out.print("Enter card number: ");
                                        cardNumber = scanner.next();
                                        cardService.blockCard(cardNumber);
                                        break;
                                    default:
                                        System.out.println("Invalid choice.");
                                }
                                break;
                            default:
                                System.out.println("Invalid choice.");
                        }
                    } else {
                        System.out.println("Authentication failed. Invalid account number or PIN.");
                    }
                    break;
                case 3:
                    System.out.println("Thank you for using the Banking System.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
