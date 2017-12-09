package com.ase.restlet.resource.impl;

import org.restlet.resource.ServerResource;

import com.ase.entity.Student;
import com.ase.restlet.resource.StudentResource;
import com.ase.service.BusinessLogic;
import com.ase.service.impl.BusinessLogicImpl;
import com.google.appengine.api.users.User;

/**
 * The server side implementation of the Restlet resource.
 */
public class StudentServerResource extends ServerResource implements StudentResource {

	BusinessLogic businessLogic = new BusinessLogicImpl();
	
	@Override
    public Student retrieve() {
		String studentId = getAttribute("studentEmail");
		if(studentId!= null && !studentId.equals(""))
			return businessLogic.getStudent(new User(studentId, "localhost"));
		return null;
    }

	@Override
    public void store(Student student) {
		businessLogic.createStudent(student.getEmail());
    }
}