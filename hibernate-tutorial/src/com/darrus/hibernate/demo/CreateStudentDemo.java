package com.darrus.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.darrus.hibernate.demo.entity.Student;

public class CreateStudentDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			System.out.println("Creating new student object...");
			Student temp = new Student("Paul", "Wall", "paul@gmail.com");
			
			session.beginTransaction();
			
			System.out.println("Saving the student...");
			session.save(temp);
			
			session.getTransaction().commit();
			
			System.out.println("Done saving student!");
		}
		finally {
			factory.close();
		}
	}

}
