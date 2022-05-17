package com.learning.BankingApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.BankingApplication.entity.CustomerAccount;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerAccount, Long>{

}
