package com.learning.BankingApplication.controllers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;

import com.learning.BankingApplication.contracts.CustomerService;
import com.learning.BankingApplication.entity.BankAccount;
import com.learning.BankingApplication.entity.Beneficiary;
import com.learning.BankingApplication.entity.CustomerAccount;
import com.learning.BankingApplication.entity.Transaction;

@Controller
public class TestController {
	CustomerService customerService;
	
	
	
	public TestController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}


	//Test service methods here!
	public void run() {

//		List<BankAccount> listBanks = new ArrayList<>();
//		Beneficiary testBnf = new Beneficiary();
//		List<Beneficiary> listBnf = new ArrayList<>();
		List<Transaction> listTrans = new ArrayList<>();
//		
//		listBnf.add(testBnf);
		Date testDate = Date.valueOf("1999-12-12");
//		CustomerAccount testCustomer = new CustomerAccount(
//				12l, "customer", true, testDate, "escrooge1",
//				"password",
//				testDate, "Ebenezer Scrooge", 
//				"What matters most?", "Money", 
//				22345210, 4053005, true, listBanks, listBnf);
//		CustomerAccount dbCustomer1 = customerService.registerCustomerAccount(testCustomer);
//		System.out.println(dbCustomer1);
//		
		
		////////////////////////////////////////
		//Testing Bank Accounts
		///////////////////////////////////////
		
//		CustomerAccount dbCustomer = customerService.getCustomerAccountById(5);
//		BankAccount testBank = new BankAccount(3, dbCustomer, 100.0, listTrans, false, testDate, "savings");
//		BankAccount testBank2 = new BankAccount(4, dbCustomer, 200.0, listTrans, false, testDate, "checking");
//		customerService.createBankAccount(testBank);
//		customerService.createBankAccount(testBank2);
//		
//		List<BankAccount> bAccounts = customerService.getUnapprovedBankAccounts();
//		customerService.approveBankAccounts(bAccounts);
//		System.out.println(customerService.getAllBankAccountsById(5));
		
//		

		////////////////////////////////////////
		//Testing Beneficiary, add and get list
		///////////////////////////////////////
		
		
//		CustomerAccount dbCustomer = customerService.getCustomerAccountById(1);
//		Beneficiary testBnf5 = new Beneficiary(3l, 13l, true, Date.valueOf("4200-12-12"), dbCustomer);
//		customerService.addBeneficiary(dbCustomer.getAccountNumber(),testBnf5);
//		customerService.updateCustomerAccount(dbCustomer);
//		List<Beneficiary> list = customerService.getAllBeneficiariesByCustomerId(dbCustomer.getAccountNumber());
//		System.out.println(customerService.getCustomerAccountById(testBnf5.getBeneficiaryId()));
//		for(Beneficiary b:list) {
//			System.out.println(b);
//		}
		/////////////////////////////////////////
		
		
//		Transaction trans1 = new Transaction(1, testDate, 50.0, "Debit", 1, 2, "broke", testBank2);
//		try {
//			customerService.transfer(trans1);
//		} catch (EntityNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InsufficientBalanceException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		StaffAccount testStaff = new StaffAccount(1l, "staff", true, testDate, "jdawg123",
//				"password","jeff123");
//		customerService.registerStaffAccount(testStaff);
//		
//		List<BankAccount> list = customerService.getAllBankAccountsById(23);
//		for(BankAccount b:list) {
//			System.out.println(b);
//		}
	}
	

}
