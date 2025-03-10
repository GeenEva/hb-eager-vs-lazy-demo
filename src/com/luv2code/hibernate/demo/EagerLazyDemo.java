package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class EagerLazyDemo {

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
				.configure()						
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		
		
		try {
			
			session.beginTransaction();
			
			int theId = 3;
			
			Instructor tempInstructor = session.get(Instructor.class, theId);
			
			
			System.out.println("Luv2Code: the instructor is: " + tempInstructor);
					
			session.getTransaction().commit();
			
			session.close();
			
			//This code does run with eager loading, but not with lazy loading
			System.out.println("Luv2Code: the courses are: " + tempInstructor.getCourses());
			
			
			System.out.println("Luv2Code: Done!");
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		finally {
			session.close();
			factory.close();
		}


	}

}
