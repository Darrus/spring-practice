package com.darrus.springdemo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class TennisCoach implements Coach {

	@Autowired
	@Qualifier("randomFortuneService")
	private FortuneService fortuneService;
	
	public TennisCoach()
	{
		System.out.println("TennisCoach: Inside default constructor");
	}
	
	@PostConstruct
	public void doMyStartupStuff()
	{
		System.out.println("TennisCoach: inside of doMyStartupStuff");
	}
	
	@PreDestroy
	public void doMyCleanupStuff()
	{
		System.out.println("TennisCoach: inside of doMyCleanupStuff");
	}
	
//	@Autowired
//	public void setFortuneService(FortuneService fortuneService) {
//		System.out.println("TennisCoach: Inside setFortuneService");
//		this.fortuneService = fortuneService;
//	}

//	@Autowired
//	public TennisCoach(FortuneService fortuneService) 
//	{
//		this.fortuneService = fortuneService;
//	}

	@Override
	public String getDailyWorkout() 
	{
		return "Practice your backhand volley";
	}

	@Override
	public String getDailyFortune() 
	{
		return fortuneService.getFortune();
	}

}
