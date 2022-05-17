package com.learning.BankingApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.BankingApplication.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

}
