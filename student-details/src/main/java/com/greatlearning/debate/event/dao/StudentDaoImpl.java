package com.greatlearning.debate.event.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.greatlearning.debate.event.*;
import com.greatlearning.debate.event.entity.Student;

@Repository

public class StudentDaoImpl implements IStudentDao {
@Autowired
	private SessionFactory sessionFactory;

	private Session session;

	public StudentDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}
	}

	
	public Student insertStudentDetails(Student student) {

		// Transaction txn = session.beginTransaction();
		session.save(student);
		System.out.println("student info saved." + student);
		// txn.commit();
		return student;
	}

	

	
	public boolean deleteStudentDetails(int studentId) {
		
		Student retrievedObject=findStudent(studentId);
		
		session.getTransaction().begin();
		System.out.println("deleting student country is: "+retrievedObject.getCountry());
		session.delete(retrievedObject);
		
		session.getTransaction().commit();
		
		if(retrievedObject!=null) {
			return true;
		}
		return false;
		
	}
	 public Student updateCustomerDetails(int id, Student updatedStudent){
		 session = sessionFactory.openSession();	
	        session.saveOrUpdate(updatedStudent);
	            return updatedStudent;
	 }
 
	
	public Student findStudent(int studentId) {
		
		return session.find(Student.class, new Integer(studentId));
	}
	public Student getStudent(int studentId) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Student.class, studentId);
	}

	
	public List<Student> retrieveStudentDetails() {
		return session.createQuery("Select s from Student s", Student.class).getResultList();
	}

}
