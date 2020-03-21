package com.darrus.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.darrus.hibernate.demo.entity.Instructor;
import com.darrus.hibernate.demo.entity.InstructorDetail;
import com.darrus.hibernate.demo.entity.Student;

public class DeleteInstructorDetailDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			int id = 3;

			session.beginTransaction();
			
			InstructorDetail instructorDetail = session.get(InstructorDetail.class, id);
			
			System.out.println("Instructor Detail: " + instructorDetail);
			
			System.out.println("Instructor: " + instructorDetail.getInstructor());

			System.out.println("Deleting instructor detail..." + instructorDetail);
			
			instructorDetail.getInstructor().setInstructorDetail(null);
			
			session.delete(instructorDetail);
			
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			session.close();
			factory.close();
		}
	}

}
