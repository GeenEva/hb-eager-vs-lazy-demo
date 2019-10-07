package com.luv2code.hibernate.demo;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class FetchJoinDemo {

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
			
			Query<Instructor> query = 
					session.createQuery("SELECT i FROM Instructor i "
								+ "JOIN FETCH i.courses "
								+ "WHERE i.id=:theInstructorId ", 
								Instructor.class);
			
			query.setParameter("theInstructorId", theId);
			
			Instructor tempInstructor = query.getSingleResult();
			
			System.out.println("Luv2Code: the instructor is: " + tempInstructor);
					
			session.getTransaction().commit();
			
			session.close();
			

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
