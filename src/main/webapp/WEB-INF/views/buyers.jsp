<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
				<div class="row">
					<div class="col-md-1">
						<b>ID</b>
					</div>
					<div class="col-md-4">
						<b>Name</b>
					</div>
					<div class="col-md-2">
						<b>NIC</b>
					</div>
					<div class="col-md-2">
						<b>Contact No</b>
					</div>
					<div class="col-md-1"></div>
					<div class="col-md-1"></div>
					<div class="col-md-1"></div>
				</div>
			</div>
		</div>
		<c:forEach items="${buyers}" var="buyer">
			<div class="list">
				<div class="card">
					<div class="row">
						<div class="col-md-1">${buyer.buyer_id }</div>
						<div class="col-md-4">${buyer.firstName } ${buyer.lastName }</div>
						<div class="col-md-2">${buyer.nic }</div>
						<div class="col-md-2">${buyer.contactNo }</div>
						<div class="col-md-1">
							<a href="/buyers/${buyer.buyer_id }">View</a>
						</div>
						<div class="col-md-1">
							<a href="/updateBuyerForm/${buyer.buyer_id }">Edit</a>
						</div>
						<div class="col-md-1">
							<a href="/deleteBuyer/${buyer.buyer_id }">Delete</a>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
		<div class="list"><a href="/newBuyerForm"><button>New Buyer</button></a></div>
	</div>
</body>
</html>