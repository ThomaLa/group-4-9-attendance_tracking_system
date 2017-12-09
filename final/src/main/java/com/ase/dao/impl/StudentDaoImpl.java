package com.ase.dao.impl;

import com.ase.dao.StudentDao;
import com.ase.entity.Student;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;

public class StudentDaoImpl implements StudentDao {

	@Override
	public Student getStudentFromDB(String email) {
		return ObjectifyService.ofy().cache(false).load().key(Key.create( Student.class,email)).now();
	}

	@Override
	public void saveStudentToDB(Student student) {
		ObjectifyService.ofy().save().entity(student).now();
	}

}
