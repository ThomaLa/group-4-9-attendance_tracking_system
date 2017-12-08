package com.ase.entity;

import java.util.ArrayList;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Load;

@Entity
public class Tutor{
	@Id public String email;

	public ArrayList<String> groupNames = new ArrayList<String>();
	
	public Tutor(){
	}
	
	public Tutor(String email){
		this.email = email;
	}
	
	private boolean ifGroupExistAlready(String groupName){
		for(int i=0;i<groupNames.size();i++){
			if(groupNames.get(i).equals(groupName)){
				return true;
			}
		}
		return false;
	}
	
	public void addGroup(String groupName){
		if(!ifGroupExistAlready(groupName))
			groupNames.add(groupName);
	}
	
	public void addGroup(Group group){
		if(!ifGroupExistAlready(group.getName()))
			groupNames.add(group.getName());
	}
	
	public void removeGroupRef(Group group)
	{
		for(int i=0;i<groupNames.size();i++){
			if(groupNames.get(i).equals(group.getName())){
				groupNames.remove(i);
				break;
			}
		}
	}
	
	public void removeGroupRef(String name)
	{
		for(int i=0;i<groupNames.size();i++){
			if(groupNames.get(i).equals(name)){
				groupNames.remove(i);
				break;
			}
		}
	}
	
	
	public ArrayList<String> getGroupNames() {
		return groupNames;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
