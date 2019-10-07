package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class CreateInstructorDemo {

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
				.configure()						
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {

			Instructor tempInstructor = new Instructor("Huhhh", "Flaijwf", "maki@dori");
			tempInstructor.setInstructorDetail(new InstructorDetail("FLoopFloop", "follw Tha Monney"));
			
			session.beginTransaction();
			
			session.save(tempInstructor); 
			
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
