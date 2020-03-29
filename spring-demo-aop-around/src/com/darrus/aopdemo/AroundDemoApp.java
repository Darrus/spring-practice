package com.darrus.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.darrus.aopdemo.service.TrafficFortuneService;

public class AroundDemoApp
{

	public static void main(String[] args)
	{
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		
		TrafficFortuneService fortuneService = context.getBean(TrafficFortuneService.class);
		
		System.out.println("\nMain Program: AroundDemoApp");
		
		System.out.println("Calling getFortune");
		
		System.out.println("\nMy fortune is: " + fortuneService.getFortune());
		
		System.out.println("Finished");
		context.close();
	}

}
