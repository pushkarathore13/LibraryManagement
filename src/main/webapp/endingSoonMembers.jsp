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

<!DOCTYPE html>
<html>
<head>
<title>Membership Ending Soon</title>
</head>
<body>

<h2>Membership Ending Soon</h2>

<table border="1" cellpadding="8">

<tr>
<th>ID</th>
<th>Name</th>
<th>Email</th>
<th>Mobile</th>
<th>Membership Start</th>
<th>Membership End</th>
<th>Seat ID</th>
</tr>

<%
List<Student> students =
(List<Student>) request.getAttribute("students");

if(students != null && !students.isEmpty()) {
	for(Student s : students) {
%>

<tr>
<td><%= s.getStudentId() %></td>
<td><%= s.getName() %></td>
<td><%= s.getEmail() %></td>
<td><%= s.getMobile() %></td>
<td><%= s.getMembershipStart() %></td>
<td><%= s.getMembershipEnd() %></td>
<td><%= s.getSeatId() %></td>
</tr>

<%
	}
} else {
%>

<tr>
<td colspan="7">No memberships ending soon</td>
</tr>

<%
}
%>

</table>

<br><br>

<a href="adminDashboard">Back to Admin Dashboard</a>

</body>
</html>