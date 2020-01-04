package com.darrus.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.darrus.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();
		
		try {
			System.out.println("Creating 3 student object...");
			Student temp1 = new Student("John", "Doe", "john@gmail.com");
			Student temp2 = new Student("Darrus", "Goh", "darrus@gmail.com");
			Student temp3 = new Student("Shawn", "Mendes", "shawn@gmail.com");
			session.beginTransaction();
			
			System.out.println("Saving the students...");
			session.save(temp1);
			session.save(temp2);
			session.save(temp3);
			
			session.getTransaction().commit();
			
			System.out.println("Done saving students!");
		}
		finally {
			factory.close();
		}
	}

}
