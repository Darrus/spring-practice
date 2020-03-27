package com.darrus.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.darrus.aopdemo.dao.AccountDAO;
import com.darrus.aopdemo.dao.MembershipDAO;

public class MainDemoApp
{

	public static void main(String[] args)
	{
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		AccountDAO accountDAO = context.getBean(AccountDAO.class);
		MembershipDAO membershipDAO = context.getBean(MembershipDAO.class);
		
		accountDAO.addAccount(new Account(), true);
		accountDAO.doWork();

		accountDAO.setName("foobar");
		accountDAO.setServiceCode("silver");
		
		String name = accountDAO.getName();
		String code = accountDAO.getServiceCode();
		
		membershipDAO.addSillyMember();
		membershipDAO.goToSleep();
		context.close();
	}

}
