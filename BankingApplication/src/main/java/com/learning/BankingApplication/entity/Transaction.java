package com.learning.BankingApplication.entity;

import java.util.Date;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long transactionId;
	private Date transactionDate;
	private double transactionAmount; 
	private String transactionType;   //Debit or Credit type
	private long beneficiaryId;
	private long senderId;
	private String reasonTransaction;

	@ManyToOne
	@JoinColumn(name = "bank_trans_con", nullable = false)
	BankAccount bankAccount;

	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", transactionDate=" + transactionDate
				+ ", transactionAmount=" + transactionAmount + ", transactionType=" + transactionType
				+ ", beneficiaryId=" + beneficiaryId + ", senderId=" + senderId + ", reasonTransaction="
				+ reasonTransaction + ", bankAccount=" + /*bankAccount.getAccountId()*/"]";
	}
	
}
