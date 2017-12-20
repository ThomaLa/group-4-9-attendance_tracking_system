<%-- //[START all]--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>

<%-- //[START imports]--%>
<%@ page import="com.ase.entity.AttendanceItem"%>
<%@ page import="com.ase.entity.AttendanceLog"%>
<%@ page import="com.ase.entity.Group"%>
<%@ page import="com.ase.entity.Student"%>
<%@ page import="com.ase.entity.Tutor"%>
<%@ page import="com.ase.entity.TutorialSession"%>
<%@ page import="com.googlecode.objectify.Key"%>
<%@ page import="com.googlecode.objectify.ObjectifyService"%>
<%-- //[END imports]--%>

<%@ page import="java.util.List"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
<link type="text/css" rel="stylesheet" href="/stylesheets/main.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>

<body>

	<%
	
		UserService userService = UserServiceFactory.getUserService();
	    User user = userService.getCurrentUser();

	%>

	<p>
		Hello, ${fn:escapeXml(user.nickname)}! (You can <a
			href="<%= userService.createLogoutURL(request.getRequestURI()) %>">sign
			out</a> here.)
		
	</p>
	
	<%
		Student student = ObjectifyService.ofy().cache(false).load().key(Key.create( Student.class, userService.getCurrentUser().getEmail())).now();
		String email = "Null";
		if(student!=null){
			email = student.email;
			
		}
		 pageContext.setAttribute("email", email);
	%>
	
	Hello,  ${fn:escapeXml(email)} Fill out the following information.

	<form class="mockForm" name="attendanceItemForm" action="" method="post">
		Attendance ID: <input type="text" name="attendance_id" id="attendance_id">
		<br />
		Student ID:    <input type="text" name="student_id" id="student_id">
		<br />
		Tutorial Group ID:  <input type="text" name="tutorial_group_id" id="tutorial_group_id">
		<br />
		Week ID:       <input type="text" name="week_id" id="week_id">
		<br />
		Presented:     <input type="text" name="presented" value="Yes" selectBoxOptions="No">

		<br />
		<br />

		<input type="submit" value="Confirm">
	</form>

left for testing



<script>
$( "form" ).on( "submit", function( event ) {
  //event.preventDefault();
  console.log( $( this ).serialize() );
});
</script>

</body>
</html>
<%-- //[END all]--%>
