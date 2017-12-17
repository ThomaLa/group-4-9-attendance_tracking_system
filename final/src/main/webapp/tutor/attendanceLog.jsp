<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="com.ase.entity.Student" %>
<%@ page import="com.googlecode.objectify.ObjectifyService" %>
<%@ page import="com.googlecode.objectify.Key" %>
<%@ page import="com.ase.entity.AttendanceLog" %>
<%@ page import="com.ase.entity.Tutor" %><%--
  Created by IntelliJ IDEA.
  User: MarcoSalazar
  Date: 17/12/17
  Time: 13:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <link type="text/css" rel="stylesheet" href="/stylesheets/main.css" />
        <title>Attendance Log Description</title>
    </head>
    <body>
        <p>
            Hello, ${fn:escapeXml(user.nickname)}! (You can <a
                href="${fn:escapeXml(logouturl)}">sign out</a> here.)
        </p>

        Hello, ${fn:escapeXml(email)} Have a nice day!
        <hr />
        <h4>Attendance Log</h4>
    </body>
</html>
