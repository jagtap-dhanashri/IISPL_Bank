package com.iispl.service;

import com.iispl.entity.Account;
import com.iispl.entity.Transaction;

public interface AccountService {
    void addAccount(Account account);
    void displayAllAccounts();
    void displayActiveAccounts();
    void displaySavingsAccounts();
    boolean validateAccount(String accountNumber);
    Account getAccountByNumber(String accountNumber);
    void addTransaction(String accountNumber, Transaction transaction);
}
