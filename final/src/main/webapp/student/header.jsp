<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<nav class="navbar navbar-light bg-faded">
<p>

 <a href="../../">Home</a> |
	 <a href="${fn:escapeXml(logouturl)}">Sign Out</a> 
</p>
</nav>
<br />
Hello, ${fn:escapeXml(student.email)} Have a nice day!

<hr />