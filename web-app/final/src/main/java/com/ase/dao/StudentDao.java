package com.ase.dao;

import com.ase.entity.Group;
import com.ase.entity.Student;

public interface StudentDao{
	public Student getStudentFromDB(String email);
	public void saveStudentToDB(Student student);
}
