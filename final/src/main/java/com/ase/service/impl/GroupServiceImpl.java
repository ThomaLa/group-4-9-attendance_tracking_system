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
import com.ase.service.GroupService;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.Ref;

public class GroupServiceImpl implements GroupService {

	StudentDao studentDAO = new StudentDaoImpl();
	TutorDao tutorDAO = new TutorDaoImpl();
	GroupDao groupDAO = new GroupDaoImpl();

	@Override
	public void joinStudentToGroup(User user, String groupName) {
		Student student = studentDAO
				.getStudentFromDB(UserServiceFactory.getUserService().getCurrentUser().getEmail());
		Group group = groupDAO.getGroupFromDB(groupName);
		for(Ref<Student> currentStudent:group.students ){
			//Not allow student to join the same group multiple times.
			if(currentStudent.get().getEmail().equals(student.email)){
				return;
			}
		}
		
		student.setGroup(group);
		group.addStudent(student);

		groupDAO.saveGroupFromDB(group);
		studentDAO.saveStudentToDB(student);
	}
	
	
	@Override
	public List<Group> getAllGroups(){
		return groupDAO.getAllGroups();
	}

	@Override
	public void createGroup(String groupName, User user) {
		// to create group
		
		Tutor tutor = tutorDAO.getTutorFromDB(user.getEmail());
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
	public void editGroup(String groupName,
			String groupDay, String groupHour, String groupPlace) {
		Group groupToEdit = groupDAO.getGroupFromDB(groupName);
		//TODO check that students remain inside
		groupToEdit.setDay(groupDay);
		groupToEdit.setHour(groupHour);
		groupToEdit.setPlace(groupPlace);
		groupDAO.saveGroupFromDB(groupToEdit);
	}

}

