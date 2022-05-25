package com.learning.BankingApplication.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@CrossOrigin(origins = "http://localhost:4200")
@Service
@AllArgsConstructor
@RestController
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
	@PostMapping(value = "/customer/register")
	public CustomerAccount registerCustomerAccount(@RequestBody CustomerAccount customerAccount) {
		LocalDate localDate = LocalDateTime.now().toLocalDate();
		customerAccount.setDoc(java.sql.Date.valueOf( localDate));
		return customerRepository.save(customerAccount);
	}

	@Override
	@GetMapping(value = "/customer/{customerID}")
	public CustomerAccount getCustomerAccountById(@PathVariable(name="customerID") long customerId) throws EntityNotFoundException{
		
		return customerRepository.getById(customerId);

	}

	@Override
	@PutMapping(value = "/customer/:customerID")
	public CustomerAccount updateCustomerAccount(@RequestBody CustomerAccount customerAccount) {
		return customerRepository.save(customerAccount);
	}

	@Override
	@GetMapping(value = "/staff/customer")
	public List<CustomerAccount> getAllCustomerAccounts() {
		return customerRepository.findAll();
	}

	@Override
	@PutMapping(value = "/staff/customer")
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
	@PostMapping(value = "/customer/:customerID/account")
	public BankAccount createBankAccount(@RequestBody BankAccount bankAccount) {
		LocalDate localDate = LocalDateTime.now().toLocalDate();
		bankAccount.setDateOfCreation(java.sql.Date.valueOf( localDate));
		return bankAccountRepository.save(bankAccount);
	}

	@Override
	@PutMapping(value = "customer/:customerID/account/{accountNo}")
	public BankAccount approveBankAccount(@RequestBody BankAccount bankAccount, @PathVariable(name = "accountNo") long urlAccountNumber, @PathVariable(name="customerID") long cid ) {
		BankAccount currentBankAccount = bankAccountRepository.getById(urlAccountNumber);
		currentBankAccount.setCustomerAccount(customerRepository.getById(cid));
		currentBankAccount.setApprove(true);
		bankAccountRepository.save(currentBankAccount);
		return currentBankAccount;
	}

	@Override
	@GetMapping(value = "customer/{customerID}/account")
	public List<BankAccount> getAllBankAccountsById(@PathVariable(name="customerID") long customerId) {
		CustomerAccount currentCustomer = customerRepository.getById(customerId);
		
		return currentCustomer.getAccounts();
	}

	@Override
	@GetMapping(value = "customer/{customerID}/account/{accountID}")
	public BankAccount getBankAccountById(@PathVariable(name = "customerID") long customerId,@PathVariable(name = "accountID") long accountId) {
		return bankAccountRepository.getById(accountId);
	}

	@Override
	@GetMapping(value = "staff/accounts/approve")
	public List<BankAccount> getUnapprovedBankAccounts() {
		return bankAccountRepository.findAll().stream().filter(c-> !c.isApprove()).collect(Collectors.toList());
	}

	@Override
	@PutMapping(value = "staff/accounts/approve")
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
	@PostMapping(value = "customer/{customerID}/beneficiary") //@PathVariable(name="customerID")
	public boolean addBeneficiary( long customerId,@RequestBody Beneficiary beneficiary) {
//		CustomerAccount c  = getCustomerAccountById(customerId);
//		List<Beneficiary> l1 = new ArrayList<>();
//		l1.add(beneficiary);
//		l1.addAll(c.getBeneficiaries());
//		c.setBeneficiaries(l1);
//		customerRepository.save(c);
		
		
		LocalDate localDate = LocalDateTime.now().toLocalDate();
		beneficiary.setCustomerAccount(customerRepository.getById(customerId));
		beneficiary.setDateOfApproval(java.sql.Date.valueOf( localDate));
		beneficiary.setApproval(false);
		beneficiary.setBankAccountNo(1l);
		
		beneficiaryRepository.save(beneficiary);
		return true;
	}

	@Override
	@GetMapping(value = "customer/{customerID}/beneficiary")
	public List<Beneficiary> getAllBeneficiariesByCustomerId(@PathVariable(name="customerID") long customerId) {
//		List<Beneficiary> list = new ArrayList<>();
//		
//		Date testDate = Date.valueOf("1999-12-12");
//		CustomerAccount testCustomer = new CustomerAccount(
//				1l, "customer", true, testDate, "jdawg123",
//				"password",
//				testDate, "Jiminy Cricket", 
//				"Who is the puppet boy?", "Pinnochio", 
//				1234590, 3053005, true, null, null);
//		CustomerAccount dbCustomer = registerCustomerAccount(testCustomer);
//		Beneficiary b1 = new Beneficiary(2, 2, true, testDate, dbCustomer);
//		Beneficiary b2 = new Beneficiary(1, 1, true, testDate, dbCustomer);
//		
//		list.add(b2);
//		list.add(b1);
//		return list;
//		CustomerAccount c = customerRepository.getById(customerId);
//		List<Long> idList = new ArrayList<>();
//		for(Beneficiary b: c.getBeneficiaries()) {
//			idList.add(b.getBeneficiaryId());
//		}
		List<Beneficiary> list  = beneficiaryRepository.getBeneficiariesForCustomer(customerId);
		
////		CustomerAccount currentCustomer = customerRepository.getById(customerId);
//		List<Beneficiary> list  = beneficiaryRepository.getBeneficiariesForMember(customerId);
		return list;
	}

	@Override
	@DeleteMapping(value = "customer/{customerID}/beneficiary/{beneficiaryID}")
	public boolean deleteBeneficiaryById(@PathVariable(name = "customerID") long customerId,@PathVariable(name = "beneficiaryID") long beneficiaryId) {

//		beneficiaryRepository.deleteById(beneficiaryId);
		return true;
		
	}

	@Override
	@GetMapping(value = "staff/beneficiary")
	public List<Beneficiary> getUnapprovedBeneficiaries() {

		return beneficiaryRepository.findAll().stream().filter(c-> !c.isApproval()).collect(Collectors.toList());

	}

	@Override
	@PutMapping(value = "staff/beneficiary")
	public boolean approveBeneficiary(@RequestBody Beneficiary beneficiary) {
		beneficiary.setApproval(true);
		beneficiaryRepository.save(beneficiary);
		return true;
	}

	
	//==========
	//Transfers/Transactions
	//==========
	


	@Override
	@PutMapping(value = {"customer/transfer", "staff/transfer"}) //suspicious
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
	@GetMapping(value = "customer/:username/forgot/question/answer")
	public boolean questionVerification(@PathVariable(name = "username") String username, @PathVariable(name = "answer") String answer) {

		CustomerAccount currentCustomer = customerRepository.getById(customerRepository.getIdbyUsername(username));
		return currentCustomer.getSecurityAnswer().equals(answer);
	}

	@Override
	@PutMapping(value = "customer/:username/forgot")
	public boolean updatePassword(@PathVariable(name = "username") String username,@PathVariable(name = "password") String password) {

		CustomerAccount currentCustomer = customerRepository.getById(customerRepository.getIdbyUsername(username));
		currentCustomer.setPassword(password);
		customerRepository.save(currentCustomer);
		return true;
	}


	@Override
	@PostMapping(value = "admin/staff")
	public StaffAccount registerStaffAccount(@RequestBody StaffAccount staffAccount) {
		staffAccount.setStatus(false);
		LocalDate localDate = LocalDateTime.now().toLocalDate();
		staffAccount.setDoc(java.sql.Date.valueOf(localDate));
		return staffRepository.save(staffAccount);
	}

	@Override
	@GetMapping(value = "admin/staff")
	public List<StaffAccount> getAllStaff() {
		return staffRepository.findAll();
	}

	@Override
	@PutMapping(value = "admin/staff")
	public boolean toggleStaff(long staffId, boolean newStatus) throws EntityNotFoundException{
		StaffAccount staffAccount = staffRepository.getById(staffId);
		staffAccount.setStatus(newStatus);
		staffRepository.save(staffAccount);
		return newStatus;
	}
	
}
