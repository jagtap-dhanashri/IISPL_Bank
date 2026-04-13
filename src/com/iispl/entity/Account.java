package com.iispl.entity;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Account {

	private final String accountNumber;
	private final String accountHolderName;
	private final String accountType;
	private  BigInteger balance;
	private  String status;
	private List<Transaction> transaction;

	public Account(String accountNumber, String accountHolderName, String accountType, BigInteger balance,
			String status) {
		this.accountNumber = accountNumber;
		this.accountHolderName = accountHolderName;
		this.accountType = accountType;
		this.balance = balance;
		this.status = status;
		this.transaction=new ArrayList<Transaction>();
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public String getAccountType() {
		return accountType;
	}

	public BigInteger getBalance() {
		return balance;
	}
	
	public void setBalance(BigInteger balance) {
		this.balance = balance;
	}

	public String getStatus() {
		return status;
	}

	public List<Transaction> getTransaction() {
		return transaction;
	}

	public void addTransaction(Transaction transaction) {
		this.transaction.add(transaction);
	}

	@Override
	public String toString() {
	    return "Account{" +
	            "accountNumber='" + accountNumber + '\'' +
	            ", accountHolderName='" + accountHolderName + '\'' +
	            ", accountType='" + accountType + '\'' +
	            ", balance=" + balance +
	            ", status='" + status + '\'' +
	            '}';
	}
	
}
