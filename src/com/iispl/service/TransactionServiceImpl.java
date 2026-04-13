package com.iispl.service;

import java.math.BigDecimal;

import java.util.List;

import com.iispl.entity.Account;
import com.iispl.entity.Transaction;
import com.iispl.exceptions.BankException;
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
            transaction.setStatus("FAILED");
            throw new BankException("Duplicate transaction ID.");
        }
        if (accountService.validateAccount(transaction.getFromAccount()) == false) {
            logFailed(transaction, false, false);
            throw new BankException("From Account is not available.");
        }
        if (accountService.validateAccount(transaction.getToAccount()) == false) {
            logFailed(transaction, true, false);
            throw new BankException("To Account is not available.");
        }

        if (transaction.getFromAccount().equals(transaction.getToAccount())) {
            logFailed(transaction, true, true);
            throw new BankException("From and To account cannot be same.");
        }

        if (transaction.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            logFailed(transaction, true, true);
            throw new BankException("Invalid transaction amount.");
        }

        Account fromAccount = accountService.getAccountByNumber(transaction.getFromAccount());
        Account toAccount = accountService.getAccountByNumber(transaction.getToAccount());

        if (fromAccount.getBalance().compareTo(transaction.getAmount()) < 0) {
            logFailed(transaction, true, true);
            throw new BankException("Insufficient balance in from account.");
        }
        
        if(fromAccount.getStatus().equalsIgnoreCase("INACTIVE")){
            logFailed(transaction, true, true);
            throw new BankException("From Account is inactive.");
        }

        if(toAccount.getStatus().equalsIgnoreCase("INACTIVE")){
            logFailed(transaction, true, true);
            throw new BankException("To Account is inactive.");
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
    public void displayHighValueTransactions(BigDecimal threshold) {
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
