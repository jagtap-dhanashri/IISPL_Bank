package com.iispl.repository;

import com.iispl.entity.Account;
import java.util.ArrayList;
import java.util.List;

public class AccountRepoImpl implements AccountRepo{

    private List<Account> accounts = new ArrayList<>();

    @Override
    public void addAccount(Account account) {
        accounts.add(account);
    }

    @Override
    public List<Account> findAllAccounts() {
        return accounts;
    }
    
    public Account findAcc(String accountNumber) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber().equals(accountNumber)) {
                return acc;
            }
        }
        return null; 
    }
}
