<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>WILEY</title>

<script src="Components/jquery-3.2.1.min.js"></script>

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
        <li class="active"><a href="Create_Assignment.jsp">TEACHERS VIEW</a></li>
        <li><a href="Student_ViewAssignments.jsp">STUDENTS VIEW</a></li>
        <li><a href=""></a></li>
        <li><a href=""></a></li>
        <li><a href=""></a></li>
        
      </ul>
      
    </div>
  </div>
</nav>

	<div class="container">
		<div class="row">
			<div class="col-6">
			<pre>
			                                          <u></u><li><h1>WILEY</h1><li></u>
				
			</pre>	
				
			</div>
		</div>
	</div>

</body>
</html>