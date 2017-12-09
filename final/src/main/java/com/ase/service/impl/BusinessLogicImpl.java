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
import com.ase.service.BusinessLogic;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;

public class BusinessLogicImpl implements BusinessLogic {

	StudentDao studentDAO = new StudentDaoImpl();
	TutorDao tutorDAO = new TutorDaoImpl();
	GroupDao groupDAO = new GroupDaoImpl();

	@Override
	public void joinStudentToGroup(User user, String groupName) {
		Student student = studentDAO
				.getStudentFromDB(UserServiceFactory.getUserService().getCurrentUser().getEmail());
		Group group = groupDAO.getGroupFromDB(groupName);
		student.setGroup(group);
		group.addStudent(student);

		groupDAO.saveGroupFromDB(group);
		studentDAO.saveStudentToDB(student);
	}
	
	@Override
	public Student getStudent(User user){
		if(user!=null)
			return studentDAO.getStudentFromDB(user.getEmail());
		else
			return null;
	}
	
	@Override
	public List<Group> getAllGroups(){
		return groupDAO.getAllGroups();
	}
	
	@Override
	public Tutor getTutor(User user){
		return tutorDAO.getTutorFromDB(user.getEmail());
	}

	@Override
	public void createGroup(String groupName, User user) {
		// to create group
		
		Tutor tutor = this.getTutor(user);
		Group group = new Group(groupName);
		group.setTutor(tutor);
		tutor.addGroup(group);
		
		tutorDAO.saveTutorToDB(tutor);
		groupDAO.saveGroupFromDB(group);
	}

	@Override
	public void deleteGroup(String groupName) {
		Group groupToDelete = groupDAO.getGroupFromDB(groupName);
		if (groupToDelete != null)
			groupDAO.deleteGroupFromDB(groupName);
		
	}

	@Override
	public void createStudent(String email) {
		Student student = studentDAO.getStudentFromDB(email);
		if (student == null) {
			Student newStudent = new Student(email);
			studentDAO.saveStudentToDB(student);
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

