package com.darrus.aopdemo;

import java.util.logging.Logger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.darrus.aopdemo.service.TrafficFortuneService;

public class AroundWithLoggerDemoApp
{
	private static Logger logger = Logger.getLogger(AroundWithLoggerDemoApp.class.getName());
	
	public static void main(String[] args)
	{
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		
		TrafficFortuneService fortuneService = context.getBean(TrafficFortuneService.class);
	
		logger.info("\nMain Program: AroundDemoApp");
		
		logger.info("Calling getFortune");
		
		logger.info("\nMy fortune is: " + fortuneService.getFortune());
		
		logger.info("Finished");
		
		context.close();
	}

}
