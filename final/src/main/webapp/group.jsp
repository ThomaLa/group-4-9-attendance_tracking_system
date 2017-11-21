<%-- //[START all]--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>

<%-- //[START imports]--%>
<%@ page import="com.example.group.Student" %>
<%@ page import="com.example.group.Group" %>
<%@ page import="com.googlecode.objectify.Key" %>
<%@ page import="com.googlecode.objectify.ObjectifyService" %>
<%-- //[END imports]--%>

<%@ page import="java.util.List" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <link type="text/css" rel="stylesheet" href="/stylesheets/main.css"/>
</head>

<body>

<%
    String groupName = request.getParameter("groupName");
    if (groupName == null) {
    	groupName = "default";
    }
    pageContext.setAttribute("groupName", groupName);
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    if (user != null) {
        pageContext.setAttribute("user", user);
%>

<p>Hello, ${fn:escapeXml(user.nickname)}! (You can
    <a href="<%= userService.createLogoutURL(request.getRequestURI()) %>">sign out</a>.)</p>
<%
    } else {
%>
<p>Hello!
    <a href="<%= userService.createLoginURL(request.getRequestURI()) %>">Sign in</a>
    to register in a group.</p>
<%
    }
%>

<%-- //[START datastore]--%>
<%
    // Create the correct Ancestor key
      Key<Group> theBook = Key.create(Group.class, groupName);

    // Run an ancestor query to ensure we see the most up-to-date
    // view of the Students belonging to the selected Group.
      List<Student> students = ObjectifyService.ofy()
          .load()
          .type(Student.class) //We want only Inscriptions
          .ancestor(theBook)    // Anyone in this book
          .order("-date")       // Most recent first - date is indexed.
          .list();

    if (students.isEmpty()) {
%>
<p>Group '${fn:escapeXml(groupName)}' has no student.</p>
<%
    } else {
%>
<p>Students in Group '${fn:escapeXml(groupName)}':</p>
<%
      // Look at all of our greetings
        for (Student student : students) {
            String s;
            if (student.student_email == null) {
                s = "NULL";
            } else {
                s = student.student_email;
                String student_id = student.student_id;
                if (user != null && user.getUserId().equals(student_id)) {
                    s += " (You)";
                }
            }
            pageContext.setAttribute("greeting_user", s);
%>
<p><b>${fn:escapeXml(greeting_user)}</b> is in this group.</p>
<%
        }
    }
%>

<form action="/sign" method="post">
    <div><input type="submit" value="Join this group"/></div>
    <input type="hidden" name="groupName" value="${fn:escapeXml(groupName)}"/>
</form>
<%-- //[END datastore]--%>
<form action="/group.jsp" method="get">
    <div><input type="text" name="groupName" value="${fn:escapeXml(groupName)}"/></div>
    <div><input type="submit" value="Switch group"/></div>
</form>

</body>
</html>
<%-- //[END all]--%>
