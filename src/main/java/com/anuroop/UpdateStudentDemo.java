package com.anuroop;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.anuroop.entity.Student;


public class UpdateStudentDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {			
			// Get a student.
			int id = 1;
			System.out.println("Getting student with key " + id);
			
			// start a transaction
			session.beginTransaction();
			
			Student student = session.get(Student.class, id);
			System.out.println("DEBUG: " + student);
//			student.setEmail("anuroop.sirothia@gmail.com");
			
			// commit transaction
			session.getTransaction().commit();
			
			// now get a new session and start transaction
			session = factory.getCurrentSession();
			
			session.beginTransaction();
			
			Student mergedStudent = (Student)session.merge(student);
			
			// Update student email
			mergedStudent.setEmail("anuroop@gmail.com"); // This update will not work.
			
			// commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}
}