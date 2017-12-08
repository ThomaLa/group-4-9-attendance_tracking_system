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

	Hello, ${fn:escapeXml(email)} Have a nice day!
	<hr />
	<br />
	<h2>List of Groups!</h2>
	<table>
		<th>
		<td>id</td>
		<td>Group Name</td>
		</th>
		<c:forEach var="group" items="${groups}" varStatus="count">
			<tr>
				<td>${count.count}</td>
				<td align="right" bgcolor="#ffffff">${group.name}</td>
			</tr>
		</c:forEach>
	</table>
	<hr />
	Create a group here !
	<form action="/tutor/create" method="post">
		<div>
			<input type="text" name="groupName"
				value="${fn:escapeXml(groupName)}" /> <input type="submit"
				value="Reset an existing group or create a new one" />
		</div>
	</form>

</body>
</html>
<%-- //[END all]--%>
