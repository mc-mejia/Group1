package com.learning.BankingApplication.entity;

import java.util.Date;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transaction {
	private long transactionId;
	private Date transactionDate;
	private double transactionAmount; 
	private String transactionType;   //Debit or Credit type
	private long beneficiaryId;
	private long senderId;
	private String reasonTransaction;
	
}
