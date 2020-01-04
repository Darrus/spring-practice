package com.darrus.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.darrus.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();

			List<Student> students = session.createQuery("from Student").getResultList();
			displayStudents(students);
			
			students = session.createQuery("from Student s where s.lastName='Doe'").getResultList();
			displayStudents(students);
			
			students = session.createQuery("from Student s where"
										+ " s.lastName='Doe' OR s.firstName='Darrus'").getResultList();
			displayStudents(students);
			
			students = session.createQuery("from Student s where"
										+ " s.email LIKE '%gmail.com'").getResultList();
			displayStudents(students);

			
			session.getTransaction().commit();
			
			System.out.println("Done saving student!");
		}
		finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> students) {
		for (Student student : students)
		{
			System.out.println(student.toString());
		}
	}

}
