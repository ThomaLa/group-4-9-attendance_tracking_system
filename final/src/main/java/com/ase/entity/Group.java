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
import java.util.Date;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;

/**
 * The @Entity tells Objectify about our entity.  We also register it in
 * OfyHelper.java -- very important.
 *
 * This is never actually created, but gives a hint to Objectify about our Ancestor key.
 */
@Entity
public class Group {
	@Id public String name;

	//public int groupNumber = -1;
	public String place;
	public Ref<Tutor> tutor;
	public @Load Ref<ArrayList<Student>> students;
	public @Load Ref<ArrayList<TutorialSession>> sessions;

	@Index public Date date;


	/**
	 * CAUTION, here we define groupName and NOT courseName
	 */
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
		this.sessions.create(sessions);
		this.tutor = null;
	}
}
//[END all]
