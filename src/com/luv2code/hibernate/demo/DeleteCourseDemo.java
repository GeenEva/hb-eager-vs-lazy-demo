package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class DeleteCourseDemo {

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
			
			Course tempCourse = session.get(Course.class, 12);
			
			session.delete(tempCourse);
			
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
