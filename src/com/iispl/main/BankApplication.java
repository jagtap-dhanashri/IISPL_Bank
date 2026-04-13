package com.iispl.main;

import com.iispl.entity.Account;
import com.iispl.entity.Transaction;
import com.iispl.repository.AccountRepo;
import com.iispl.repository.AccountRepoImpl;
import com.iispl.repository.TransactionRepo;
import com.iispl.repository.TransactionRepoImpl;
import com.iispl.service.AccountSericeImpl;
import com.iispl.service.AccountService;
import com.iispl.service.TransactionService;
import com.iispl.service.TransactionServiceImpl;

import java.math.BigInteger;
import java.util.Scanner;

public class BankApplication {

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
            mainChoice = scanner.nextInt();

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
                        accChoice = scanner.nextInt();

                        switch (accChoice) {

                            case 1:
                                scanner.nextLine(); 
                                System.out.print("Account Number : ");
                                String accNo = scanner.nextLine();

                                System.out.print("Holder Name : ");
                                String name = scanner.nextLine();

                                System.out.print("Account Type (Savings/Current): ");
                                String type = scanner.nextLine();

                                System.out.print("Balance : ");
                                BigInteger balance = scanner.nextBigInteger();

                                System.out.print("Status (ACTIVE/INACTIVE): ");
                                String status = scanner.next();

                                accountService.addAccount(
                                    new Account(accNo, name, type, balance, status)
                                );
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
                        txnChoice = scanner.nextInt();

                        switch (txnChoice) {

                            case 1:
                                scanner.nextLine(); 
                                System.out.print("Transaction ID : ");
                                String txnId = scanner.nextLine();

                                System.out.print("From Account : ");
                                String from = scanner.nextLine();

                                System.out.print("To Account : ");
                                String to = scanner.nextLine();

                                System.out.print("Amount : ");
                                BigInteger amount = scanner.nextBigInteger();

                                System.out.print("Channel (UPI/NEFT/ATM): ");
                                String channel = scanner.next();

                                System.out.print("Status (SUCCESS/FAILED): ");
                                String txnStatus = scanner.next();

                                transactionService.addTransaction(
                                    new Transaction(txnId, from, to, amount, channel, txnStatus)
                                );
                                break;

                            case 2:
                                transactionService.displayAllTransactions();
                                break;

                            case 3:
                                System.out.print("Enter threshold amount: ");
                                BigInteger threshold = scanner.nextBigInteger();
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
}