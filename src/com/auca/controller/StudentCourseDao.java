package com.auca.controller;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.auca.domain.StudentCourse;
import com.auca.view.HibernateUtil;

public class StudentCourseDao {

	public void saveStudentCourse(StudentCourse studentCourse) {
		
		Transaction transaction = null;
		
		try {
			
			Session session = HibernateUtil.getSession().openSession();
			transaction = session.beginTransaction();
			session.save(studentCourse);
			transaction.commit();
			session.close();
			
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		
	}
	
}
