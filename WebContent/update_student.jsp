<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

<link type="text/css" rel="stylesheet" href="css/style.css" />
<title>Update Student</title>
</head>
<body>
	<div class="container col-md-5">
		<h3>Update Student</h3>
		<form action="students" method="get">
			<input type="hidden" name="command" value="UPDATE" />
			<input type="hidden" name="studentId" value="${student.id}" />
			<div class="form-group">
			    <label for="fname">First Name</label>
			    <input type="text"
			    	   value="${student.firstName}" 
			    	   name="firstName" 
			    	   class="form-control" 
			    	   id="fname"
			    	   placeholder="Enter First Name" />
			</div>
			<div class="form-group">
			    <label for="lname">Last Name</label>
			    <input type="text"
			    	   value="${student.lastName}" 
			    	   name="lastName" class="form-control" 
			    	   id="lname"
			    	   placeholder="Enter Last Name" />
			</div>
			<div class="form-group">
			    <label for="email">Email</label>
			    <input type="email"
			    	   value="${student.email}" 
			    	   name="email"
			    	   class="form-control" 
			    	   id="fname"
					   placeholder="Enter Email" />
			</div>
			<input type="submit" value="Update" class="btn btn-primary" />
		</form>
		<hr/>
		<p>
			<a href="students">Back</a>
		</p>
	</div>
</body>
</html>