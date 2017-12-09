package com.ase.dao.impl;

import java.util.List;

import com.ase.dao.GroupDao;
import com.ase.entity.Group;
import com.googlecode.objectify.ObjectifyService;

public class GroupDaoImpl implements GroupDao{

	@Override
	public void deleteGroupFromDB(String groupName) {
		ObjectifyService.ofy().cache(false).delete().type(Group.class).id(groupName).now();
	}

	@Override
	public void saveGroupFromDB(Group group) {
		ObjectifyService.ofy().save().entity(group).now();
	}

	@Override
	public Group getGroupFromDB(String groupName) {
		return ObjectifyService.ofy().cache(false).load().type(Group.class).id(groupName).now();
	}

	@Override
	public List<Group> getAllGroups() {
		return ObjectifyService.ofy().cache(false).load().type(Group.class).list();
	}
	
}
