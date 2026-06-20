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
<%@ page import="in.sp.model.SeatChangeRequest" %>

<!DOCTYPE html>
<html>
<head>
<title>Seat Change Requests</title>
</head>
<body>

<h2>Seat Change Requests</h2>

<table border="1" cellpadding="8">

<tr>
<th>Request ID</th>
<th>Student ID</th>
<th>Name</th>
<th>Email</th>
<th>Current Seat</th>
<th>Requested Seat</th>
<th>Status</th>
<th>Approve</th>
<th>Reject</th>
</tr>

<%
List<SeatChangeRequest> requests =
(List<SeatChangeRequest>) request.getAttribute("seatChangeRequests");

if(requests != null && !requests.isEmpty()) {

	for(SeatChangeRequest r : requests) {
%>

<tr>
<td><%= r.getRequestId() %></td>
<td><%= r.getStudentId() %></td>
<td><%= r.getStudentName() %></td>
<td><%= r.getStudentEmail() %></td>
<td><%= r.getCurrentSeatId() %></td>
<td><%= r.getRequestedSeatId() %></td>
<td><%= r.getStatus() %></td>

<td>
<a href="approveSeatChange?requestId=<%= r.getRequestId() %>">
Approve
</a>
</td>

<td>
<a href="rejectSeatChange?requestId=<%= r.getRequestId() %>">
Reject
</a>
</td>
</tr>

<%
	}
} else {
%>

<tr>
<td colspan="9">No pending seat change requests</td>
</tr>

<%
}
%>

</table>

<br><br>

<a href="adminDashboard">Back to Admin Dashboard</a>

</body>
</html>