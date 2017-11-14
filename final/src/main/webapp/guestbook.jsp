<%-- //[START all]--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>

<%-- //[START imports]--%>
<%@ page import="com.example.guestbook.Greeting" %>
<%@ page import="com.example.guestbook.Guestbook" %>
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
    String guestbookName = request.getParameter("guestbookName");
    if (guestbookName == null) {
        guestbookName = "default";
    }
    pageContext.setAttribute("guestbookName", guestbookName);
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
      Key<Guestbook> theBook = Key.create(Guestbook.class, guestbookName);

    // Run an ancestor query to ensure we see the most up-to-date
    // view of the Greetings belonging to the selected Guestbook.
      List<Greeting> greetings = ObjectifyService.ofy()
          .load()
          .type(Greeting.class) // We want only Greetings
          .ancestor(theBook)    // Anyone in this book
          .order("-date")       // Most recent first - date is indexed.
          //.limit(10)             // Only show 5 of them.
          .list();

    if (greetings.isEmpty()) {
%>
<p>Group '${fn:escapeXml(guestbookName)}' has no student.</p>
<%
    } else {
%>
<p>Students in Group '${fn:escapeXml(guestbookName)}':</p>
<%
      // Look at all of our greetings
        for (Greeting greeting : greetings) {
            pageContext.setAttribute("greeting_content", greeting.content);
            String author;
            if (greeting.author_email == null) {
                author = "NULL";
            } else {
                author = greeting.author_email;
                String author_id = greeting.author_id;
                if (user != null && user.getUserId().equals(author_id)) {
                    author += " (You)";
                }
            }
            pageContext.setAttribute("greeting_user", author);
%>
<p><b>${fn:escapeXml(greeting_user)}</b> is in this group.</p>
<%
        }
    }
%>

<form action="/sign" method="post">
    <div><input type="submit" value="Join this group"/></div>
    <input type="hidden" name="guestbookName" value="${fn:escapeXml(guestbookName)}"/>
</form>
<%-- //[END datastore]--%>
<form action="/guestbook.jsp" method="get">
    <div><input type="text" name="guestbookName" value="${fn:escapeXml(guestbookName)}"/></div>
    <div><input type="submit" value="Switch group"/></div>
</form>

</body>
</html>
<%-- //[END all]--%>
