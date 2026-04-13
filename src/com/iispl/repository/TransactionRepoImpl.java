package com.iispl.repository;

import java.util.ArrayList;
import java.util.List;

import com.iispl.entity.Transaction;

public class TransactionRepoImpl implements TransactionRepo{

    private List<Transaction> transactions;

    public TransactionRepoImpl() {
        this.transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    public List<Transaction> findAllTransactions() {
        return this.transactions;
    }
}
