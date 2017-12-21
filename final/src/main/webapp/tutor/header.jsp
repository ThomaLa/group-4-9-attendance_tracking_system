<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@ page import="com.ase.entity.Student"%>
<%@ page import="com.googlecode.objectify.ObjectifyService"%>
<%@ page import="com.googlecode.objectify.Key"%>
<%@ page import="com.ase.entity.AttendanceLog"%>

<nav class="navbar navbar-light bg-faded">
<p>
	<a href="../../">Home </a> | <a href="attendanceLog">Monitor
		attendance log </a> | <a href="mockAndroidPi.jsp">Create
		attendance item</a> | <a href="${fn:escapeXml(logouturl)}">Sign Out</a> 
</p>
</nav>
<br/>
<p>
Hello, ${fn:escapeXml(email)} Have a nice day!
</p>
<hr />