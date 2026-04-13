package com.iispl.service;

import com.iispl.entity.Account;
import com.iispl.repository.AccountRepo;
import java.util.List;

public class AccountSericeImpl implements AccountService {

    private AccountRepo accountRepo;

    public AccountSericeImpl(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    @Override
    public void addAccount(Account account) {
        accountRepo.addAccount(account);
        System.out.println("Account added successfully.");
    }

    @Override
    public void displayAllAccounts() {
        List<Account> accounts = accountRepo.findAllAccounts();
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }
        for (Account a : accounts) System.out.println(a);
    }

    @Override
    public void displayActiveAccounts() {
        boolean found = false;
        for (Account a : accountRepo.findAllAccounts()) {
            if ("ACTIVE".equalsIgnoreCase(a.getStatus())) {
                System.out.println(a);
                found = true;
            }
        }
        if (!found) System.out.println("No active accounts found.");
    }

    @Override
    public void displaySavingsAccounts() {
        boolean found = false;
        for (Account a : accountRepo.findAllAccounts()) {
            if ("SAVINGS".equalsIgnoreCase(a.getAccountType())) {
                System.out.println(a);
                found = true;
            }
        }
        if (!found) System.out.println("No savings accounts found.");
    }
    
    public boolean validateAccount(String accountNumber) {
    	if(accountRepo.findAcc(accountNumber)!=null) {
    		return true;
    	}
    	return false;
    }
}
