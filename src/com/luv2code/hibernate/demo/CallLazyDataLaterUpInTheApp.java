package com.luv2code.hibernate.demo;

import org.hibernate.query.Query;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class CallLazyDataLaterUpInTheApp {

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
            
            // get the instructor from db
            int theId = 3;
            Instructor tempInstructor = session.get(Instructor.class, theId);                    
            
            System.out.println("luv2code: Instructor: " + tempInstructor);    
            
         
            session.getTransaction().commit();
            session.close();

            System.out.println("\nluv2code: The session is now closed!\n");

            //
            // THIS HAPPENS SOMEWHERE ELSE / LATER IN THE PROGRAM
            // YOU NEED TO GET A NEW SESSION
            //
            
            System.out.println("\n\nluv2code: Opening a NEW session \n");

            session = factory.getCurrentSession();
            
            session.beginTransaction();
            
            // get courses for a given instructor
            Query<Course> query = session.createQuery("SELECT c FROM Course c "
                                                    + "WHERE c.instructor.id=:theInstructorId",    
                                                    Course.class);
            
            query.setParameter("theInstructorId", theId);
            
            List<Course> tempCourses = query.getResultList();
            
            System.out.println("tempCourses: " + tempCourses);
            
            // now assign to instructor object in memory
            tempInstructor.setCourses(tempCourses);
            
            System.out.println("luv2code: Courses: " + tempInstructor.getCourses());
            
            session.getTransaction().commit();
            
            System.out.println("luv2code: Done!");
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		finally {
			session.close();
			factory.close();
		}


	}

}
