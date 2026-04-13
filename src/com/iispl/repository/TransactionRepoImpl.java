package com.iispl.repository;

import java.util.ArrayList;
import java.util.List;

import com.iispl.entity.Transaction;

public class TransactionRepoImpl implements TransactionRepo{

    private List<Transaction> transactions;

    public TransactionRepoImpl() {
        this.transactions = new ArrayList<>();
    }
    
    @Override
    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    @Override
    public List<Transaction> findAllTransactions() {
        return this.transactions;
    }

    @Override
    public boolean existsTransactionId(String transactionId) {
        for (Transaction t : transactions) {
            if (t.getTransactionId().equals(transactionId)) {
                return true;
            }
        }
        return false;
    }
}
