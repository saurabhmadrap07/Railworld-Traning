package com.example.banking.presentation;

import com.example.banking.model.Account;
import com.example.banking.service.AccountService;
import com.example.banking.service.TransactionService;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class BankingSystemApp {
    public static void main(String[] args) {
        AccountService accountService = new AccountService();
        TransactionService transactionService = new TransactionService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nBanking System Menu:");
            System.out.println("1. Create Account");
            System.out.println("2. View Account");
            System.out.println("3. Update Account");
            System.out.println("4. Delete Account");
            System.out.println("5. Deposit");
            System.out.println("6. Withdraw");
            System.out.println("7. Transfer");
            System.out.println("8. View All Accounts");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");
            int option;

            try {
                option = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear invalid input
                continue;
            }

            switch (option) {
                case 1:
                    createAccount(accountService, scanner);
                    break;
                case 2:
                    viewAccount(accountService, scanner);
                    break;
                case 3:
                    updateAccount(accountService, scanner);
                    break;
                case 4:
                    deleteAccount(accountService, scanner);
                    break;
                case 5:
                    deposit(transactionService, scanner);
                    break;
                case 6:
                    withdraw(transactionService, scanner);
                    break;
                case 7:
                    transfer(transactionService, scanner);
                    break;
                case 8:
                    viewAllAccounts(accountService);
                    break;
                case 9:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void createAccount(AccountService accountService, Scanner scanner) {
        System.out.print("Enter holder name: ");
        String holderName = scanner.next();
        double initialDeposit;

        while (true) {
            System.out.print("Enter initial deposit: ");
            try {
                initialDeposit = scanner.nextDouble();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid amount.");
                scanner.next(); // Clear invalid input
            }
        }

        accountService.createAccount(holderName, initialDeposit);
        System.out.println("Account created successfully!");
    }

    private static void viewAccount(AccountService accountService, Scanner scanner) {
        System.out.print("Enter account number: ");
        int accountNumber;

        try {
            accountNumber = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid account number.");
            scanner.next(); // Clear invalid input
            return;
        }

        Account account = accountService.getAccount(accountNumber);
        if (account != null) {
            System.out.println("Account Number: " + account.getAccountNumber());
            System.out.println("Holder Name: " + account.getHolderName());
            System.out.println("Balance: " + account.getBalance());
        } else {
            System.out.println("Account not found!");
        }
    }

    private static void updateAccount(AccountService accountService, Scanner scanner) {
        System.out.print("Enter account number: ");
        int accountNumber;

        try {
            accountNumber = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid account number.");
            scanner.next(); // Clear invalid input
            return;
        }

        System.out.print("Enter new holder name: ");
        String holderName = scanner.next();

        double balance;
        while (true) {
            System.out.print("Enter new balance: ");
            try {
                balance = scanner.nextDouble();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid amount.");
                scanner.next(); // Clear invalid input
            }
        }

        accountService.updateAccount(accountNumber, holderName, balance);
        System.out.println("Account updated successfully!");
    }

    private static void deleteAccount(AccountService accountService, Scanner scanner) {
        System.out.print("Enter account number: ");
        int accountNumber;

        try {
            accountNumber = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid account number.");
            scanner.next(); // Clear invalid input
            return;
        }

        accountService.deleteAccount(accountNumber);
        System.out.println("Account deleted successfully!");
    }

    private static void deposit(TransactionService transactionService, Scanner scanner) {
        System.out.print("Enter account number: ");
        int accountNumber;

        try {
            accountNumber = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid account number.");
            scanner.next(); // Clear invalid input
            return;
        }

        double depositAmount;
        while (true) {
            System.out.print("Enter deposit amount: ");
            try {
                depositAmount = scanner.nextDouble();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid amount.");
                scanner.next(); // Clear invalid input
            }
        }

        try {
            transactionService.deposit(accountNumber, depositAmount);
            System.out.println("Deposit successful!");
        } catch (SQLException e) {
            System.out.println("Error during deposit: " + e.getMessage());
        }
    }

    private static void withdraw(TransactionService transactionService, Scanner scanner) {
        System.out.print("Enter account number: ");
        int accountNumber;

        try {
            accountNumber = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid account number.");
            scanner.next(); // Clear invalid input
            return;
        }

        double withdrawAmount;
        while (true) {
            System.out.print("Enter withdrawal amount: ");
            try {
                withdrawAmount = scanner.nextDouble();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid amount.");
                scanner.next(); // Clear invalid input
            }
        }

        try {
            transactionService.withdraw(accountNumber, withdrawAmount);
            System.out.println("Withdrawal successful!");
        } catch (SQLException e) {
            System.out.println("Error during withdrawal: " + e.getMessage());
        }
    }

    private static void transfer(TransactionService transactionService, Scanner scanner) {
        System.out.print("Enter source account number: ");
        int fromAccountNumber;

        try {
            fromAccountNumber = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid account number.");
            scanner.next(); // Clear invalid input
            return;
        }

        System.out.print("Enter destination account number: ");
        int toAccountNumber;

        try {
            toAccountNumber = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid account number.");
            scanner.next(); // Clear invalid input
            return;
        }

        double transferAmount;
        while (true) {
            System.out.print("Enter transfer amount: ");
            try {
                transferAmount = scanner.nextDouble();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid amount.");
                scanner.next(); // Clear invalid input
            }
        }

        try {
            transactionService.transfer(fromAccountNumber, toAccountNumber, transferAmount);
            System.out.println("Transfer successful!");
        } catch (SQLException e) {
            System.out.println("Error during transfer: " + e.getMessage());
        }
    }

    private static void viewAllAccounts(AccountService accountService) {
        List<Account> accounts = accountService.getAllAccounts();
        for (Account account : accounts) {
            System.out.println("Account Number: " + account.getAccountNumber());
            System.out.println("Holder Name: " + account.getHolderName());
            System.out.println("Balance: " + account.getBalance());
            System.out.println("------------------------------");
        }
    }
}
