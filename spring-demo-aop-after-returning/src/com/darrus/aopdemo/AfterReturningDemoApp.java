package com.darrus.aopdemo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.darrus.aopdemo.dao.AccountDAO;

public class AfterReturningDemoApp
{

	public static void main(String[] args)
	{
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		AccountDAO accountDAO = context.getBean(AccountDAO.class);
		
		List<Account> accounts = accountDAO.findAccounts();

		System.out.println("\n\nMain Program: AfterReturningDemoApp");
		System.out.println("----");
		System.out.println(accounts);
		System.out.println("\n");
		
		context.close();
	}

}
