package com.learning.BankingApplication.contracts;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.learning.BankingApplication.entity.Admin;
import com.learning.BankingApplication.entity.BankAccount;
import com.learning.BankingApplication.entity.Beneficiary;
import com.learning.BankingApplication.entity.CustomerAccount;
import com.learning.BankingApplication.entity.StaffAccount;
import com.learning.BankingApplication.entity.Transaction;
import com.learning.BankingApplication.exceptions.InsufficientBalanceException;

public interface CustomerService {
	
	Admin createDefaultAdmin();
	
	/*Customer Handling*/
	CustomerAccount registerCustomerAccount(CustomerAccount customerAccount);
	CustomerAccount getCustomerAccountById(long customerId);
	//CustomerAccount updateCustomerAccount(CustomerAccount customerAccount);
	CustomerAccount updateCustomerAccount(CustomerAccount customerAccount);
	List<CustomerAccount> getAllCustomerAccounts();
	CustomerAccount toggleCustomer(CustomerAccount ca);
	
	/*Bank Account Handling*/
	BankAccount createBankAccount(BankAccount bankAccount, long customerId);
	BankAccount approveBankAccount(BankAccount bankAccount/*, long urlAccountNumber, long cid*/);
	List<BankAccount> getAllBankAccountsById(long customerId);
	BankAccount getBankAccountById(/*long customerId, */long accountId);
	List<BankAccount> getUnapprovedBankAccounts();
	List<BankAccount> approveBankAccounts(List<BankAccount> accList);
		//added this ourselves, not sure if needed
	
	/*Beneficiary Handling*/
	Beneficiary addBeneficiary(long customerId, Beneficiary beneficiary);
	List<Beneficiary> getAllBeneficiariesByCustomerId(long customerId);
	boolean deleteBeneficiaryById(long customerId, long beneficiaryId);
	List<Beneficiary> getUnapprovedBeneficiaries();
	Beneficiary approveBeneficiary(Beneficiary beneficiary);
	
	/*Transaction Handling*/
	Transaction transfer(Transaction transaction) throws EntityNotFoundException, InsufficientBalanceException;
	
	/*Authentication Handling*/
	//authenticate goes here (for both customer and staff and admin?)
	boolean questionVerification(String username, String answer);//Not sure about this
	boolean updatePassword(String username, String password);
	
	/*Staff Handling*/
	StaffAccount registerStaffAccount(StaffAccount staffAccount);
	List<StaffAccount> getAllStaff();
	StaffAccount toggleStaff(StaffAccount sa);
}
