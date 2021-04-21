<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Latest compiled and minified CSS -->
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
			<div class="row">
				<div class="col-md-8">
					<div class="card">
						<table>
							<tr>
								<th>ID:</th>
								<td>${research.research_id }</td>
							</tr>
							<tr>
								<th>Name:</th>
								<td>${research.name }</td>
							</tr>
							<tr>
								<th>Description</th>
								<td>${research.description }</td>
							</tr>
							<tr>
								<th>Category:</th>
								<td>${research.category }</td>
							</tr>
						</table>
					</div>
					<div class="card">
						<h2>Buyer</h2>
						<hr />
						<table>
							<tr>
								<th>Buyer ID:</th>
								<td>${research.buyer.buyer_id}</td>
							</tr>
							<tr>
								<th>Buyer Name:</th>
								<td>${research.buyer.firstName } ${research.buyer.lastName }</td>
							</tr>
							<tr>
								<th>NIC:</th>
								<td>${research.buyer.nic }</td>
							</tr>
							<tr>
								<th>Contact No:</th>
								<td>${research.buyer.contactNo }</td>
							</tr>
						</table>
					</div>
				</div>
				<div class="col-md-4">
					<div class="row">
						<div class="card">
							<h2>Supervisor</h2>
							<hr />
							<table>
								<tr>
									<th>Supervisor ID:</th>
									<td>${research.supervisor.supervisor_id }</td>
								</tr>
								<tr>
									<th>Supervisor Name:</th>
									<td>${research.supervisor.firstName } ${research.supervisor.lastName }</td>
								</tr>
								<tr>
									<th>NIC:</th>
									<td>${research.supervisor.nic }</td>
								</tr>
							</table>
						</div>
					</div>
					<div class="row">
						<div class="card">
							<h2>Researchers</h2>
							<table>
								<c:forEach items="${research.researchers}" var="researcher">
									<hr />
									<tr>
										<th>Researcher ID:</th>
										<td>${researcher.researcher_id}</td>
									</tr>
									<tr>
										<th>Researcher Name:</th>
										<td>${researcher.firstName } ${researcher.lastName }</td>
									</tr>
									<tr>
										<th>NIC:</th>
										<td>${researcher.nic }</td>
									</tr>
									<tr>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
					<div class="row">
						<div class="card">
							<h1>Funders</h1>
							<hr />
							<table>
								<c:forEach items="${research.funders}" var="funder">
									<tr>
										<th>Funder ID:</th>
										<td>${funder.funder_id}</td>
									</tr>
									<tr>
										<th>Funder Name:</th>
										<td>${funder.firstName } ${funder.lastName }</td>
									</tr>
									<tr>
										<th>NIC:</th>
										<td>${funder.nic }</td>
									</tr>
									<tr>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>