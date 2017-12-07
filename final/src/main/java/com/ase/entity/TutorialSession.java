package com.ase.entity;

import com.google.appengine.repackaged.org.joda.time.DateTime;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class TutorialSession {
	@Id public String id;
	public String place;
	public DateTime date;
	
}
