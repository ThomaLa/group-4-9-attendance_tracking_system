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
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
	integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ"
	crossorigin="anonymous">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>

<body class='container'>

	<%
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
	%>
<jsp:include page="header.jsp"></jsp:include>

	<%
		Student student = ObjectifyService.ofy().cache(false).load()
				.key(Key.create(Student.class, userService.getCurrentUser().getEmail())).now();
		String email = "Null";
		if (student != null) {
			email = student.email;

		}
		pageContext.setAttribute("email", email);
	%>

	Hello, ${fn:escapeXml(email)}. Please fill out the missing information.

	<hr />

	<form class="mockForm" name="attendanceItemForm" class="form-group">
		<table border="0">
			<tr>
				<td>Attendance ID:</td>
				<td>10483084049</td>
			</tr>
			<tr>
				<td>Student Name:</td>
				<td><input type="text" name="student_id" id="student_id" value="Max Mustermann"></td>
			</tr>
			<tr>
				<td>Tutorial Group:</td>
				<td><select name="group_id" id="group_id">
						<option value="id0">Group 0</option>
						<option value="id1">Group 1</option>
						<option value="id2">Group 2</option>
						<option value="id3">Group 3</option>
						<option value="id4">Group 4</option>
						<option value="id5">Group 5</option>
					</select></td>
			</tr>
			<tr>
				<td>Week:</td>
				<td><select name="week_id" id="week_id">
						<option value="id0">Jan. 01 - 07</option>
						<option value="id1">Jan. 08 - 14</option>
						<option value="id2">Jan. 15 - 21</option>
						<option value="id3">Jan. 22 - 28</option>
						<option value="id4">Jan. 29 - Feb. 4</option>
						<option value="id5">Feb. 5 - 11</option>
					</select></td>
			</tr>
			<tr>
				<td>Presented:</td>
				<td><select name="presented" id="presented">
						<option value="1">Yes</option>
						<option value="0">No</option>
					</select></td>
			</tr>
		</table>
		<br /> <input type="button" value="Confirm" class="btn-success" onclick="callApi()">
	</form>

	left for testing



	<script>
		function callApi() {
			$.ajax({
				type : "PUT",
				url : "/api/attendance/" + $('#attendance_id').val(),
				dataType : "xml",
				contentType : "application/xml",
				data : "<AttendanceItemRequest>" + "<studentId>"
						+ $('#student_id').val() + "</studentId>"
						+ "<hasAttended>true</hasAttended>"
						+ "<time>12th May 1992</time>"
						+ "</AttendanceItemRequest>",
				success : function(res) {
					alert(res);
				},
				error : function(res) {
					alert("XML: not working! " + res.statusText);
				}
			});
		}
	</script>

</body>
</html>
<%-- //[END all]--%>
