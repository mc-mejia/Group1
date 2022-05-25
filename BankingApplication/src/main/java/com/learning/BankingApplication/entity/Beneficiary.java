package com.learning.BankingApplication.entity;

import java.sql.Date;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="beneficiaries")
public class Beneficiary {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long beneficiaryId;
	private Long bankAccountNo;
	private boolean approval;
	private Date dateOfCreation;
	
	@JoinColumn(name = "accNumber")
	private CustomerAccount customerAccount;

	@Override
	public String toString() {

				+ ", dateOfApproval=" + dateOfCreation + "]";

	}
	
	
	
}
