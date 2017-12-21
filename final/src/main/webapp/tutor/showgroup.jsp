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
	<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>

<title>Create/Modify Groups</title>
</head>

<body>
<div class="container">
	<jsp:include page="header.jsp"></jsp:include>
	
		<h4 class="h4"">List of Groups!</h4>
		<form action="/tutor/group" method="post" class="form-group">
			<table class="info">
				<tr>
					<th>ID</th>
					<th>Group</th>
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
						</select></td>
						<td><input type="time" name="${group.name}Hour"
							value=${group.hour } /></td>
						<td><input type="text" name="${group.name}Place"
							value=${group.place } /></td>
						<td><button type="submit" value="${group.name}" class="btn-success" name="edit">Edit</button></td>
						<td><button type="submit" value="${group.name}" class="btn-success" name="delete">Delete</button></td>
					</tr>
				</c:forEach>
			</table>
			<button type="submit" name="none" class="btn-warning">Discard
				Changes</button>
		</form>
		<hr />
		Create a group here !
		<form action="/tutor/group" method="post" class="form-group">
			<div>
				<input type="text" name="create" value="${fn:escapeXml(groupName)}" />
				<input class="btn-success" type="submit"
					value="Reset an existing group or create a new one" />
			</div>
		</form>
	</div>
</body>
</html>
<%-- //[END all]--%>
