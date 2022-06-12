package com.learning.BankingApplication.entity;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bankAcc")
public class BankAccount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long accountId;

	@ManyToOne
	@JoinColumn(name = "cust_to_bank_join_col")//, nullable = true) //changed from false to true
	@JsonBackReference
	private CustomerAccount customerAccount;
	private double balance;

	@OneToMany(targetEntity=com.learning.BankingApplication.entity.Transaction.class,
			/*cascade= CascadeType.ALL,*/ mappedBy="bankAccount")
	List<Transaction> transactions;
	private boolean approve;
	private Date dateOfCreation;
	private String bankAccountType;
	@Override
	public String toString() {
		return "BankAccount [accountId=" + accountId + ", customerAccount=" + customerAccount.getUserName() + ", balance=" + balance
				+ ", approve=" + approve + ", dateOfCreation=" + dateOfCreation
				+ ", bankAccountType=" + bankAccountType + "]";
	} 
	
	
}
