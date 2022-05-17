package com.learning.BankingApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.BankingApplication.entity.StaffAccount;

public interface StaffRepository extends JpaRepository<StaffAccount, Long>{
	
}
