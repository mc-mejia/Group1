package com.learning.BankingApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.learning.BankingApplication.controllers.TestController;
//Jujhar was here
@SpringBootApplication
public class BankingApplication {

	public static void main(String[] args) {
		
		ApplicationContext ctx = SpringApplication.run(BankingApplication.class, args);
		TestController tc = ctx.getBean("testController", TestController.class);
		tc.run();
		System.out.println("i am cool");
	}

	
}
