package com.learning.BankingApplication.controllers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Controller;

import com.learning.BankingApplication.contracts.CustomerService;
import com.learning.BankingApplication.entity.BankAccount;
import com.learning.BankingApplication.entity.Beneficiary;
import com.learning.BankingApplication.entity.CustomerAccount;
import com.learning.BankingApplication.entity.StaffAccount;
import com.learning.BankingApplication.entity.Transaction;
import com.learning.BankingApplication.exceptions.InsufficientBalanceException;

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
//		List<Transaction> listTrans = new ArrayList<>();
//		
//		listBnf.add(testBnf);
//		Date testDate = Date.valueOf("1999-12-12");
//		CustomerAccount testCustomer = new CustomerAccount(
//				1l, "customer", true, testDate, "jdawg123",
//				"password",
//				"Hello", testDate, "Jiminy Cricket", 
//				"Who is the puppet boy?", "Pinnochio", 
//				1234590, 3053005, true, listBanks, listBnf);
//		CustomerAccount dbCustomer = customerService.registerCustomerAccount(testCustomer);
//		
//		
//		BankAccount testBank = new BankAccount(1, dbCustomer, 100.0, listTrans, true, testDate, "savings");
//		BankAccount testBank2 = new BankAccount(2, dbCustomer, 200.0, listTrans, true, testDate, "checking");
//		customerService.createBankAccount(testBank);
//		customerService.createBankAccount(testBank2);
//		
//		Beneficiary testBnf2 = new Beneficiary(1, 1, true, testDate, dbCustomer);
//		customerService.addBeneficiary(6, testBnf2);
//		
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
		
		List<BankAccount> list = customerService.getAllBankAccountsById(23);
		for(BankAccount b:list) {
			System.out.println(b);
		}
	}
	

}
