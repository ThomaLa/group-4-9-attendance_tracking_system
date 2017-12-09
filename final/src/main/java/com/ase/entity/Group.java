/**
 * Copyright 2014-2015 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

//[START all]
package com.ase.entity;

import java.util.ArrayList;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

/**
 * The @Entity tells Objectify about our entity.  We also register it in
 * OfyHelper.java -- very important.
 *
 * This is never actually created, but gives a hint to Objectify about our Ancestor key.
 */
@Entity
public class Group {
	@Id public String name;
	public Ref<Tutor> tutor;
	@Index public ArrayList<Ref<Student>> students = new ArrayList<Ref<Student>>();
	@Index public ArrayList<Ref<TutorialSession>> sessions = new ArrayList<Ref<TutorialSession>>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Ref<Tutor> getTutor() {
		return tutor;
	}

	public void setTutor(Tutor tutor) {
		this.tutor = Ref.create(tutor);
	}

	public ArrayList<Ref<Student>> getStudents() {
		return students;
	}

	public void addStudent(Student student) {
		this.students.add(Ref.create(student));
	}
	
	public void removeStudent(Student student)
	{
		for(int i=0;i<students.size();i++){
			if(students.get(i).get().getEmail().equals(student.getEmail())){
				students.remove(i);
				break;
			}
		}
	}
	
	public void removeSession(TutorialSession session)
	{
		for(int i=0;i<sessions.size();i++){
			if(sessions.get(i).get().id.equals(session.id)){
				sessions.remove(i);
				break;
			}
		}
	}

	public ArrayList<Ref<TutorialSession>> getSessions() {
		return sessions;
	}

	public void addTutorialSession(TutorialSession session) {
		this.sessions.add(Ref.create(session));
	}

	/**
	 * CAUTION, here we define groupName and NOT courseName
	 */
	
	public Group(){
		
	}
	
	public Group(String groupName) {
		if( groupName != null ) {
			name = groupName;
		} else {
			name = "NULL";
		}
	}

	/**
	 * complete constructor
	 */
	public Group(String groupName, ArrayList<TutorialSession> sessions, Tutor instructor) {
		this(groupName);
		this.tutor = null;
	}
}
//[END all]
