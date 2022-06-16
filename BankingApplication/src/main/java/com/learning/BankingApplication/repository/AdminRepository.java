package com.learning.BankingApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.BankingApplication.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long>{

}
