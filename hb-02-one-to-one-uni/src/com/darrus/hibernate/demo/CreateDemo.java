package com.darrus.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.darrus.hibernate.demo.entity.Instructor;
import com.darrus.hibernate.demo.entity.InstructorDetail;
import com.darrus.hibernate.demo.entity.Student;

public class CreateDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			Instructor instructor = new Instructor("Madhu", "Patel", "madhu@gmail.com");
			InstructorDetail instructorDetail = new InstructorDetail("http://www.youtube.com", "Guitar");
			instructor.setInstructorDetail(instructorDetail);
			
			session.beginTransaction();
			
			// This will also save the details object because of CascadeType.ALL
			System.out.println("Saving Instructor: " + instructor);
			session.save(instructor);
			
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}

}
