package com.ase.dao;

import java.util.List;

import com.ase.entity.Group;

public interface GroupDao {
	public void saveGroupFromDB(Group group);
	public Group getGroupFromDB(String groupName);
	public void deleteGroupFromDB(String groupName);
	public List<Group> getAllGroups();
}
