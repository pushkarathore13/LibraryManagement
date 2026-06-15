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

<a href="activeMembers">View Active Members Information</a>
<br><br>

<a href="seatChangeRequests">View Seat Change Requests</a>
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