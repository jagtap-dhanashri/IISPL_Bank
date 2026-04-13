package com.iispl.entity;

import java.math.BigDecimal;
import java.math.BigInteger;

public final class Transaction {
	
	private final String transactionId;
	private final String fromAccount;
	private final String toAccount;
	private final BigDecimal amount;
	private final String channel;
	private String status;
	public Transaction(String transactionId, String fromAccount, String toAccount, BigDecimal amount, String channel
			) {
		this.transactionId = transactionId;
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.amount = amount;
		this.channel = channel;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public String getFromAccount() {
		return fromAccount;
	}
	public String getToAccount() {
		return toAccount;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public String getChannel() {
		return channel;
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String status){
		this.status=status;
	}
	
	 @Override
    public String toString() {
        return "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", fromAccount='" + fromAccount + '\'' +
                ", toAccount='" + toAccount + '\'' +
                ", amount=" + amount +
                ", channel='" + channel + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
	
	

}
