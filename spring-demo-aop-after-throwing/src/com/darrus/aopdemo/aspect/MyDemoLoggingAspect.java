package com.darrus.aopdemo.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.darrus.aopdemo.Account;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect
{
	@AfterThrowing(
			pointcut="execution(* com.darrus.aopdemo.dao.AccountDAO.findAccounts(..))",
			throwing="exc")
	public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint, Throwable exc)
	{
		String method = joinPoint.getSignature().toShortString();
		System.out.println("\n=====>>> Executing @AfterThrowing on method: " + method);
		System.out.println("\n=====>>> Exception is: " + exc);
	}
	
	
	@AfterReturning(
			pointcut="execution(* com.darrus.aopdemo.dao.AccountDAO.findAccounts(..))",
			returning="result"
			)
	public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> result)
	{
		String method = joinPoint.getSignature().toShortString();
		System.out.println("\n=====>>> Executing @AfterReturning on method: " + method);
		System.out.println("\n=====>>> result is: " + result);
		
		convertAccountNamesToUpperCase(result);
	}
	
	private void convertAccountNamesToUpperCase(List<Account> result)
	{
		for(Account account : result)
		{
			account.setName(account.getName().toUpperCase());
		}
	}

	@Before("com.darrus.aopdemo.aspect.AopExpressions.forDaoPackageNoGetterSetter()")
	public void beforeAddAccountAdvice(JoinPoint joinPoint)
	{
		System.out.println("\n====>>> Executing @Before advice on method");
		
		MethodSignature methodSig = (MethodSignature) joinPoint.getSignature();
		System.out.println("Method: " + methodSig);
		
		Object[] args = joinPoint.getArgs();
		for(Object arg : args)
		{
			System.out.println(arg);
			
			if(arg instanceof Account)
			{
				Account account = (Account)arg;
				System.out.println("account name: " + account.getName());
				System.out.println("account level: " + account.getLevel());
			}
		}
	}
}
