package com.learning.BankingApplication.repository;

import com.learning.BankingApplication.entity.LoginAccount;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.learning.BankingApplication.entity.Beneficiary;
import com.learning.BankingApplication.entity.CustomerAccount;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerAccount, Long>{

    @Query("SELECT c.accountNumber FROM CustomerAccount c WHERE c.username=?1")
    Long getIdbyUsername(String username);
    
    @Query(value = "SELECT c FROM CustomerAccount c WHERE c.accountNumber=?1")
    CustomerAccount getCustomerObjectById(Long customerId);
    
    Optional<CustomerAccount> findByUsername(String username);
    
}
