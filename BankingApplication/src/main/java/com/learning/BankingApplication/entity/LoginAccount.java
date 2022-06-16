/**
 * 
 */
package com.learning.BankingApplication.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
public class LoginAccount implements UserDetails{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="accNumber")
	private Long accountNumber; //primary key for db
	private String accountType;
	private boolean status;
	private Date doc;
	private String username;
	private String password;
	
	private String strRole;
	
	@Transient
	private Collection<? extends GrantedAuthority> authorities;
	
	  @ManyToMany(fetch = FetchType.LAZY)
	  @JoinTable(  name = "user_roles", 
	        joinColumns = @JoinColumn(name = "user_id"), 
	        inverseJoinColumns = @JoinColumn(name = "role_id"))
	  private Set<Role> roles = new HashSet<>();
	  
	  public LoginAccount(Long accountNumber, String username, String password,
		      Collection<? extends GrantedAuthority> authorities) {
		    this.accountNumber = accountNumber;
		    this.username = username;
		    //this.email = email;
		    this.password = password;
		    this.authorities = authorities;
		  }
	  
	  public static LoginAccount build(LoginAccount user) {
		    Collection<GrantedAuthority> authorities = user.getRoles().stream()
		        .map(role -> new SimpleGrantedAuthority(role.getName().name()))
		        .collect(Collectors.toList());

		    return new LoginAccount(
		        user.getAccountNumber(), 
		        user.getUsername(), 
		        user.getPassword(), 
		        authorities);
		  }
	
	@Override
	public String toString() {
		return "LoginAccount [accountNumber=" + accountNumber + ", accountType=" + accountType + ", status=" + status
				+ ", doc=" + doc + ", userName=" + username + ", password=" + password + "]";
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	

}
