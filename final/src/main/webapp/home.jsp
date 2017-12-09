<%-- //[START all]--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@ page import="com.googlecode.objectify.LoadResult"%>

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
	<center>
		Welcome <br /> I'm a <a href="/student/group">Student</a> <br /> 
		I'm a <a href="/tutor/showgroup">Tutor</a>
		<br />
	</center>
</body>
</html>
<%-- //[END all]--%>
