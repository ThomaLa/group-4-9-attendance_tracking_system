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
<link type="text/css" rel="stylesheet" href="/stylesheets/main.css" />
</head>

<body>

	<p>
		Hello, ${fn:escapeXml(user.nickname)}! (You can <a
			href="${fn:escapeXml(logouturl)}">sign out</a> here.) 
			<a href="../../">Home </a> | 
			<a href="attendanceLog">Monitor attendance log </a> | 
			<a href="../../mockAndroidPi.jsp">Create attendance item</a>
	</p>

	Hello, ${fn:escapeXml(email)} Have a nice day!
	<hr />
	<h4>List of Groups!</h4>
	<form action="/tutor/group" method="post">
		<table>
		<tr>
			<th>id</th>
			<th>Group Name</th>
			<th>Day</th>
			<th>Hour</th>
			<th>Place</th>
			<th>Action</th>
</tr>
			<c:forEach var="group" items="${groups}" varStatus="count">
				<tr bgcolor="#ffffff">
					<td>${count.count}</td>
					<td>${group.name}</td>
					<td><select name="${group.name}Day">
						<option value=${group.day}>${group.day}</option>
						<option value="Monday">Monday</option>
						<option value="Tuesday">Tuesday</option>
						<option value="Wednesday">Wednesday</option>
						<option value="Thursday">Thursday</option>
						<option value="Friday">Friday</option>
						<option value="Saturday">Saturday</option>
						<option value="Sunday">Sunday</option>
					</select>
					</td>
					<td><input type="time" name="${group.name}Hour"
				value=${group.hour} /></td>
					<td><input type="text" name="${group.name}Place"
				value=${group.place} /></td>
					<td><button type="submit"
						value="${group.name}" name="edit" >Edit</button></td>
					<td><button type="submit"
						value="${group.name}" name="delete" >Delete</button></td>
				</tr>
			</c:forEach>
		</table>
		<button type="submit"
						 name="none" >Discard Changes</button>
	</form>
	<hr />
	Create a group here !
	<form action="/tutor/group" method="post">
		<div>
			<input type="text" name="create"
				value="${fn:escapeXml(groupName)}" /> <input type="submit"
				value="Reset an existing group or create a new one" />
		</div>
	</form>

</body>
</html>
<%-- //[END all]--%>
