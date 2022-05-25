package com.learning.BankingApplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.learning.BankingApplication.entity.Beneficiary;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long>{
	
//	@Query("SELECT b FROM Beneficiary b WHERE b.customerAccount.accountNumber = ?1")
//	public List<Beneficiary> getAllBeneficiariesByCustomerId(long customerId);
	
	@Query(value ="SELECT * FROM beneficiaries where acc_number=?1", nativeQuery=true)
    List<Beneficiary> getBeneficiariesForCustomer(long customerId);
}