package com.learning.BankingApplication.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.learning.BankingApplication.contracts.CustomerService;
import com.learning.BankingApplication.entity.BankAccount;
import com.learning.BankingApplication.entity.Beneficiary;
import com.learning.BankingApplication.entity.CustomerAccount;
import com.learning.BankingApplication.entity.StaffAccount;
import com.learning.BankingApplication.entity.Transaction;
import com.learning.BankingApplication.exceptions.InsufficientBalanceException;
import com.learning.BankingApplication.repository.BankAccountRepository;
import com.learning.BankingApplication.repository.BeneficiaryRepository;
import com.learning.BankingApplication.repository.CustomerRepository;
import com.learning.BankingApplication.repository.StaffRepository;
import com.learning.BankingApplication.repository.TransactionRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService{
	
	CustomerRepository customerRepository;
	BankAccountRepository bankAccountRepository;
	BeneficiaryRepository beneficiaryRepository;
	TransactionRepository transactionRepository;
	StaffRepository staffRepository;
	

	//==========
	//Customer
	//==========
	
	@Override
	public CustomerAccount registerCustomerAccount(CustomerAccount customerAccount) {
		return customerRepository.save(customerAccount);
	}

	@Override
	public CustomerAccount getCustomerAccountById(long customerId) throws EntityNotFoundException{
		return customerRepository.getById(customerId);
	}

	@Override
	public CustomerAccount updateCustomerAccount(CustomerAccount customerAccount) {
		// TODO Auto-generated method stub
		return customerRepository.save(customerAccount);
	}

	@Override
	public List<CustomerAccount> getAllCustomerAccounts() {
		return customerRepository.findAll();
	}

	@Override
	public boolean toggleCustomer(long customerId, boolean newStatus) {
		//toggling only possible if account already exists, assume no misuse
		CustomerAccount currentCustomer = customerRepository.getById(customerId);
		currentCustomer.setApproved(newStatus);
		customerRepository.save(currentCustomer);
		return newStatus;
	}

	
	//==========
	//Bank Account
	//==========
	
	@Override
	public BankAccount createBankAccount(BankAccount bankAccount) {
		LocalDate localDate = LocalDateTime.now().toLocalDate();
		bankAccount.setDateOfCreation(java.sql.Date.valueOf( localDate));
		return bankAccountRepository.save(bankAccount);
	}

	@Override
	public BankAccount approveBankAccount(BankAccount bankAccount, long urlAccountNumber) {
		BankAccount currentBankAccount = bankAccountRepository.getById(urlAccountNumber);
		currentBankAccount.setApprove("APPROVED");
		bankAccountRepository.save(currentBankAccount);
		return currentBankAccount;
	}

	@Override
	public List<BankAccount> getAllBankAccountsById(long customerId) {
		CustomerAccount currentCustomer = customerRepository.getById(customerId);
		
		return currentCustomer.getAccounts();
	}

	@Override
	public BankAccount getBankAccountById(long customerId, long accountId) {
		BankAccount currentBankAccount = bankAccountRepository.getById(accountId);
		return currentBankAccount;
	}

	@Override
	public List<BankAccount> getUnapprovedBankAccounts() {
		//TODO
		return null;
	}

	@Override
	public List<BankAccount> approveBankAccounts(List<BankAccount> accList) {
		for (BankAccount ba: accList) {
			ba = approveBankAccount(ba, ba.getAccountId());
			accList.add(ba);
		}
		return accList;
	}

	
	//==========
	//Beneficiary
	//==========
	
	@Override
	public boolean addBeneficiary(long customerId, Beneficiary beneficiary) {
		beneficiaryRepository.save(beneficiary);
		return true;
	}

	@Override
	public List<Beneficiary> getAllBeneficiariesByCustomerId(long customerId) {
		// TODO Auto-generated method stub

		CustomerAccount currentCustomer = customerRepository.getById(customerId);

		return null;
	}

	@Override
	public boolean deleteBeneficiaryById(long customerId, long beneficiaryId) {
		// TODO Auto-generated method stub		
		
		beneficiaryRepository.deleteById(beneficiaryId);
		return false;
		
	}

	@Override
	public boolean getUnapprovedBeneficiaries(long beneficiaryId) {
		// TODO Auto-generated method stub
		beneficiaryRepository.findById(beneficiaryId);
		
		
		return false;
	}

	@Override
	public boolean approveBeneficiary(Beneficiary beneficiary) {
		// TODO Auto-generated method stub
		beneficiaryRepository.findAll();
		
		
		return true;
	}

	
	//==========
	//Transfers/Transactions
	//==========
	
	@Override
	public boolean transferByCustomer(Transaction transaction) {
		//TODO Actually move the money between the bank accounts, maybe in helper method
		
		//check if accounts exist
		
		//check if accounts are beneficiaries
		
		//check if there is enough money to transfer
		
		//update BankAccount balances appropriately (using repo calls)
		
		//call repo method to add the transaction
		createTransaction(transaction);
		//add exception handling
		return true;
	}

	@Override
	public boolean transferByStaff(Transaction transaction) throws EntityNotFoundException, InsufficientBalanceException {
		BankAccount fromAccount;
		BankAccount toAccount;
		//check if accounts exist
		fromAccount = bankAccountRepository.getById(transaction.getSenderId());
		toAccount = bankAccountRepository.getById(transaction.getBeneficiaryId());
		//check if there is enough money to transfer
		if(transaction.getTransactionAmount() > fromAccount.getBalance())
			throw new InsufficientBalanceException("Insufficient balance to transfer");
		//update BankAccount balances appropriately (using repo calls)
		fromAccount.setBalance(fromAccount.getBalance()-transaction.getTransactionAmount());
		toAccount.setBalance(fromAccount.getBalance()+transaction.getTransactionAmount());
		bankAccountRepository.save(fromAccount);
		bankAccountRepository.save(toAccount);
		//call repo method to add the transaction
		createTransaction(transaction);

		return true;
	}
	
	private boolean createTransaction(Transaction transaction) {
		transactionRepository.save(transaction);
		return true;
	}

	
	//==========
	//Verification
	//==========
	
	@Override
	public boolean questionVerification(String answer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updatePassword(String urlUsername, String payloadUsername, String password) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public StaffAccount registerStaffAccount(StaffAccount staffAccount) {
		staffAccount.setStatus(false);
		LocalDate localDate = LocalDateTime.now().toLocalDate();
		staffAccount.setDoc(java.sql.Date.valueOf(localDate));
		return staffRepository.save(staffAccount);
	}

	@Override
	public List<StaffAccount> getAllStaff() {
		return staffRepository.findAll();
	}

	@Override
	public boolean toggleStaff(long staffId, boolean newStatus) throws EntityNotFoundException{
		StaffAccount staffAccount = staffRepository.getById(staffId);
		staffAccount.setStatus(newStatus);
		staffRepository.save(staffAccount);
		return newStatus;
	}
	
}
