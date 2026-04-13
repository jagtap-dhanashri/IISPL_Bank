package com.iispl.main;

import com.iispl.entity.Account;
import com.iispl.entity.Transaction;
import com.iispl.exceptions.BankException;
import com.iispl.repository.AccountRepo;
import com.iispl.repository.AccountRepoImpl;
import com.iispl.repository.TransactionRepo;
import com.iispl.repository.TransactionRepoImpl;
import com.iispl.service.AccountSericeImpl;
import com.iispl.service.AccountService;
import com.iispl.service.TransactionService;
import com.iispl.service.TransactionServiceImpl;

import java.math.BigDecimal;

import java.util.Scanner;

public class BankApplication {

    private static int nextAccountSeq = 1001;
    private static int nextTxnSeq = 1;

    public static void main(String[] args) {

        AccountRepo accountRepo = new AccountRepoImpl();
        TransactionRepo transactionRepo = new TransactionRepoImpl();
        AccountService accountService = new AccountSericeImpl(accountRepo);
        TransactionService transactionService = new TransactionServiceImpl(transactionRepo, accountService);

        Scanner scanner = new Scanner(System.in);
        int mainChoice;

        do {
            System.out.println("\n-------------------IISPL BANK----------------------         ");
            System.out.println("1. Accounts");
            System.out.println("2. Transactions");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            mainChoice = readInt(scanner);

            switch (mainChoice) {

                case 1:
                    int accChoice;
                    do {
                        System.out.println("\n--------------- Account Menu ----------------");
                        System.out.println("1. Add Account");
                        System.out.println("2. Display All Accounts");
                        System.out.println("3. Display Active Accounts");
                        System.out.println("4. Display Savings Accounts");
                        System.out.println("0. Back");
                        System.out.print("Enter choice: ");
                        accChoice = readInt(scanner);

                        switch (accChoice) {

                            case 1:
                                String accNo = generateAccountNumber(accountRepo);
                                System.out.println("Account Number : " + accNo);

                                System.out.print("Holder Name : ");
                                String name = readLine(scanner);

                                String type = chooseAccountType(scanner);

                                System.out.print("Balance : ");
                                BigDecimal balance = readBigDecimal(scanner);

                                String status = chooseAccountStatus(scanner);

                                accountService.addAccount(
                                        new Account(accNo, name, type, balance, status));
                                break;

                            case 2:
                                accountService.displayAllAccounts();
                                break;

                            case 3:
                                accountService.displayActiveAccounts();
                                break;

                            case 4:
                                accountService.displaySavingsAccounts();
                                break;

                            case 0:
                                System.out.println("Back to Main Menu....");
                                break;

                            default:
                                System.out.println("Invalid choice....");
                        }

                    } while (accChoice != 0);
                    break;

                case 2:
                    int txnChoice;
                    do {
                        System.out.println("\n--- Transaction Menu ---");
                        System.out.println("1. Add Transaction");
                        System.out.println("2. Display All Transactions");
                        System.out.println("3. Display High Value Transactions");
                        System.out.println("4. Display Failed Transactions");
                        System.out.println("0. Back");
                        System.out.print("Enter choice: ");
                        txnChoice = readInt(scanner);

                        switch (txnChoice) {

                            case 1:
                                String txnId = generateTransactionId(transactionRepo);
                                System.out.println("Transaction ID : " + txnId);

                                System.out.print("From Account : ");
                                String from = readLine(scanner);

                                System.out.print("To Account : ");
                                String to = readLine(scanner);

                                System.out.print("Amount : ");
                                BigDecimal amount = readBigDecimal(scanner);

                                String channel = chooseChannel(scanner);

                                try {
                                    transactionService.addTransaction(
                                            new Transaction(txnId, from, to, amount, channel));
                                } catch (BankException e) {
                                    System.out.println("Transaction failed: " + e.getMessage());
                                }

                                break;

                            case 2:
                                transactionService.displayAllTransactions();
                                break;

                            case 3:
                                System.out.print("Enter threshold amount: ");
                                BigDecimal threshold = readBigDecimal(scanner);
                                transactionService.displayHighValueTransactions(threshold);
                                break;

                            case 4:
                                transactionService.displayFailedTransactions();
                                break;

                            case 0:
                                System.out.println("Back to Main Menu......");
                                break;

                            default:
                                System.out.println("Invalid choice....");
                        }

                    } while (txnChoice != 0);
                    break;

                case 0:
                    System.out.println("Thank you...");
                    break;

                default:
                    System.out.println("Invalid choice.....");
            }

        } while (mainChoice != 0);

        scanner.close();
    }

    private static int readInt(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a number: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    private static BigDecimal readBigDecimal(Scanner scanner) {
        while (!scanner.hasNextBigDecimal()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        BigDecimal value = scanner.nextBigDecimal();
        scanner.nextLine();
        return value;
    }

    private static String readLine(Scanner scanner) {
        String value = scanner.nextLine();
        while (value.trim().isEmpty()) {
            System.out.print("Value cannot be empty, try again: ");
            value = scanner.nextLine();
        }
        return value;
    }

    private static String readToken(Scanner scanner) {
        String value = scanner.next();
        scanner.nextLine();
        return value;
    }

    private static String chooseAccountType(Scanner scanner) {
        System.out.println("Account Type:");
        System.out.println("1. SAVINGS");
        System.out.println("2. CURRENT");
        System.out.print("Enter choice: ");
        int choice = readInt(scanner);
        if (choice == 1)
            return "SAVINGS";
        if (choice == 2)
            return "CURRENT";
        System.out.println("Invalid choice, defaulting to SAVINGS.");
        return "SAVINGS";
    }

    private static String chooseAccountStatus(Scanner scanner) {
        System.out.println("Account Status:");
        System.out.println("1. ACTIVE");
        System.out.println("2. INACTIVE");
        System.out.print("Enter choice: ");
        int choice = readInt(scanner);
        if (choice == 1)
            return "ACTIVE";
        if (choice == 2)
            return "INACTIVE";
        System.out.println("Invalid choice, defaulting to ACTIVE.");
        return "ACTIVE";
    }

    private static String chooseChannel(Scanner scanner) {
        System.out.println("Channel:");
        System.out.println("1. UPI");
        System.out.println("2. NEFT");
        System.out.println("3. ATM");
        System.out.print("Enter choice: ");
        int choice = readInt(scanner);
        if (choice == 1)
            return "UPI";
        if (choice == 2)
            return "NEFT";
        if (choice == 3)
            return "ATM";
        System.out.println("Invalid choice, defaulting to UPI.");
        return "UPI";
    }

    private static String generateAccountNumber(AccountRepo accountRepo) {
        String candidate;
        do {
            candidate = "ACC" + nextAccountSeq++;
        } while (accountRepo.existsAccountNumber(candidate));
        return candidate;
    }

    private static String generateTransactionId(TransactionRepo transactionRepo) {
        String candidate;
        do {
            candidate = "TXN" + String.format("%05d", nextTxnSeq++);
        } while (transactionRepo.existsTransactionId(candidate));
        return candidate;
    }
}
