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

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Load;

@Entity
public class Student{

  @Id public String email;
  public @Load Ref<Group> group;

  public Student(){
	  
  }
  
  public Student(String email) {
	  this.email = email;
  }
  
  public void setGroup(Group group){
	  this.group = Ref.create(group);
  }
  public Group getGroup() {
	  if(group==null)
		  return null;
	  return group.get();
  }
  
  public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
