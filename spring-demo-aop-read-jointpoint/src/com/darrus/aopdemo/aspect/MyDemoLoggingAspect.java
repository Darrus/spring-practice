package com.darrus.aopdemo.aspect;

import org.aspectj.lang.JoinPoint;
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
