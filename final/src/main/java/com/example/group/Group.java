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
package com.example.group;

import java.util.Date;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

/**
 * The @Entity tells Objectify about our entity.  We also register it in
 * OfyHelper.java -- very important.
 *
 * This is never actually created, but gives a hint to Objectify about our Ancestor key.
 */
@Entity
public class Group {
	@Parent Key<Course> course; 
	@Id public String book;

	//public int groupNumber = -1;
	public String time;
	public String place;
	public String instructor;

	@Index public Date date;

	/**
	 * Simple constructor
	 */
	public Group() {
		date = new Date();
		course = Key.create(Course.class, "default");
	}

	/**
	 * CAUTION, here we define groupName and NOT courseName
	 */
	public Group(String groupName) {
		this();
		if( groupName != null ) {
			book = groupName;
		} else {
			book = "NULL";
		}
	}

	/**
	 * complete constructor
	 */
	public Group(String groupName, String time, String place, String instructor) {
		this(groupName);
		//this.groupNumber = groupNumber;
		this.time = time;
		this.place = place;
		this.instructor = instructor;
	}
}
//[END all]
