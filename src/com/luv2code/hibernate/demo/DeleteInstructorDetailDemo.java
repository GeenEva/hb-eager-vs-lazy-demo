package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class DeleteInstructorDetailDemo {

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
				.configure()						
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			
			int id = 5;
			
			session.beginTransaction();
			
			InstructorDetail temp = session.get(InstructorDetail.class, id);
			
			//break bi-directional link between instructor and instructorDetail
			temp.getInstructor().setInstructorDetail(null);;
			
			session.delete(temp);
			
			session.getTransaction().commit();
			
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		finally {
			factory.close();
		}


	}

}
