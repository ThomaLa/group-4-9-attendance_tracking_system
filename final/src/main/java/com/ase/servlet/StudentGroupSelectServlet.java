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
package com.ase.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ase.entity.Group;
import com.ase.entity.Student;
import com.ase.entity.Tutor;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;

/**
 * Form Handling Servlet Most of the action for this sample is in
 * webapp/guestbook.jsp, which displays the {@link Greeting}'s. This servlet has
 * one method {@link #doPost(<#HttpServletRequest req#>, <#HttpServletResponse
 * resp#>)} which takes the form data and saves it.
 */
public class StudentGroupSelectServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		Student student = ObjectifyService.ofy().cache(false).load()
				.key(Key.create(Student.class, UserServiceFactory.getUserService().getCurrentUser().getEmail())).now();

		String groupName = req.getParameter("groupName");
		String action = req.getParameter("action");	
		
		Group group = ObjectifyService.ofy().cache(false).load().type(Group.class).id(groupName).now();
		student.setGroup(group);
		group.addStudent(student);
		ObjectifyService.ofy().save().entity(group).now();
		ObjectifyService.ofy().save().entity(student).now();
		
		resp.sendRedirect("/student/group");
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		UserService userService = UserServiceFactory.getUserService();
		List<Group> groups = ObjectifyService.ofy().cache(false).load().type(Group.class).list();
		req.setAttribute("logouturl", userService.createLogoutURL(req.getRequestURI()));
		Student student =  ObjectifyService.ofy().cache(false).load().type(Student.class).id( UserServiceFactory.getUserService().getCurrentUser().getEmail()).now();
		req.setAttribute("student",student);
		req.setAttribute("groups", groups);
		if(student.getGroup()!=null){
			req.setAttribute("currentGroup", student.getGroup().getName());
		}else{
			req.setAttribute("currentGroup", "Not Assigned.");
		}
		req.getRequestDispatcher("/student/joingroup.jsp").forward(req, resp);
	}

}
