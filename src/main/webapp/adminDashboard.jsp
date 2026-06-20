<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);

if(session.getAttribute("admin_email") == null){
	response.sendRedirect("login.jsp");
	return;
}
%>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);

if(session.getAttribute("admin_email") == null){
	response.sendRedirect("login.jsp");
	return;
}
%>

<!DOCTYPE html>
<html>
<head>
<title>Admin Dashboard</title>
</head>
<body>

<h2>Admin Dashboard</h2>

<h3>
Total Active Memberships :
<%= request.getAttribute("activeMemberships") %>
</h3>

<h3>
Total Selected Seats :
<%= request.getAttribute("selectedSeats") %>
</h3>

<a href="activeMembers">View Active Members Information</a>
<br><br>

<a href="allStudents">View All Student Details</a>
<br><br>

<a href="seatChangeRequests">View Seat Change Requests</a>
<br><br>

<a href="membershipHistory">View Membership History</a>
<br><br>

<a href="endMembershipPage">End Student Membership</a>
<br><br>

<a href="endingSoonMembers">Membership Ending Soon</a>
<br><br>

<a href="expiredMembers">Expired Memberships</a>
<br><br>

<a href="pendingStudents">Pending Student Requests</a>
<br><br>

<a href="searchStudent.jsp">Search Student</a>
<br><br>

<a href="index.jsp">Back to Homepage</a>
<br><br>

<a href="logout">Logout</a>

</body>
</html>