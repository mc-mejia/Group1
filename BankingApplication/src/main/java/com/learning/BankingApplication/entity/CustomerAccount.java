package com.learning.BankingApplication.entity;

import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "accNumber")
public class CustomerAccount extends LoginAccount{

	private String customerService;
	private Date dob;
	private String customerName;
	private String securityQuestion;
	private String securityAnswer;
	private long ssn;
	//customer will have image 
//	private byte[] profilePicture;
	private long phoneNo;
	private boolean approved;

	@OneToMany(targetEntity=com.learning.BankingApplication.entity.BankAccount.class,
			cascade= CascadeType.ALL, mappedBy="customerAccount")
	List<BankAccount> accounts;

	@OneToMany(targetEntity=com.learning.BankingApplication.entity.Beneficiary.class,
			cascade= CascadeType.ALL, mappedBy="customerAccount")
	List<Beneficiary> beneficiaries;
	
}
