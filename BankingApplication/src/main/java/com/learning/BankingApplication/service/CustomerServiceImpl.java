package com.learning.BankingApplication.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/api")
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
		LocalDate localDate = LocalDateTime.now().toLocalDate();
		customerAccount.setDoc(java.sql.Date.valueOf( localDate));
		return customerRepository.save(customerAccount);
	}

	@Override
	public CustomerAccount getCustomerAccountById(long customerId) throws EntityNotFoundException{

		return customerRepository.getById(customerId);
	}

	@Override
	public CustomerAccount updateCustomerAccount(CustomerAccount customerAccount) {
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
		currentCustomer.setStatus(newStatus);
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
	public BankAccount approveBankAccount(BankAccount bankAccount, long urlAccountNumber, long cid ) {
		BankAccount currentBankAccount = bankAccountRepository.getById(urlAccountNumber);
		currentBankAccount.setCustomerAccount(customerRepository.getById(cid));
		currentBankAccount.setApprove(true);
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
		return bankAccountRepository.getById(accountId);
	}

	@Override
	public List<BankAccount> getUnapprovedBankAccounts() {
		return bankAccountRepository.findAll().stream().filter(c-> !c.isApprove()).collect(Collectors.toList());
	}

	@Override
	public List<BankAccount> approveBankAccounts(List<BankAccount> accList) {
		for (BankAccount ba: accList) {
			ba = approveBankAccount(ba, ba.getAccountId(), ba.getCustomerAccount().getAccountNumber());
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

		CustomerAccount currentCustomer = customerRepository.getById(customerId);
		return currentCustomer.getBeneficiaries();
	}

	@Override
	public boolean deleteBeneficiaryById(long customerId, long beneficiaryId) {

		beneficiaryRepository.deleteById(beneficiaryId);
		return true;
		
	}

	@Override
	public List<Beneficiary> getUnapprovedBeneficiaries() {

		return beneficiaryRepository.findAll().stream().filter(c-> !c.isApproval()).collect(Collectors.toList());

	}

	@Override
	public boolean approveBeneficiary(Beneficiary beneficiary) {
		beneficiary.setApproval(true);
		beneficiaryRepository.save(beneficiary);
		return true;
	}

	
	//==========
	//Transfers/Transactions
	//==========
	


	@Override
	public boolean transfer(Transaction transaction) throws EntityNotFoundException, InsufficientBalanceException {
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
		toAccount.setBalance(toAccount.getBalance()+transaction.getTransactionAmount());
		bankAccountRepository.save(fromAccount);
		bankAccountRepository.save(toAccount);
		transactionRepository.save(transaction);
		//call repo method to add the transaction
		return true;
	}

	
	//==========
	//Verification
	//==========



	@Override
	public boolean questionVerification(String username, String answer) {

		CustomerAccount currentCustomer = customerRepository.getById(customerRepository.getIdbyUsername(username));
		return currentCustomer.getSecurityAnswer().equals(answer);
	}

	@Override
	public boolean updatePassword(String username, String password) {

		CustomerAccount currentCustomer = customerRepository.getById(customerRepository.getIdbyUsername(username));
		currentCustomer.setPassword(password);
		customerRepository.save(currentCustomer);
		return true;
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
