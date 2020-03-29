package com.darrus.aopdemo.dao;

import org.springframework.stereotype.Component;

@Component
public class MembershipDAO
{
	public Boolean addSillyMember()
	{
		System.out.println(getClass() + ": DOING MY DB WORK: ADDING A SILLY MEMBER");
		return true;
	}
	
	public void goToSleep()
	{
		System.out.println(getClass() + ": I'm going to sleep now...");
	}
}
