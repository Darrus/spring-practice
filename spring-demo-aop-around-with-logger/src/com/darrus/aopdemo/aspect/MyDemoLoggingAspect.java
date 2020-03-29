package com.darrus.aopdemo.aspect;

import java.util.List;
import java.util.logging.Logger;

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
	private Logger logger = Logger.getLogger(MyDemoLoggingAspect.class.getName());
	
	@Around("execution(* com.darrus.aopdemo.service.*.getFortune())")
	public Object aroundGetFortuneAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable
	{
		String method = proceedingJoinPoint.getSignature().toShortString();
		logger.info("\n=====>>> Executing @Around on method: " + method);
		
		long begin = System.currentTimeMillis();
		
		Object result = proceedingJoinPoint.proceed();
		
		long end = System.currentTimeMillis();
		
		long duration = end - begin;
		logger.info("\n=====>>> Duration:" + duration / 1000.0 + " seconds");
		
		return result;
	}
	
	@After("execution(* com.darrus.aopdemo.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyFindAccountsAdvice(JoinPoint joinPoint)
	{
		String method = joinPoint.getSignature().toShortString();
		logger.info("\n=====>>> Executing @After (finally) on method: " + method);
	}
	
	@AfterThrowing(
			pointcut="execution(* com.darrus.aopdemo.dao.AccountDAO.findAccounts(..))",
			throwing="exc")
	public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint, Throwable exc)
	{
		String method = joinPoint.getSignature().toShortString();
		logger.info("\n=====>>> Executing @AfterThrowing on method: " + method);
		logger.info("\n=====>>> Exception is: " + exc);
	}
	
	
	@AfterReturning(
			pointcut="execution(* com.darrus.aopdemo.dao.AccountDAO.findAccounts(..))",
			returning="result"
			)
	public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> result)
	{
		String method = joinPoint.getSignature().toShortString();
		logger.info("\n=====>>> Executing @AfterReturning on method: " + method);
		logger.info("\n=====>>> result is: " + result);
		
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
		logger.info("\n====>>> Executing @Before advice on method");
		
		MethodSignature methodSig = (MethodSignature) joinPoint.getSignature();
		logger.info("Method: " + methodSig);
		
		Object[] args = joinPoint.getArgs();
		for(Object arg : args)
		{
			logger.info(arg.toString());
			
			if(arg instanceof Account)
			{
				Account account = (Account)arg;
				logger.info("account name: " + account.getName());
				logger.info("account level: " + account.getLevel());
			}
		}
	}
}
