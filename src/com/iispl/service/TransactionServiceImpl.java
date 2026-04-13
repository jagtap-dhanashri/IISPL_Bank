package com.iispl.service;

import java.math.BigInteger;
import java.util.List;

import com.iispl.entity.Account;
import com.iispl.entity.Transaction;
import com.iispl.repository.TransactionRepo;

public class TransactionServiceImpl implements TransactionService {

    private TransactionRepo transactionRepo;
    private AccountService accountService;

    public TransactionServiceImpl(TransactionRepo transactionRepo, AccountService accountService) {
        this.transactionRepo = transactionRepo;
        this.accountService = accountService;
    }

    @Override
    public void addTransaction(Transaction transaction) {
        if (transactionRepo.existsTransactionId(transaction.getTransactionId())) {
            System.out.println("Duplicate transaction ID.");
            transaction.setStatus("FAILED");
            return;
        }
        if (accountService.validateAccount(transaction.getFromAccount()) == false) {
            System.out.println("From Account is not available.");
            logFailed(transaction, false, false);
            return;
        }
        if (accountService.validateAccount(transaction.getToAccount()) == false) {
            System.out.println("To Account is not available.");
            logFailed(transaction, true, false);
            return;
        }

        if (transaction.getFromAccount().equals(transaction.getToAccount())) {
            System.out.println("From and To account cannot be same.");
            logFailed(transaction, true, true);
            return;
        }

        if (transaction.getAmount().compareTo(BigInteger.ZERO) <= 0) {
            System.out.println("Invalid transaction amount.");
            logFailed(transaction, true, true);
            return;
        }

        Account fromAccount = accountService.getAccountByNumber(transaction.getFromAccount());
        Account toAccount = accountService.getAccountByNumber(transaction.getToAccount());

        if (fromAccount.getBalance().compareTo(transaction.getAmount()) < 0) {
            System.out.println("Insufficient balance in from account.");
            logFailed(transaction, true, true);
            return;
        }
        
        if(fromAccount.getStatus().equalsIgnoreCase("INACTIVE")){
            System.out.println("From Account is inactive.");
            logFailed(transaction, true, true);
            return;
        }

        if(toAccount.getStatus().equalsIgnoreCase("INACTIVE")){
            System.out.println("To Account is inactive.");
            logFailed(transaction, true, true);
            return;
        }
        fromAccount.setBalance(fromAccount.getBalance().subtract(transaction.getAmount()));
        toAccount.setBalance(toAccount.getBalance().add(transaction.getAmount()));

        accountService.addTransaction(transaction.getFromAccount(), transaction);
        accountService.addTransaction(transaction.getToAccount(), transaction);
        transaction.setStatus("SUCCESS");
        transactionRepo.addTransaction(transaction);

        System.out.println("Transaction added successfully.");
    }

    @Override
    public void displayAllTransactions() {
        List<Transaction> transactions = transactionRepo.findAllTransactions();
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    @Override
    public void displayHighValueTransactions(BigInteger threshold) {
        List<Transaction> transactions = transactionRepo.findAllTransactions();
        boolean found = false;

        for (Transaction transaction : transactions) {
            if (transaction.getAmount().compareTo(threshold) > 0) {
                System.out.println(transaction);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No high-value transactions found above " + threshold);
        }
    }

    @Override
    public void displayFailedTransactions() {
        List<Transaction> transactions = transactionRepo.findAllTransactions();
        boolean found = false;
        for (Transaction transaction : transactions) {
            if ("FAILED".equalsIgnoreCase(transaction.getStatus())) {
                System.out.println(transaction);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No failed transactions found.");
        }
    }

    private void logFailed(Transaction transaction, boolean addToFrom, boolean addToTo) {
        transaction.setStatus("FAILED");
        if (addToFrom) {
            accountService.addTransaction(transaction.getFromAccount(), transaction);
        }
        if (addToTo) {
            accountService.addTransaction(transaction.getToAccount(), transaction);
        }
        transactionRepo.addTransaction(transaction);
    }
}
