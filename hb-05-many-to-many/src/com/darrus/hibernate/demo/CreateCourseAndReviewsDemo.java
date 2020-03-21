package com.darrus.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.darrus.hibernate.demo.entity.Course;
import com.darrus.hibernate.demo.entity.Instructor;
import com.darrus.hibernate.demo.entity.InstructorDetail;
import com.darrus.hibernate.demo.entity.Review;
import com.darrus.hibernate.demo.entity.Student;

public class CreateCourseAndReviewsDemo {

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
			
			Course tempCourse = new Course("Pacman - How to score one million points");
			
			System.out.println("Saving the course");			
			session.save(tempCourse);
			System.out.println("Saved the course " + tempCourse.getTitle());
			
			Student tempStudent1 = new Student("John", "Doe", "john@test.com");
			Student tempStudent2 = new Student("Mary", "Poppins", "mary@test.com");
			
			tempCourse.addStudent(tempStudent1);
			tempCourse.addStudent(tempStudent2);
			
			session.save(tempStudent1);
			session.save(tempStudent2);
			
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}

}
