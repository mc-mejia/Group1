package com.learning.BankingApplication.entity;

import java.sql.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
	private String label;
	
	@ManyToOne(targetEntity = CustomerAccount.class)
	@JoinColumn(name = "accNumber")
	@JsonBackReference
	private CustomerAccount customerAccount;

	@Override
	public String toString() {

		return "Beneficiary [beneficiaryId=" + beneficiaryId + ", accountNo=" + bankAccountNo + ", approval=" + approval
				+ ", dateOfApproval=" + dateOfCreation + "]";

	}
	
	
	
}
