package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class CreateCoursesDemo {

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
			
		//	Instructor tempInstructor = session.get(Instructor.class, 3);
			
			Course course = new Course("Flapperpappen");
			Course course2 = new Course("Alibaba en veertig poffertjes");

			session.save(course);
			session.save(course2);
			
			session.getTransaction().commit();
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		finally {
			session.close();
			factory.close();
		}


	}

}
