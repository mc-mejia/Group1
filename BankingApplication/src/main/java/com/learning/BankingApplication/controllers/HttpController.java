package com.learning.BankingApplication.controllers;

import java.util.List;

import javax.persistence.EntityNotFoundException;

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

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class HttpController {
	CustomerService service;
	
	@PostMapping(value = "/customer/register")
	public CustomerAccount registerCustomerAccount(@RequestBody CustomerAccount customerAccount) {
		return service.registerCustomerAccount(customerAccount);
	}
	
	@GetMapping(value = "/customer/{customerID}")
	public CustomerAccount getCustomerAccountById(@PathVariable(name="customerID") long customerId) throws EntityNotFoundException{

		return service.getCustomerAccountById(customerId);
	}
	
	@PutMapping(value = "/customer/:customerID")
	public CustomerAccount updateCustomerAccount(@RequestBody CustomerAccount customerAccount) {
		return service.updateCustomerAccount(customerAccount);
	}
	
	@GetMapping(value = "/staff/customer")
	public List<CustomerAccount> getAllCustomerAccounts() {
		return service.getAllCustomerAccounts();
	}
	
	@PutMapping(value = "/staff/customer")
	public boolean toggleCustomer(long customerId, boolean newStatus) {
		return service.toggleCustomer(customerId, newStatus);
	}
	
	@PostMapping(value = "/customer/:customerID/account")
	public BankAccount createBankAccount(@RequestBody BankAccount bankAccount) {
		return service.createBankAccount(bankAccount);
	}
	
	@PutMapping(value = "customer/:customerID/account/{accountNo}")
	public BankAccount approveBankAccount(@RequestBody BankAccount bankAccount, @PathVariable(name = "accountNo") long urlAccountNumber, @PathVariable(name="customerID") long cid ) {
		return service.approveBankAccount(bankAccount, urlAccountNumber, cid);
	}
	
	@GetMapping(value = "customer/{customerID}/account")
	public List<BankAccount> getAllBankAccountsById(@PathVariable(name="customerID") long customerId) {	
		return service.getAllBankAccountsById(customerId);
	}
	
	@GetMapping(value = "customer/{customerID}/account/{accountID}")
	public BankAccount getBankAccountById(@PathVariable(name = "customerID") long customerId,@PathVariable(name = "accountID") long accountId) {
		return service.getBankAccountById(customerId, accountId);
	}
	
	@GetMapping(value = "staff/accounts/approve")
	public List<BankAccount> getUnapprovedBankAccounts() {
		return service.getUnapprovedBankAccounts();
	}
	
	@PutMapping(value = "staff/accounts/approve")
	public List<BankAccount> approveBankAccounts(List<BankAccount> accList) {
		return service.approveBankAccounts(accList);
	}
	
	@PostMapping(value = "customer/{customerID}/beneficiary")
	public boolean addBeneficiary(@PathVariable(name="customerID") long customerId,@RequestBody Beneficiary beneficiary) {
		return service.addBeneficiary(customerId, beneficiary);
	}
	
	@GetMapping(value = "customer/{customerID}/beneficiary")
	public List<Beneficiary> getAllBeneficiariesByCustomerId(@PathVariable(name="customerID") long customerId) {
		return service.getAllBeneficiariesByCustomerId(customerId);
	}
	
	@DeleteMapping(value = "customer/:customerID/beneficiary/:beneficiaryID")
	public boolean deleteBeneficiaryById(@PathVariable(name = "customerID") long customerId,@PathVariable(name = "beneficiaryID") long beneficiaryId) {

		return service.deleteBeneficiaryById(customerId, beneficiaryId);
		
	}
	
	@GetMapping(value = "staff/beneficiary")
	public List<Beneficiary> getUnapprovedBeneficiaries() {

		return service.getUnapprovedBeneficiaries();

	}
	
	@PutMapping(value = "staff/beneficiary")
	public boolean approveBeneficiary(@RequestBody Beneficiary beneficiary) {
		return service.approveBeneficiary(beneficiary);
	}
	
	@PutMapping(value = {"customer/transfer", "staff/transfer"}) //suspicious
	public boolean transfer(Transaction transaction) throws EntityNotFoundException, InsufficientBalanceException {
		return service.transfer(transaction);
	}
	
	@GetMapping(value = "customer/:username/forgot/question/answer")
	public boolean questionVerification(@PathVariable(name = "username") String username, @PathVariable(name = "answer") String answer) {

		return service.questionVerification(username, answer);
	}
	
	@PutMapping(value = "customer/:username/forgot")
	public boolean updatePassword(@PathVariable(name = "username") String username,@PathVariable(name = "password") String password) {

		return service.updatePassword(username, password);
	}
	
	@PostMapping(value = "admin/staff")
	public StaffAccount registerStaffAccount(@RequestBody StaffAccount staffAccount) {
		return service.registerStaffAccount(staffAccount);
	}
	
	@GetMapping(value = "admin/staff")
	public List<StaffAccount> getAllStaff() {
		return service.getAllStaff();
	}
	
	@PutMapping(value = "admin/staff")
	public boolean toggleStaff(long staffId, boolean newStatus) throws EntityNotFoundException{
		return service.toggleStaff(staffId, newStatus);
	}
}
