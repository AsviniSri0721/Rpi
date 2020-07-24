<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="model.Student_ViewAssignment"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View All Assignment</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/assignments.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
			
				<h1>View All Assignments</h1>
				
				
				
				
				<div id="divPaymentGrid">
					<%
					Student_ViewAssignment assignmentdetails = new Student_ViewAssignment();
						out.print(assignmentdetails.readAssignmentDetails());
					%>
				</div>
			</div>
		</div>
	</div>

</body>
</html>