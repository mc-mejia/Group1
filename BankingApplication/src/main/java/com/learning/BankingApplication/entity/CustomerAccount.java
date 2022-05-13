package com.learning.BankingApplication.entity;

import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CustomerAccount {	
	private String customerService;
	private Date dob;
	private String customerName;
	private String securityQuestion;
	private String securityAnswer;
	private long ssn;
	//customer will have image 
	private BufferedImage profilePicture;
	private long phoneNo;
	private String approved;
	List<BankAccount>accounts;
	
}
