package com.darrus.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.darrus.hibernate.demo.entity.Student;

public class ReadStudentDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			System.out.println("Creating new student object...");
			Student temp = new Student("Daffy", "Duck", "daffy@gmail.com");
			
			session.beginTransaction();
			
			System.out.println("Saving the student...");
			session.save(temp);
			
			session.getTransaction().commit();
			
			System.out.println("Saved student. Generated id: " + temp.getId());

			session = factory.getCurrentSession();
			session.beginTransaction();
			
			System.out.println("\nGetting student with id: " + temp.getId());
			
			Student student = session.get(Student.class, temp.getId());
			
			System.out.println("Get complete: " + student);
			
			session.getTransaction().commit();
			
			System.out.println("Done saving student!");
		}
		finally {
			factory.close();
		}
	}

}
