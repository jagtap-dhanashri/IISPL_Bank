package com.iispl.service;

import com.iispl.entity.Account;

public interface AccountService {
    void addAccount(Account account);
    void displayAllAccounts();
    void displayActiveAccounts();
    void displaySavingsAccounts();
    boolean validateAccount(String accountNumber);
}
