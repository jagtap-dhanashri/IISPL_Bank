package com.iispl.repository;

import java.util.List;

import com.iispl.entity.Transaction;

public interface TransactionRepo {
    public void addTransaction(Transaction transaction);
    public List<Transaction> findAllTransactions();
}
