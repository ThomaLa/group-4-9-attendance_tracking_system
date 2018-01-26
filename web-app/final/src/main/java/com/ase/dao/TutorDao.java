package com.ase.dao;

import com.ase.entity.Tutor;

public interface TutorDao {
	public Tutor getTutorFromDB(String email);
	public void saveTutorToDB(Tutor tutor);
}
