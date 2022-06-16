package com.learning.BankingApplication.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "accNumber")
public class Admin extends LoginAccount{
	
}
