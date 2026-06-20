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
<%@ page import="in.sp.model.MembershipHistory" %>

<!DOCTYPE html>
<html>
<head>
<title>Membership History</title>
</head>
<body>

<h2>Membership History</h2>

<table border="1" cellpadding="8">

<tr>
<th>History ID</th>
<th>Student ID</th>
<th>Name</th>
<th>Email</th>
<th>Seat ID</th>
<th>Shift</th>
<th>Membership Start</th>
<th>Membership End</th>
<th>Action</th>
<th>Reason</th>
<th>Date/Time</th>
</tr>

<%
List<MembershipHistory> historyList =
(List<MembershipHistory>) request.getAttribute("historyList");

if(historyList != null && !historyList.isEmpty()) {
	for(MembershipHistory h : historyList) {
%>

<tr>
<td><%= h.getHistoryId() %></td>
<td><%= h.getStudentId() %></td>
<td><%= h.getStudentName() %></td>
<td><%= h.getStudentEmail() %></td>
<td><%= h.getSeatId() %></td>
<td><%= h.getShiftName() %></td>
<td><%= h.getMembershipStart() %></td>
<td><%= h.getMembershipEnd() %></td>
<td><%= h.getActionType() %></td>
<td><%= h.getReason() %></td>
<td><%= h.getCreatedAt() %></td>
</tr>

<%
	}
} else {
%>

<tr>
<td colspan="11">No membership history found</td>
</tr>

<%
}
%>

</table>

<br><br>

<a href="adminDashboard">Back to Admin Dashboard</a>

</body>
</html>