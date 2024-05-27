package com.example.banking.service;

import com.example.banking.dao.AccountDAO;
import com.example.banking.model.Account;

import java.util.List;

public class AccountService {
    private AccountDAO accountDAO = new AccountDAO();

    public void createAccount(String holderName, double initialDeposit) {
        Account account = new Account();
        account.setHolderName(holderName);
        account.setBalance(initialDeposit);
        accountDAO.createAccount(account);
    }

    public Account getAccount(int accountNumber) {
        return accountDAO.getAccount(accountNumber);
    }

    public void updateAccount(int accountNumber, String holderName, double balance) {
        Account account = new Account(accountNumber, holderName, balance);
        accountDAO.updateAccount(account);
    }

    public void deleteAccount(int accountNumber) {
        accountDAO.deleteAccount(accountNumber);
    }

    public List<Account> getAllAccounts() {
        return accountDAO.getAllAccounts();
    }
}
