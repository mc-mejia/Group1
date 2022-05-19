package com.learning.BankingApplication.repository;

import com.learning.BankingApplication.entity.LoginAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.learning.BankingApplication.entity.CustomerAccount;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerAccount, Long>{

    @Query("SELECT c.accountNumber FROM CustomerAccount c WHERE c.userName=?1")
    Long getIdbyUsername(String username);
}
