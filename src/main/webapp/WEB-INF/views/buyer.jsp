<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
		<div class="list mt-3">
			<div class="card">
				<table>
					<tr>
						<th>Buyer ID:</th>
						<td>${buyer.buyer_id}</td>
					</tr>
					<tr>
						<th>Buyer Name:</th>
						<td>${buyer.firstName } ${buyer.lastName }</td>
					</tr>
					<tr>
						<th>NIC:</th>
						<td>${buyer.nic }</td>
					</tr>
					<tr>
						<th>Contact No:</th>
						<td>${buyer.contactNo }</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
</html>