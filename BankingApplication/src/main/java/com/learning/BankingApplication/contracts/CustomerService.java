package com.learning.BankingApplication.contracts;

import java.util.List;

import com.learning.BankingApplication.entity.BankAccount;
import com.learning.BankingApplication.entity.Beneficiary;
import com.learning.BankingApplication.entity.CustomerAccount;
import com.learning.BankingApplication.entity.StaffAccount;
import com.learning.BankingApplication.entity.Transaction;

public interface CustomerService {
	/*Customer Handling*/
	CustomerAccount registerCustomerAccount(CustomerAccount customerAccount);
	CustomerAccount getCustomerAccountById(long customerId);
	CustomerAccount updateCustomerAccount(CustomerAccount customerAccount);
	List<CustomerAccount> getAllCustomerAccounts();
	boolean toggleCustomer(long customerId, String newStatus);
	
	/*Bank Account Handling*/
	BankAccount createBankAccount(BankAccount bankAccount);
	BankAccount approveBankAccount(BankAccount bankAccount, long urlAccountNumber);
	List<BankAccount> getAllBankAccountsById(long customerId);
	BankAccount getBankAccountById(long customerId, long accountId);
	List<BankAccount> getUnapprovedBankAccounts();
	List<BankAccount> approveBankAccounts(List<BankAccount> accList);
		//added this ourselves, not sure if needed
	
	/*Beneficiary Handling*/
	boolean addBeneficiary(long customerId, Beneficiary beneficiary);
	List<Beneficiary> getAllBeneficiariesByCustomerId(long customerId);
	boolean deleteBeneficiaryById(long customerId, long beneficiaryId);
	List<Beneficiary> getUnapprovedBeneficiaries();
	Beneficiary approveBeneficiary(Beneficiary beneficiary);
	
	/*Transaction Handling*/
	boolean transferByCustomer(Transaction transaction);
	boolean transferByStaff(Transaction transaction);
	
	/*Authentication Handling*/
	//authenticate goes here (for both customer and staff and admin?)
	boolean questionVerification(String answer);//Not sure about this
	boolean updatePassword(String urlUsername, String payloadUsername, String password);
	
	/*Staff Handling*/
	StaffAccount registerStaffAccount(StaffAccount staffAccount);
	List<StaffAccount> getAllStaff();
	boolean toggleStaff(long staffId, String newStatus);
}
