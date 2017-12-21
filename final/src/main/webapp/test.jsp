<%-- //[START all]--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>

<%-- //[START imports]--%>
<%@ page import="com.ase.entity.Student"%>
<%@ page import="com.ase.entity.Group"%>
<%@ page import="com.googlecode.objectify.Key"%>
<%@ page import="com.googlecode.objectify.ObjectifyService"%>
<%-- //[END imports]--%>

<%@ page import="java.util.List"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
<link type="text/css" rel="stylesheet" href="/stylesheets/main.css" />
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
	
	Hello,  ${fn:escapeXml(email)}  Have a nice day!

</body>
</html>
<%-- //[END all]--%>
