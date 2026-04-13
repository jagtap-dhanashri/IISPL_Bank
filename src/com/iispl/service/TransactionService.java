package com.iispl.service;

import java.math.BigInteger;

import com.iispl.entity.Transaction;

public interface TransactionService {

    public void addTransaction(Transaction transaction);
    public void displayAllTransactions();
    public void displayHighValueTransactions(BigInteger threshold);
    public void displayFailedTransactions();
}
