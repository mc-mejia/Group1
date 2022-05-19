/**
 * 
 */
package com.learning.BankingApplication.entity;

import java.sql.Date;

import javax.persistence.*;

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
@Table(name="accounts")
@Inheritance(strategy = InheritanceType.JOINED)
public class LoginAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="accNumber")
	private Long accountNumber; //primary key for db
	private String accountType;
	private boolean status;
	private Date doc;
	private String userName;
	private String password;

}
