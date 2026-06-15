<%@ page import="java.util.List" %>
<%@ page import="in.sp.model.Student" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<title>Search Student</title>
</head>
<body>

<h2>Search Student by ID or Name</h2>

<form action="searchStudent" method="post">

	Enter ID or Name:
	<input type="text" name="keyword">

	<input type="submit" value="Search">

</form>

<br>

<%
List<Student> students =
		(List<Student>) request.getAttribute("students");

if(students != null) {
%>

<table border="1" cellpadding="8">

<tr>
	<th>ID</th>
	<th>Name</th>
	<th>Email</th>
	<th>Status</th>
	<th>Membership Start</th>
	<th>Membership End</th>
</tr>

<%
	for(Student student : students) {
%>

<tr>
	<td><%= student.getStudentId() %></td>
	<td><%= student.getName() %></td>
	<td><%= student.getEmail() %></td>
	<td><%= student.getStatus() %></td>
	<td><%= student.getMembershipStart() %></td>
	<td><%= student.getMembershipEnd() %></td>
</tr>

<%
	}
%>

</table>

<%
}
%>

<br>

<a href="adminDashboard.jsp">Back</a>

</body>
</html>