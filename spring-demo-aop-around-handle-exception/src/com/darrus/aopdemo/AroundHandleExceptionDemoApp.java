package com.darrus.aopdemo;

import java.util.logging.Logger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.darrus.aopdemo.service.TrafficFortuneService;

public class AroundHandleExceptionDemoApp
{
	private static Logger logger = Logger.getLogger(AroundHandleExceptionDemoApp.class.getName());
	
	public static void main(String[] args)
	{
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		
		TrafficFortuneService fortuneService = context.getBean(TrafficFortuneService.class);
	
		logger.info("\nMain Program: AroundDemoApp");
		
		logger.info("Calling getFortune");
		
		boolean tripwire = true;
		
		logger.info("\nMy fortune is: " + fortuneService.getFortune(tripwire));
		
		logger.info("Finished");
		
		context.close();
	}

}
