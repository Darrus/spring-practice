package com.darrus.aopdemo.dao;

import org.springframework.stereotype.Component;

import com.darrus.aopdemo.Account;

@Component
public class AccountDAO
{
	public void addAccount(Account account, Boolean vipFlag)
	{
		System.out.println(getClass() + ": DOING MY DB WORK: ADDING ACCOUNT");
	}
	
	public boolean doWork()
	{
		System.out.println(getClass() + ": doWork()");
		return false;
	}
}
