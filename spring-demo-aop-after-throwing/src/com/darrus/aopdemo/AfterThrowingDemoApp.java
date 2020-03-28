package com.darrus.aopdemo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.darrus.aopdemo.dao.AccountDAO;

public class AfterThrowingDemoApp
{

	public static void main(String[] args)
	{
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		AccountDAO accountDAO = context.getBean(AccountDAO.class);
		List<Account> accounts = null;
		
		try
		{
			boolean tripWire = true;
			accounts = accountDAO.findAccounts(tripWire);		
		}
		catch(Exception e)
		{
			System.out.println("\n\nMain Program: ... caught exception: " + e);
		}
		
		System.out.println("\n\nMain Program: AfterThrowingDemoApp");
		System.out.println("----");
		System.out.println(accounts);
		System.out.println("\n");
		context.close();
	}

}
