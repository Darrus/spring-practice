package com.darrus.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.darrus.hibernate.demo.entity.Course;
import com.darrus.hibernate.demo.entity.Instructor;
import com.darrus.hibernate.demo.entity.InstructorDetail;
import com.darrus.hibernate.demo.entity.Review;
import com.darrus.hibernate.demo.entity.Student;

public class DeletePacmanCourseDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			
			session.beginTransaction();
			
			int courseId = 10;
			Course tempCourse = session.get(Course.class, courseId);
			
			System.out.println("\nDeleting course " + tempCourse.getTitle());
			session.delete(tempCourse);
			
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}

}
