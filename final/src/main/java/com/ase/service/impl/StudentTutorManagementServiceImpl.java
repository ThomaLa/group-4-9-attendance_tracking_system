package com.ase.service.impl;

import java.util.List;

import com.ase.dao.GroupDao;
import com.ase.dao.StudentDao;
import com.ase.dao.TutorDao;
import com.ase.dao.impl.GroupDaoImpl;
import com.ase.dao.impl.StudentDaoImpl;
import com.ase.dao.impl.TutorDaoImpl;
import com.ase.entity.Group;
import com.ase.entity.Student;
import com.ase.entity.Tutor;
import com.ase.service.StudentTutorManagementService;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;

public class StudentTutorManagementServiceImpl implements StudentTutorManagementService {

	StudentDao studentDAO = new StudentDaoImpl();
	TutorDao tutorDAO = new TutorDaoImpl();
	
	@Override
	public Student getStudent(User user){
		if(user!=null)
			return studentDAO.getStudentFromDB(user.getEmail());
		else
			return null;
	}
	
	@Override
	public Tutor getTutor(User user){
		return tutorDAO.getTutorFromDB(user.getEmail());
	}


	@Override
	public void createStudent(String email) {
		Student student = studentDAO.getStudentFromDB(email);
		if (student == null) {
			Student newStudent = new Student(email);
			studentDAO.saveStudentToDB(newStudent);
		}
		
	}

	@Override
	public void createTutor(String email) {
		Tutor tutor = tutorDAO.getTutorFromDB(email);
		if (tutor == null) {
			tutor = new Tutor(email);
			tutorDAO.saveTutorToDB(tutor);
		}
		
	}
}

