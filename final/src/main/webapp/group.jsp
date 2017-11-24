<%-- //[START all]--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>

<%-- //[START imports]--%>
<%@ page import="com.example.group.Student"%>
<%@ page import="com.example.group.Group"%>
<%@ page import="com.example.group.Course"%>
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
	pageContext.setAttribute("groupName", groupName);
    
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
		// Find this student if he is already there
		Student him = null;
		Key<Course> theCourse = Key.create(Course.class, courseName);
		List<Student> students = ObjectifyService.ofy().load().type(Student.class) //We want only Students
				.ancestor(theCourse) // Anyone in this book
				.order("-date") // Most recent first - date is indexed. 
				.list();
		if(userService.isUserAdmin() && students.isEmpty()) {
			%>
			<p>Group '${fn:escapeXml(groupName)}' has no student.</p>
			<%
	    } else {
	    	int nStudents = students.size();
	    	pageContext.setAttribute("nStudents", nStudents);
    		if(userService.isUserAdmin()){
    			%>
    			<p>Students in total:  ${fn:escapeXml(nStudents)}</p>
    			<%
    		}
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
                    him = student;
                }
            }
            if (student.group == null){
            	g = "NULL";
            } else {
            	g = student.group;
            	//TODO add notice if this is current group
            }
            //g += " _"+student.id+"_";
            pageContext.setAttribute("user", s);
            pageContext.setAttribute("user_group", g);
            if(userService.isUserAdmin()){
            	%>
            	<p>
            		<b>- ${fn:escapeXml(user)}</b> is in group: <b>${fn:escapeXml(user_group)}</b>
            	</p>
            	<%
            }		
        }
    }
	
	//Group test = ObjectifyService.ofy().load().type(Group.class).id(him.group).now();
	//pageContext.setAttribute("test", test.instructor);
	
	
	List<Group> groups = ObjectifyService.ofy()
			.load()
			.type(Group.class)
			.ancestor(theCourse)
			.list();
	
	String nGroups = " "+groups.size()+((groups.size()>1)?" groups":" group");
	pageContext.setAttribute("nGroups", nGroups);
	Group hisGroup = null;
	if(him == null){
		%>
		<p>
			Please choose your group in the following list:
		</p>
		<%
	} else {
		%>
		<p>
			Your current group is <b>${fn:escapeXml(groupName)}</b>. You may change it below. 
		</p>
		<%
	}
	%>
	<p>Available groups in “${fn:escapeXml(courseName)}”:</p>
	<p>This course has${fn:escapeXml(nGroups)}.</p>
	<%
	if (!groups.isEmpty()){
      // Look at all of our students
	        for (Group group : groups) {
	            String s = group.book;
	            if (him != null && s.equals(groupName)){
	            	hisGroup = group;
	            	s+= " (your current group)";
	            }
	            String t = " will be taught by " + group.instructor
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
					<input type="submit" value="Create a new group" />
				</div>
			</form>
			<%
			String time = "", place = "", instructor = "";
			if(hisGroup != null){
				time = hisGroup.time;
				place = hisGroup.place;
				instructor = hisGroup.instructor;
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
		}

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
