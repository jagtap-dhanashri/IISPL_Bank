package com.iispl.service;

import java.math.BigDecimal;


import com.iispl.entity.Transaction;

public interface TransactionService {

    public void addTransaction(Transaction transaction);
    public void displayAllTransactions();
    public void displayHighValueTransactions(BigDecimal threshold);
    public void displayFailedTransactions();
}
