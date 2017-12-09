<%-- //[START all]--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>

<%-- //[START imports]--%>
<%@ page import="com.ase.entity.Student"%>
<%@ page import="com.ase.entity.Group"%>
<%@ page import="com.ase.entity.Course"%>
<%@ page import="com.ase.entity.Tutor"%>
<%@ page import="com.googlecode.objectify.Key"%>
<%@ page import="com.googlecode.objectify.ObjectifyService"%>
<%-- //[END imports]--%>

<%@ page import="java.util.List"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
<link type="text/css" rel="stylesheet" href="/stylesheets/main.css" />
</head>

<body>

	<%
	String courseName = request.getParameter("courseName");
	if(courseName == null){
		courseName = "default";
	}
	String groupName = request.getParameter("groupName");
    if (groupName == null) {
    	groupName = "NULL";
    }

	pageContext.setAttribute("courseName", courseName);
	
	UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    if (user != null) {
        pageContext.setAttribute("user", user);
        
        //pageContext.setAttribute("admin", userService.isUserAdmin());
%>

	<p>
		Hello, ${fn:escapeXml(user.nickname)}! (You can <a
			href="<%= userService.createLogoutURL(request.getRequestURI()) %>">sign
			out</a> here.)
	</p>
	
	<%
		// fetch student and his course in the database
		Key<Course> theCourse = Key.create(Course.class, courseName);
		Student thisStudent = ObjectifyService.ofy().cache(false).load().key(Key.create(theCourse, Student.class, user.getUserId())).now();
		Group hisGroup = null;
		if(thisStudent != null){
			hisGroup = thisStudent.getGroup(theCourse);
		}
		pageContext.setAttribute("thisStudent", thisStudent);
		pageContext.setAttribute("hisGroup", hisGroup);
	    
		if(thisStudent == null || userService.isUserAdmin()){// need to choose a group or to update them
			if(thisStudent == null){
				%>
				<p><b>
				Please choose a course in the list below:
				</b></p>
				<%
			} else {
				if(hisGroup != null)
					groupName = hisGroup.book;
				pageContext.setAttribute("groupName", groupName);

				%>
				<p>
					Your current group is <b>${fn:escapeXml(groupName)}</b>. You may change it below. 
				</p>
				<%
			}
		
			List<Group> groups = ObjectifyService.ofy()
					.load()
					.type(Group.class)
					.ancestor(theCourse)
					.list();
			
			String nGroups = " "+groups.size()+((groups.size()>1)?" groups":" group");
			pageContext.setAttribute("nGroups", nGroups);
			
			%>
			<p>Available groups in “${fn:escapeXml(courseName)}”:</p>
			<p>This course has${fn:escapeXml(nGroups)}.</p>
			<%
			if (!groups.isEmpty()){
		      // Look at all of our students
			        for (Group group : groups) {
			            String s = group.book;
			            if (thisStudent != null && s.equals(groupName)){
			            	s+= " (your current group)";
			            }
			            String t = " will be taught by " + group.tutor.name
			            		+ " at time " + group.time
			            		+ " at place " + group.place;
			            
			            /*if (group.groupNumber == -1) {
			                s = "NULL";
			            } else {
			                s = "Group "+group.groupNumber;
			                //TODO show which group he's in
			            }*/
			            pageContext.setAttribute("other_group", s);
			            pageContext.setAttribute("other_group_description", t);
			            %>
			            <p><b>  - ${fn:escapeXml(other_group)}</b>${fn:escapeXml(other_group_description)}</p>
			            <%
			            
			            	%>
			            	<p>
			            		<form action="/sign" method="post">
			            		<div>
			            			<input type="submit" value="Join this group" />
			            		</div>
			            		<input type="hidden" name="groupName"
			            			value="${fn:escapeXml(other_group)}" />
			            	</form>
			            	</p>
			            	<%
			            
			
			    		}
					}
					// view of the Students belonging to the selected Group. 
				if(userService.isUserAdmin()){
					%>
					<form action="/create" method="post">
						<div>
							<input type="text" name="groupName"
								value="${fn:escapeXml(groupName)}" />
						</div>
						<div>
							<input type="submit" value="Reset an existing group or create a new one" />
						</div>
					</form>
					<%
					String time = "", place = "", instructor = "";
					if(hisGroup != null){
						time = hisGroup.time;
						place = hisGroup.place;
						instructor = hisGroup.tutor.name;
					}
					pageContext.setAttribute("groupTime",time);
					pageContext.setAttribute("groupPlace",place);
					pageContext.setAttribute("groupInstructor",instructor);
					%>
					<form action="/edit" method="post">
						<p>
							Edit current group (${fn:escapeXml(groupName)}) time/place/instructor
						</p>
						<div>
							<input type="text" name="groupTime"
								value="${fn:escapeXml(groupTime)}" />
							<input type="text" name="groupPlace"
								value="${fn:escapeXml(groupPlace)}" />
							<input type="text" name="groupInstructor"
								value="${fn:escapeXml(groupInstructor)}" />
						</div>
						<input type="hidden" name="groupName"
			            			value="${fn:escapeXml(groupName)}" />
						<div>
							<input type="submit" value="Save changes" />
						</div>
					</form>
					<%
					
						List<Student> students = ObjectifyService.ofy().load().type(Student.class) //We want only Students
						.ancestor(theCourse) // Anyone in this book
						.order("-date") // Most recent first - date is indexed. 
						.list();
				if(students.isEmpty()) {
					%>
					<p>Group '${fn:escapeXml(groupName)}' has no student.</p>
					<%
			    } else {
			    	int nStudents = students.size();
			    	pageContext.setAttribute("nStudents", nStudents);
		    		
		    			%>
		    			<p>Students in total:  ${fn:escapeXml(nStudents)}</p>
		    			<%
		    		
		      // Look at all of our students
			        for (Student student : students) {
			            String s,g;
			            if (student.student_email == null) {
			                s = "NULL";
			            } else {
			                s = student.student_email;
			                String student_id = student.id;
			                if (user != null && user.getUserId().equals(student_id)) {
			                    s += " (You)";
			                }
			            }
			            if (student.group == null){
			            	g = "NULL";
			            } else {
			            	g = student.group;
			            }
			            //g += " _"+student.id+"_";
			            pageContext.setAttribute("user", s);
			            pageContext.setAttribute("user_group", g);
			            	%>
			            	<p>
			            		<b>- ${fn:escapeXml(user)}</b> is in group: <b>${fn:escapeXml(user_group)}</b>
			            	</p>
			            	<%
			            		
		            }
		        }
				}
			%>
			<%
		}else{
			String time = "undefined", place = "undefined", instructor="undefined";
			if(hisGroup != null){
				if(hisGroup.time.length()>0){
					time = hisGroup.time;
				}
				if(hisGroup.place.length()>0){
					place = hisGroup.place;
				}
				if(hisGroup.tutor.name.length()>0){
					instructor = hisGroup.tutor.name;
				}
				groupName = hisGroup.book;
			}
			pageContext.setAttribute("hgt", time);
			pageContext.setAttribute("hgp", place);
			pageContext.setAttribute("hgi", instructor);
			pageContext.setAttribute("groupName", groupName);
			%><p><i>
			You have already signed in to a group: </i>'${fn:escapeXml(groupName)}'
		</p>
		<p>
		<b>Group details:</b>
		</p>
		<p>
		Time: <b>${fn:escapeXml(hgt)}</b>
		</p>
		<p>
		Place: <b>${fn:escapeXml(hgp)}</b>
		</p>
		<p>
		Instructor: <b>${fn:escapeXml(hgi)}</b>
		</p>
		<%
			pageContext.setAttribute("test", thisStudent);
		}	
		
		
    
		
	//Group test = ObjectifyService.ofy().load().type(Group.class).id(him.group).now();
	
	
	
	

    } else {
%>
	<p>
		Hello! <a
			href="<%= userService.createLoginURL(request.getRequestURI()) %>">Please
			sign in</a> to register in a group.
	</p>
	<%
    }
%>
	
</body>
</html>
<%-- //[END all]--%>