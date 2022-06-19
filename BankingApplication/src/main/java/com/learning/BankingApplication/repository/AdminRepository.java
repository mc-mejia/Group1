package com.learning.BankingApplication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.BankingApplication.entity.Admin;
import com.learning.BankingApplication.entity.StaffAccount;

public interface AdminRepository extends JpaRepository<Admin, Long>{
	Optional<StaffAccount> findByUsername(String username);
}
