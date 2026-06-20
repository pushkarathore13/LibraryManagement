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
<title>End Membership</title>
</head>
<body>

<h2>End Student Membership</h2>

<table border="1" cellpadding="8">

<tr>
<th>ID</th>
<th>Name</th>
<th>Email</th>
<th>Mobile</th>
<th>Gender</th>
<th>Occupation</th>
<th>Aadhaar</th>
<th>Seat ID</th>
<th>Shift</th>
<th>Membership Start</th>
<th>Membership End</th>
<th>Reason</th>
<th>Action</th>
</tr>

<%
List<Student> activeMembers =
(List<Student>) request.getAttribute("activeMembers");

if(activeMembers != null && !activeMembers.isEmpty()) {
	for(Student s : activeMembers) {
%>

<tr>
<form action="endMembership" method="post">

<td><%= s.getStudentId() %></td>
<td><%= s.getName() %></td>
<td><%= s.getEmail() %></td>
<td><%= s.getMobile() %></td>
<td><%= s.getGender() %></td>
<td><%= s.getOccupation() %></td>
<td><%= s.getAadhaarId() %></td>
<td><%= s.getSeatId() %></td>
<td><%= s.getShiftName() != null ? s.getShiftName() : "-" %></td>
<td><%= s.getMembershipStart() %></td>
<td><%= s.getMembershipEnd() %></td>

<td>
<input type="text" name="reason" placeholder="Enter reason" required>
<input type="hidden" name="studentId" value="<%= s.getStudentId() %>">
</td>

<td>
<input type="submit" value="End Membership">
</td>

</form>
</tr>

<%
	}
} else {
%>

<tr>
<td colspan="13">No active members found</td>
</tr>

<%
}
%>

</table>

<br><br>

<a href="adminDashboard">Back to Admin Dashboard</a>

</body>
</html>