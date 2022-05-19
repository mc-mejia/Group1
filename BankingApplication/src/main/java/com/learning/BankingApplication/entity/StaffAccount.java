package com.learning.BankingApplication.entity;

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
	
}
