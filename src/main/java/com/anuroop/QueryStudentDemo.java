package com.anuroop;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.anuroop.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		// Create Session factory

		try(SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class).buildSessionFactory()) {

			Transaction tx = null;
			
			// Idiom to use transactions.
			try(Session session = factory.getCurrentSession()) {

				tx = session.beginTransaction();

				List<Student> studentList = session.createQuery("FROM Student s").getResultList();
				printStudents(studentList);
				
				System.out.println("\n\nStudents with last name as Sirothia:-");
				studentList = session.createQuery("FROM Student s WHERE s.lastName = 'Sirothia'").getResultList();
				printStudents(studentList);
				
				tx.commit();
				
				
			} catch (Exception e) {
				if(tx != null) tx.rollback();
			}
		}
	}

	private static void printStudents(List<Student> studentList) {
		for(Student student : studentList) {
			System.out.println(student);
		}
	}
}