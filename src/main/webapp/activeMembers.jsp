<%@ page import="java.util.List" %>
<%@ page import="in.sp.model.Student" %>

<!DOCTYPE html>
<html>
<head>
<title>Active Members</title>
</head>
<body>

<h2>Active Members Information</h2>

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
List<Student> activeMembers =
(List<Student>) request.getAttribute("activeMembers");

if(activeMembers != null && !activeMembers.isEmpty()) {

	for(Student s : activeMembers) {
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
<td colspan="7">No active members found</td>
</tr>

<%
}
%>

</table>

<br><br>

<a href="adminDashboard">Back to Admin Dashboard</a>

</body>
</html>