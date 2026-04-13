package com.iispl.entity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Account {

	private final String accountNumber;
	private final String accountHolderName;
	private final String accountType;
	private  BigDecimal balance;
	private  String status;
	private List<Transaction> transaction;

	public Account(String accountNumber, String accountHolderName, String accountType, BigDecimal balance,
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

	public BigDecimal getBalance() {
		return balance;
	}
	
	public void setBalance(BigDecimal balance) {
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
