<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="model.AssignmentDetails"%>
<%@ page import="model.QuestionDetails"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Questions & Answers</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>

<script src="Components/questions.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <style>
    /* Remove the navbar's default margin-bottom and rounded borders */ 
    .navbar {
      margin-bottom: 0;
      border-radius: 0;
    }
    
    /* Add a gray background color and some padding to the footer */
    footer {
      background-color: #f2f2f2;
      padding: 25px;
    }
    body{
    background-color:lightgrey;
    background-image:url('image/im1.jpg');
    background-attachment:fixed;
    background-size:cover;
    }
    
    * {
  box-sizing: border-box;
}
/* Create two equal columns that floats next to each other */
.column {
  float: left;
  width: 50%;
  padding: 10px;
  height: 300px; /* Should be removed. Only for demonstration */
}
/* Clear floats after the columns */
.row:after {
  content: "";
  display: table;
  clear: both;
}
bu.{
border:none;
color:white;
text-align:center;
display:inline-block;
font-size:40px;
cursor:pointer;
 width: 100px;
 height: 300px;
}
  </style>
</head>
<body>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="#">WiLeY</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active"><a href="View_Assignments.jsp"></a></li>
        <li><a href="Create_Assignment.jsp"></a></li>
        <li><a href="fullDetailsView.jsp"></a></li>
        <li><a href="ReportTemp.jsp"></a></li>
        <li><a href="SendEmail.jsp"></a></li>
        
      </ul>
      
    </div>
  </div>
</nav>
	<div class="container">
		<div class="row">
			<div class="col-6">
			
				<h1>Create Questions & Answers</h1>
				
				<form id="formQuestion" name="formQuestion">
				
				
				
					Question No: <input id="questionNo" name="questionNo" type="text"
						class="form-control form-control-sm"> <br>
						
					 <div className="form-group">
                        <label>Question: </label>
                        	<div class="form-group">
                				<textarea
                    				class="form-control"
                    				rows="8"
                    				required
                    				id="question"
		    						name="question" 
		    						type="text"
                				></textarea>
                        	 </div>
                     </div>
			
					 <div className="form-group">
                        <label>Answer: </label>
                        	<div class="form-group">
                				<textarea
                    				class="form-control"
                    				rows="8"
                    				required
                    				id="answer"
		    						name="answer" 
		    						type="text"
                				></textarea>
                        	</div>
                    </div>
						
					 <input id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary">
					<input type="hidden"id="hidQuestionIDSave" name="hidQuestionIDSave" value="">
				</form>
				
				<div id="alertSuccess" class="alert alert-success"></div>
                <div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divQuestionGrid">
					<%
					QuestionDetails questiondetails = new QuestionDetails();
						out.print(questiondetails.readQuestionDetails());
					%>
				</div>
				
				<form name="myForm" method="POST" action="View_Assignments.jsp.jsp" >
				<button type="submit" class="btn btn-primary"  id="submit" onClick="">All Done</button> 
				</form>
				
			</div>
		</div>
	</div>

</body>
</html>