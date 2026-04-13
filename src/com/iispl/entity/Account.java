package com.iispl.entity;

import java.math.BigInteger;

public class Account {

	private String accountNumber;
	private String accountHolderName;
	private String accountType;
	private BigInteger balance;
	private String status;

	public Account(String accountNumber, String accountHolderName, String accountType, BigInteger balance,
			String status) {
		super();
		this.accountNumber = accountNumber;
		this.accountHolderName = accountHolderName;
		this.accountType = accountType;
		this.balance = balance;
		this.status = status;
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

	public String getStatus() {
		return status;
	}

}
