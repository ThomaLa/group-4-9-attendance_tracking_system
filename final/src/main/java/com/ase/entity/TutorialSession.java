package com.ase.entity;

import com.google.appengine.repackaged.org.joda.time.DateTime;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class TutorialSession {
	@Id public String id;
	private Ref<Group> group;
	private DateTime date;

	public TutorialSession() {

	}

	public TutorialSession(String id, Group group, DateTime date) {
		this.id = id;
		this.group = Ref.create(group);
		this.date = date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Ref<Group> getGroup() {
		return group;
	}

	public void setGroup(Ref<Group> group) {
		this.group = group;
	}

	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}
	
}
