package com.learning.BankingApplication.entity;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StaffAccount extends LoginAccount{
	
	private long staffId;
	private String staffName; 
	private String staffStatus; 
	
}
