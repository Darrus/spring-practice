package com.darrus.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect
{
	//@Before("execution(public void com.darrus.aopdemo.dao.AccountDAO.addAccount())")
	//@Before("execution(public void add*())")
	//@Before("execution(* add*())")
	//@Before("execution(* add*(com.darrus.aopdemo.Account, ..))")
	//@Before("execution(* add*(..))")
	@Before("execution(* com.darrus.aopdemo.dao.*.*(..))")
	public void beforeAddAccountAdvice()
	{
		System.out.println("====>>> Executing @Before advice on method");
	}
}
