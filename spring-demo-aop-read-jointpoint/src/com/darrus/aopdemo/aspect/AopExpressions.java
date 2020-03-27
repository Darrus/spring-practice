package com.darrus.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AopExpressions
{
	@Pointcut("execution(* com.darrus.aopdemo.dao.*.*(..))")
	private void forDaoPackage(){}
	
	@Pointcut("execution(* com.darrus.aopdemo.dao.*.get*(..))")
	private void getter() {}
	
	@Pointcut("execution(* com.darrus.aopdemo.dao.*.set*(..))")
	private void setter() {}
	
	@Pointcut("forDaoPackage() && !(getter() || setter())")
	public void forDaoPackageNoGetterSetter() {}
}
