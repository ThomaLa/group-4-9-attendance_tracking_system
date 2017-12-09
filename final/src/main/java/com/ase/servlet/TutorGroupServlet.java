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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ase.entity.Group;
import com.ase.entity.Tutor;
import com.google.appengine.api.users.User;
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
public class TutorGroupServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		Tutor tutor = ObjectifyService.ofy().cache(false).load()
				.key(Key.create(Tutor.class, UserServiceFactory.getUserService().getCurrentUser().getEmail())).now();

		String groupName = req.getParameter("groupName");
		String action = req.getParameter("action");

		// Same post does deletion also !
		if (action != null && action.equals("delete")) {
			// Group deleteThisGroup =
			// ObjectifyService.ofy().cache(false).load().entity(arg0)
			// .key(Key.create(Group.class,
			// req.getParameter("groupName"))).now();
			Group deleteThisGroup = ObjectifyService.ofy().cache(false).load().type(Group.class).id(req.getParameter("groupName"))
					.now();
			if (deleteThisGroup != null)
				ObjectifyService.ofy().cache(false).delete().type(Group.class).id(req.getParameter("groupName")).now();
			resp.sendRedirect("/tutor/showgroup");
			return;
		}

		Group group = new Group(groupName);
		group.setTutor(tutor);
		tutor.addGroup(group);

		ObjectifyService.ofy().save().entity(group).now();
		ObjectifyService.ofy().save().entity(tutor).now();

		resp.sendRedirect("/tutor/showgroup");
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		UserService userService = UserServiceFactory.getUserService();
		List<Group> groups = ObjectifyService.ofy().cache(false).load().type(Group.class).list();
		Tutor tutor = ObjectifyService.ofy().cache(false).load()
				.key(Key.create(Tutor.class, userService.getCurrentUser().getEmail())).now();
		String email = "Null";
		if (tutor != null) {
			email = tutor.getEmail();
		}
		req.setAttribute("logouturl", userService.createLogoutURL(req.getRequestURI()));
		req.setAttribute("email", email);
		req.setAttribute("groups", groups);

		req.getRequestDispatcher("/tutor/showgroup.jsp").forward(req, resp);
	}

}