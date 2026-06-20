<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);

if(session.getAttribute("admin_email") == null){
	response.sendRedirect("login.jsp");
	return;
}
%>
<%@ page import="java.util.List" %>
<%@ page import="in.sp.model.Student" %>

<%@ page language="java"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

<%
List<Student> students =
(List<Student>) request.getAttribute("students");
%>

<!DOCTYPE html>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Pending Students</title>

<style>

body{
	font-family:Arial,sans-serif;
	background:#f4f6f9;
	padding:20px;
}

h2{
	color:#1e3c72;
	margin-bottom:20px;
}

table{
	width:100%;
	border-collapse:collapse;
	background:white;
	box-shadow:0 2px 10px rgba(0,0,0,0.1);
}

th{
	background:#1e3c72;
	color:white;
	padding:10px;
}

td{
	padding:10px;
	text-align:center;
	border:1px solid #ddd;
}

.approve-btn{
	background:#28a745;
	color:white;
	padding:6px 12px;
	border:none;
	border-radius:5px;
	cursor:pointer;
}

.reject-btn{
	background:#dc3545;
	color:white;
	padding:6px 12px;
	border:none;
	border-radius:5px;
	cursor:pointer;
}

.reason-box{
	width:150px;
	padding:5px;
}

.back-link{
	display:inline-block;
	margin-top:20px;
	text-decoration:none;
	font-weight:bold;
}

</style>

</head>

<body>

<h2>Pending Student Requests</h2>

<table>

<tr>
	<th>ID</th>
	<th>Name</th>
	<th>Email</th>
	<th>Mobile</th>
	<th>Gender</th>
	<th>Occupation</th>
	<th>Aadhaar</th>
	<th>Approve</th>
	<th>Reject</th>
</tr>

<%
if(students != null){
for(Student student : students){
%>

<tr>

```
<td><%= student.getStudentId() %></td>
<td><%= student.getName() %></td>
<td><%= student.getEmail() %></td>
<td><%= student.getMobile() %></td>
<td><%= student.getGender() %></td>
<td><%= student.getOccupation() %></td>
<td><%= student.getAadhaarId() %></td>

<td>
	<a href="approveStudent?id=<%= student.getStudentId() %>">
		<button class="approve-btn">
			Approve
		</button>
	</a>
</td>

<td>

	<form action="rejectStudent" method="post">

		<input
		type="hidden"
		name="id"
		value="<%= student.getStudentId() %>">

		<input
		type="text"
		name="reason"
		class="reason-box"
		placeholder="Reason"
		required>

		<br><br>

		<input
		type="submit"
		value="Reject"
		class="reject-btn">

	</form>

</td>
```

</tr>

<%
}
}
%>

</table>

<br>

<a href="adminDashboard.jsp"
class="back-link">
Back to Dashboard </a>

</body>
</html>
