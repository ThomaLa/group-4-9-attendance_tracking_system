package com.ase.service;

import java.util.List;

import com.ase.entity.Group;
import com.ase.entity.Student;
import com.ase.entity.Tutor;
import com.google.appengine.api.users.User;

public interface BusinessLogic {

	void joinStudentToGroup(User user, String group);
	public Student getStudent(User user);
	public List<Group> getAllGroups();
	public Tutor getTutor(User user);
	public void createGroup(String groupName,User user);
	public void createStudent(String email);
	public void createTutor(String email);
	public void deleteGroup(String groupName);
}
