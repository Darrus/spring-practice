package com.darrus.aopdemo.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
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
	@Around("execution(* com.darrus.aopdemo.service.*.getFortune())")
	public Object aroundGetFortuneAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable
	{
		String method = proceedingJoinPoint.getSignature().toShortString();
		System.out.println("\n=====>>> Executing @Around on method: " + method);
		
		long begin = System.currentTimeMillis();
		
		Object result = proceedingJoinPoint.proceed();
		
		long end = System.currentTimeMillis();
		
		long duration = end - begin;
		System.out.println("\n=====>>> Duration:" + duration / 1000.0 + " seconds");
		
		return result;
	}
	
	@After("execution(* com.darrus.aopdemo.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyFindAccountsAdvice(JoinPoint joinPoint)
	{
		String method = joinPoint.getSignature().toShortString();
		System.out.println("\n=====>>> Executing @After (finally) on method: " + method);
	}
	
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
