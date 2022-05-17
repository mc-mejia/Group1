/**
 * 
 */
package com.learning.BankingApplication.entity;

import java.sql.Date;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author MariaMejia
 * @Date May 13, 2022
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LoginAccount {
	private Long accountNumber; //primary key for db
	private String accountType;
	private String status;
	private Date doc;
	private String userName;
	private String password;

}
