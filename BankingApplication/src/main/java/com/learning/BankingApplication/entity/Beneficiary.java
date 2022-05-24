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
	private long beneficiaryId;
	private long accountNo;
	private boolean approval;
	private Date dateOfCreation;
	@ManyToOne
	@JoinColumn(name = "cust_ben_con")
	private CustomerAccount customerAccount;
	@Override
	public String toString() {
		return "Beneficiary [beneficiaryId=" + beneficiaryId + ", accountNo=" + accountNo + ", approval=" + approval
				+ ", dateOfApproval=" + dateOfCreation + ", customerAccount=" + customerAccount.getUserName() + "]";
	}
	
	
	
}
