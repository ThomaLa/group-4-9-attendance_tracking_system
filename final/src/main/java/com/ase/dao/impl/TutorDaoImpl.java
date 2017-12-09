package com.ase.dao.impl;

import com.ase.dao.TutorDao;
import com.ase.entity.Tutor;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;

public class TutorDaoImpl implements TutorDao {

	@Override
	public Tutor getTutorFromDB(String email) {
		return ObjectifyService.ofy().cache(false).load().key(Key.create(Tutor.class, email)).now();
	}

	@Override
	public void saveTutorToDB(Tutor tutor) {
		ObjectifyService.ofy().save().entity(tutor).now();
	}

}
