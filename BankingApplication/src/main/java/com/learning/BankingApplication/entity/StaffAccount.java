package com.learning.BankingApplication.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "accNumber")
public class StaffAccount extends LoginAccount{
	
	//private long staffId; //I think this is done by LoginAccount
	private String staffName;
	//private String staffStatus; //I think this is done by LoginAccount

	public StaffAccount(Long accountNumber, String accountType, boolean status, Date doc, String userName,
			String password, String staffName) {
		super(accountNumber, accountType, status, doc, userName, password, null, null, null);
		this.staffName = staffName;
	}

	@Override
	public String toString() {
		return "StaffAccount [staffName=" + staffName + "]";
	}
	
	
	
}
