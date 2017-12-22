<%-- //[START all]--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>

<%-- //[START imports]--%>
<%@ page import="com.ase.entity.Tutor"%>
<%@ page import="com.ase.entity.Group"%>
<%@ page import="com.googlecode.objectify.Key"%>
<%@ page import="com.googlecode.objectify.ObjectifyService"%>
<%-- //[END imports]--%>

<%@ page import="java.util.List"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>


<html>
<head>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
	integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"
	integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"
	integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"
	integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn"
	crossorigin="anonymous"></script>

<title>Create/Modify Groups</title>
</head>

<body>
	<div class="container">
		
		<jsp:include page="header.jsp"></jsp:include>
		<h3 class="h3">Group: ${group.name}</h3>
		<hr />
		<h4 class="h4"">Attendance Log :</h4>
		<table class="info">
			<tr>
				<th>Week</th>
				<th>Action</th>
			</tr>
			<c:forEach var="attendance" items="${attendanceLogs}" varStatus="count">
				<tr bgcolor="#ffffff">
					<td>${attendance.weekId}</td>
					<td><a href="/tutor/attendancelog?id=${attendance.attendanceLogId}&groupName=${group.name}" class="btn btn-success">View</a></td>
				</tr>
			</c:forEach>
			</table>
			<hr />
		<h4 class="h4"">List of Students !</h4>
		<table class="info">
			<tr>
				<th>ID</th>
				<th>Name</th>
			</tr>
			<c:forEach var="student" items="${students}" varStatus="count">
				<tr bgcolor="#ffffff">
					<td>${count.count}</td>
					<td>${student.email}</td>
				</tr>
			</c:forEach>
		</table>
		<br/>
		<a href="/tutor/showgroup" class="btn btn-success">Back</a>
	</div>
</body>
</html>
<%-- //[END all]--%>
