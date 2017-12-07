package com.ase.entity;

import java.util.ArrayList;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Load;

@Entity
public class Tutor{
	@Id public String name;
	public @Load Ref<ArrayList<Group>> group;
	
	public void setGroup(ArrayList<Group> group){
		Ref.create(group);
	}
	
	public ArrayList<Group> getGroup() {
		return group.get();
	}

}
