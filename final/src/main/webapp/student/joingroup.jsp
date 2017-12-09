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
	</p>

	Hello, ${fn:escapeXml(student.email)} Have a nice day!
	<hr />
	
		Current group is:  ${fn:escapeXml(currentGroup)}
	<hr />
	<h4>Join a group!</h4>
	<form action="/student/group" method="post">
		<table>
		<tr>
			<th>id</th>
			<th>Group Name</th>
			<th>Action</th>
</tr>
			<c:forEach var="group" items="${groups}" varStatus="count">
				<tr bgcolor="#ffffff">
					<td>${count.count}</td>
					<td>${group.name}</td>
					<td><button type="submit"
						value="${group.name}" name="groupName" >Join</button></td>
				</tr>
			</c:forEach>
		</table>
		<input type="hidden" name="action" value="join" />
	</form>
	<hr />

</body>
</html>
<%-- //[END all]--%>
