<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	  href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" 
	  integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" 
	  crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" 
	  href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" 
	  integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" 
	  crossorigin="anonymous">

<!-- jQuery CDN -->
<script
  src="https://code.jquery.com/jquery-3.2.1.min.js"
  integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
  crossorigin="anonymous"></script>

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" 
		integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" 
		crossorigin="anonymous"></script> 

<link type="text/css" rel="stylesheet" href="css/style.css" />
<title>Student Tracker</title>
</head>
<body>
	<div class="container">
		<div class="jumbotron">
		  <p>Cal State East Bay</p>
		</div>
		<a href="add_student_form.jsp" class="btn btn-success">Add Student</a>
		<br/><br/>
		<div>
			<table class="table table-striped">
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email </th>
					<th>Action</th>
				</tr>
				<c:forEach var="student" items="${students}">
					<%-- <c:url var="updateLink" value="students">
						<c:param name="command" value="LOAD" />
						<c:param name="studentId" value="${student.id}" />
					</c:url> --%>
					<tr>
						<td>${student.firstName}</td>
						<td>${student.lastName}</td>
						<td>${student.email}</td>
						<td><a href="students?command=LOAD&studentId=${student.id}">
								Update
							</a> |
							<a href="students?command=DELETE&studentId=${student.id}"
							   onClick="if(!(confirm('Are you sure you want to proceed ?'))) return false"
							>
								Delete
							</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>