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
package com.ase.service;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.ase.entity.AttendanceItem;
import com.ase.entity.AttendanceLog;
import com.ase.entity.Group;
import com.ase.entity.Student;
import com.ase.entity.Tutor;
import com.googlecode.objectify.ObjectifyService;

/**
 * OfyHelper, a ServletContextListener, is setup in web.xml to run before a JSP is run.  This is
 * required to let JSP's access Ofy.
 **/
public class OfyHelper implements ServletContextListener {
  public void contextInitialized(ServletContextEvent event) {
    ObjectifyService.register(Group.class);
    ObjectifyService.register(Student.class);
    ObjectifyService.register(Tutor.class);
    ObjectifyService.register(AttendanceLog.class);
    ObjectifyService.register(AttendanceItem.class);
  }

  public void contextDestroyed(ServletContextEvent event) {
    // App Engine does not currently invoke this method.
  }
}
//[END all]
