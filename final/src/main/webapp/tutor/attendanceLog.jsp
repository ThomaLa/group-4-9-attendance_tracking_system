<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@ page import="com.ase.entity.Student"%>
<%@ page import="com.ase.entity.AttendanceItem"%>
<%@ page import="com.googlecode.objectify.ObjectifyService"%>
<%@ page import="com.googlecode.objectify.Key"%>
<%@ page import="com.ase.entity.AttendanceLog"%>
<%@ page import="com.ase.entity.Tutor"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
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

<title>Attendance Log Description</title>
</head>
<body>
	<div class="container">
		<jsp:include page="header.jsp"></jsp:include>
		<h4>Attendance Log :</h4>
		<h6 class="h6">Group: ${groupName}</h6>
		<hr />
		<h4 class="h4">Attendance Log :</h4>
		<table border="1" class="info">
			<tr>
			<th>ID</th>
				<th>Student</th>
				<th>Present</th>
			</tr>
			<c:forEach var="attendance" items="${attendances}" varStatus="count">
				<tr bgcolor="#ffffff">
					<td>${count.count }</td>
					<td>${attendance.student}</td>
					<td>${attendance.hasAttended}</td>
				</tr>
			</c:forEach>
		</table>
		<hr />
		<a href="/tutor/group/students?groupName=${groupName}"
			class="btn btn-success">Back</a>
	</div>
</body>
</html>
