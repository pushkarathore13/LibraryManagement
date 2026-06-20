<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);

if(session.getAttribute("student_email") == null){
	response.sendRedirect("login.jsp");
	return;
}
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<title>Change Password</title>
</head>
<body>

<h2>Change Password</h2>

<%
String message = (String) request.getAttribute("message");

if(message != null) {
%>
<h3 style="color:red;"><%= message %></h3>
<%
}
%>

<form action="changePassword" method="post">

	Old Password:
	<input type="password" name="oldPassword">
	<br><br>

	New Password:
	<input type="password" name="newPassword">
	<br><br>

	<input type="submit" value="Change Password">

</form>

<br>

<a href="studentDashboard">Back to Dashboard</a>

</body>
</html>