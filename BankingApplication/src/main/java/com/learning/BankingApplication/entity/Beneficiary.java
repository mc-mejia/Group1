package com.learning.BankingApplication.entity;

import java.sql.Date;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Beneficiary {
	
	private long beneficiaryId;
	private long accoutnNo;
	private boolean approval;
	private Date dateOfApproval;
	
}
