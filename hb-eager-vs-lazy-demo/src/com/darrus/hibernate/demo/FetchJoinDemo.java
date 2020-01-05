package com.darrus.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.darrus.hibernate.demo.entity.Course;
import com.darrus.hibernate.demo.entity.Instructor;
import com.darrus.hibernate.demo.entity.InstructorDetail;

public class FetchJoinDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			
			session.beginTransaction();
			
			int instructorId = 1;

			Query<Instructor> query = session.createQuery("select i from Instructor i"
					+ " JOIN FETCH i.courses"
					+ " where i.id=:instructorId", 
					Instructor.class);
			
			query.setParameter("instructorId", instructorId);
			
			Instructor instructor = query.getSingleResult();

			System.out.println("Instructor: " + instructor);
			
			session.getTransaction().commit();

			session.close();

			System.out.println("The session is now close");

			System.out.println("Courses: " + instructor.getCourses());
			
			System.out.println("Done!");
		}
		finally {
			session.close();
			factory.close();
		}
	}

}
