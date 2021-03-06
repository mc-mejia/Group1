package com.learning.BankingApplication.entity;

import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "accNumber")
public class CustomerAccount extends LoginAccount{
	
	

	public CustomerAccount(Long accountNumber, String accountType, boolean status, java.sql.Date doc, String userName,
			String password, Date dob, String customerName, String securityQuestion,
			String securityAnswer, long ssn, long phoneNo, boolean approved, List<BankAccount> accounts,
			List<Beneficiary> beneficiaries) {
		super(accountNumber, accountType, status, doc, userName, password);

		this.dob = dob;
		this.customerName = customerName;
		this.securityQuestion = securityQuestion;
		this.securityAnswer = securityAnswer;
		this.ssn = ssn;
		this.phoneNo = phoneNo;
		this.approved = approved;
		this.accounts = accounts;

	}

	private Date dob;
	private String customerName;
	private String securityQuestion;
	private String securityAnswer;
	private Long ssn;
	//customer will have image 
//	private byte[] profilePicture;
	private Long phoneNo;
	private boolean approved;

	@OneToMany(targetEntity=com.learning.BankingApplication.entity.BankAccount.class,
			/*cascade= CascadeType.ALL,*/ mappedBy="customerAccount")
	@JsonManagedReference
	List<BankAccount> accounts;

	@OneToMany(targetEntity=com.learning.BankingApplication.entity.Beneficiary.class,
			/*cascade= CascadeType.ALL,*/ mappedBy="customerAccount")
	@JsonManagedReference
	//@OneToMany(cascade=CascadeType.ALL)
	//@JoinTable(name = "beneficiaries", joinColumns = @JoinColumn(name="accNumber"))
	List<Beneficiary> beneficiaries;

	@Override
	public String toString() {
		return "CustomerAccount [" + ", dob=" + dob + ", customerName=" + customerName
				+ ", securityQuestion=" + securityQuestion + ", securityAnswer=" + securityAnswer + ", ssn=" + ssn
				+ ", phoneNo=" + phoneNo + ", approved=" + approved + "]";
	}
	
	
	
}
