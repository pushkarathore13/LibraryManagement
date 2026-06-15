<%@ page import="java.util.List" %>
<%@ page import="in.sp.model.Student" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
List<Student> students =
		(List<Student>) request.getAttribute("students");
%>

<!DOCTYPE html>
<html>
<head>
<title>Pending Students</title>
</head>
<body>

<h2>Pending Student Requests</h2>

<table border="1" cellpadding="8">

<tr>
	<th>ID</th>
	<th>Name</th>
	<th>Email</th>
	<th>Mobile</th>
	<th>Gender</th>
	<th>Occupation</th>
	<th>Aadhaar</th>
	<th>Action</th>
</tr>

<%
if(students != null) {
	for(Student student : students) {
%>

<tr>
	<td><%= student.getStudentId() %></td>
	<td><%= student.getName() %></td>
	<td><%= student.getEmail() %></td>
	<td><%= student.getMobile() %></td>
	<td><%= student.getGender() %></td>
	<td><%= student.getOccupation() %></td>
	<td><%= student.getAadhaarId() %></td>

	<td>
		<a href="approveStudent?id=<%= student.getStudentId() %>">Approve</a>
		|
		<a href="rejectStudent?id=<%= student.getStudentId() %>">Reject</a>
	</td>
</tr>

<%
	}
}
%>

</table>

<br>

<a href="adminDashboard.jsp">Back</a>

</body>
</html>