package com.learning.BankingApplication.security;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.learning.BankingApplication.entity.LoginAccount;
import com.learning.BankingApplication.repository.CustomerRepository;
import com.learning.BankingApplication.repository.StaffRepository;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	StaffRepository staffRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		LoginAccount user;
		
		try {
			user = customerRepository.findByUsername(username).
					orElseThrow(()->new UsernameNotFoundException("User Not Found with username" + username ));
		}
		catch(UsernameNotFoundException unfe) {
			user = staffRepository.findByUsername(username).
					orElseThrow(()->new UsernameNotFoundException("User Not Found with username" + username ));
		}
		
		List<GrantedAuthority> authorities = user.getRoles().stream()
		        .map(role -> new SimpleGrantedAuthority(role.getName().name()))
		        .collect(Collectors.toList());
		
		user.setAuthorities(authorities);
		
		return user;
	}

}
