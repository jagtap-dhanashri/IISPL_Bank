package com.iispl.repository;

import com.iispl.entity.Account;
import java.util.List;

public interface AccountRepo {
    void addAccount(Account account);

    List<Account> findAllAccounts();
    
    public Account findAcc(String accountNumber);
}
