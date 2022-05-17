package com.learning.BankingApplication.entity;
import java.util.List;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class BankAccount {
	private long accountId;
	private long customerId;
	private double balance;
	List<Transaction> transactions;
	private String approve; 
	private Date dateOfCreation;
	private String bankAccountType; 
}
