<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
	integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap-theme.min.css"
	integrity="sha384-6pzBo3FDv/PJ8r2KRkGHifhEocL+1X2rVCTTkUfGk7/0pbek5mMa1upzvWbrUbOZ"
	crossorigin="anonymous">
<link rel="stylesheet" href="/index.css">
<title>GadgetBadget</title>
</head>
<body>
	<jsp:include page="home.jsp"></jsp:include>
	<div class="container">
		<div class="list">
			<div class="card">
				<h2>Update Researcher</h2>
				<hr />
				<form:form action="updateResearcher" method="post"
					modelAttribute="researcher">
					<form:hidden path="researcher_id" />
					<br />
	
					<form:label path="firstName">First Name:</form:label>
					<form:input path="firstName" />
					<br />
	
					<form:label path="lastName">Last Name:</form:label>
					<form:input path="lastName" />
					<br />
	
					<form:label path="nic">NIC:</form:label>
					<form:input path="nic" />
					<br />
	
					<form:button>Save</form:button>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>