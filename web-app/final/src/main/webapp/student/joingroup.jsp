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
</head>

<body>
	<div class="container">

		<jsp:include page="header.jsp"></jsp:include>

		Current group is: ${fn:escapeXml(currentGroup)}
		<hr />
		<h4>Join a group!</h4>
		<form action="/student/group" method="post">
			<table class="info">
				<tr>
					<th>ID</th>
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
						<td>${group.day}</td>
						<td>${group.hour}</td>
						<td>${group.place}</td>
						<td><button type="submit" value="${group.name}"
								name="groupName" class="btn btn-success">Join</button></td>
					</tr>
				</c:forEach>
			</table>
			<input type="hidden" name="action" value="join"/>
		</form>
		<hr />
	</div>
</body>
</html>
<%-- //[END all]--%>
