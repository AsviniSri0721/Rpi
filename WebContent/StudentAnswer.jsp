<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="model.StudentAnswers"%>
<%@ page import="model.QuestionDetails"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title> Answers</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>

<script src="Components/answers.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
			
				<h1> Answers</h1>
				
				<form id="formAnswer" name="formAnswer">
			
					 <div className="form-group">
                        <label>Answer: </label>
                        	<div class="form-group">
                				<textarea
                    				class="form-control"
                    				rows="8"
                    				required
                    				id="stuAnswer"
		    						name="stuAnswer" 
		    						type="text"
                				></textarea>
                        	</div>
                    </div>
						
					 <input id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary">
					<input type="hidden"id="hidAnswerIDSave" name="hidAnswerIDSave" value="">
				</form>
				
				<div id="alertSuccess" class="alert alert-success"></div>
                <div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divAnswerGrid">
					<%
					StudentAnswers studentanswer = new StudentAnswers();
						out.print(studentanswer.readStudentAnswers());
					%>
				</div>
				
				<form name="myForm" method="POST" action="Student_ViewQuestions.jsp" >
				<button type="submit" class="btn btn-primary"  id="submit" onClick="">Confirm</button> 
				</form>
				
			</div>
		</div>
	</div>

</body>
</html>