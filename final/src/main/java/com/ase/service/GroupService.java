package com.ase.service;

import java.util.List;

import com.ase.entity.Group;
import com.ase.entity.Student;
import com.ase.entity.Tutor;
import com.google.appengine.api.users.User;

public interface GroupService {
	void joinStudentToGroup(User user, String group);
	public List<Group> getAllGroups();
	public void createGroup(String groupName,User user);
	public void deleteGroup(String groupName);
	public void editGroup(String editInput);
}
