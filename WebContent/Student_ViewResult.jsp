<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="model.Student_Results"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View All Answers</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/results.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
			
				<h1>View All Answers</h1>
				
				
				
				
				<div id="divResultGrid">
					<%
					Student_Results resultdetails = new Student_Results();
						out.print(resultdetails.readResultDetails());
					%>
				</div>
			</div>
		</div>
	</div>

</body>
</html>