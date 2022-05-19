package com.learning.BankingApplication.controllers;

import org.springframework.stereotype.Controller;

import com.learning.BankingApplication.contracts.CustomerService;

@Controller
public class TestController {
	CustomerService customerService;
	
	
	
	public TestController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}


	//Test service methods here!
	public void run() {
		
	}
}
