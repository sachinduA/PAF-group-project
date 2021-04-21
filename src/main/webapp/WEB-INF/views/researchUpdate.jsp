<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			<div class="row">
				<div class="col-md-8">
					<div class="card">
						<h2>Update Research</h2>
						<hr />
						<form:form action="updateResearch" method="post"
							modelAttribute="research">
							<form:hidden path="research_id" />
							<br />
	
							<form:label path="name">Name:</form:label>
							<form:input path="name" />
							<br />
	
							<form:label path="description">Description:</form:label>
							<form:textarea path="description" cols="25" rows="5" />
							<br />
	
							<form:label path="category">Category:</form:label>
							<form:input path="category" />
							<br />
	
							<form:button>Save</form:button>
						</form:form>
					</div>
					<div class="card">
						<h2>Assign Buyer</h2>
						<hr />
						<form action="addBuyerToResearch" method="post">
							<input name="id" value="${research.research_id}" hidden="true" />
							<select name="buyerId">
								<c:forEach items="${buyers}" var="buyer">
									<option value="${buyer.buyer_id}">${buyer.firstName}
										${buyer.lastName}</option>
								</c:forEach>
							</select> <input type="submit" value="Assign" />
						</form>
	
						<h2>Remove Buyer</h2>
						<hr />
						<form action="deleteBuyerFromResearch" method="post">
							<input name="id" value="${research.buyer.buyer_id }" type="number" />
							<input type="submit" value="Remove" />
						</form>
					</div>
				</div>
				<div class="col-md-4">
					<div class="row">
						<div class="card">
							<h2>Assign Supervisor</h2>
							<hr />
							<form action="addSupervisorToResearch" method="post">
								<input name="id" value="${research.research_id}" hidden="true" />
								<select name="supervisorId">
									<c:forEach items="${supervisors}" var="supervisor">
										<option value="${supervisor.supervisor_id}">${supervisor.firstName}
											${supervisor.lastName}</option>
									</c:forEach>
								</select> <input type="submit" value="Assign" />
							</form>
							<h2>Remove Supervisor</h2>
							<hr />
							<form action="removeSupervisorFromResearch" method="post">
								<input name="id" value="${research.supervisor.supervisor_id }"
									type="number" /> <input type="submit" value="Remove" />
							</form>
						</div>
					</div>
					<div class="row">
						<div class="card">
							<h2>Assign a Researcher</h2>
							<hr />
							<form action="addResearcherToResearch" method="post">
								<input name="id" value="${research.research_id}" hidden="true" />
								<select name="researcherId">
									<c:forEach items="${researchers}" var="researcher">
										<option value="${researcher.researcher_id}">${researcher.firstName}
											${researcher.lastName}</option>
									</c:forEach>
								</select> <input type="submit" value="Assign" />
							</form>
							<h2>Remove a Researcher</h2>
							<hr />
							<form action="deleteResearcherFromResearch" method="post">
								<input name="id" value="${research.research_id}" hidden="true" />
								<select name="researcherId">
									<c:forEach items="${research.researchers}" var="researcher">
										<option value="${researcher.researcher_id}">${researcher.firstName}
											${researcher.lastName}</option>
									</c:forEach>
								</select> <input type="submit" value="Remove" />
							</form>
						</div>
					</div>
					<div class="row">
						<div class="card">
							<h2>Assign a Funder</h2>
							<hr />
							<form action="addFunderToResearch" method="post">
								<input name="id" value="${research.research_id}" hidden="true" />
								<select name="funderId">
									<c:forEach items="${funders}" var="funder">
										<option value="${funder.funder_id}">${funder.firstName}
											${funder.lastName}</option>
									</c:forEach>
								</select> <input type="submit" value="Assign" />
							</form>
							<h2>Remove a Funder</h2>
							<hr />
							<form action="deleteFunderFromResearch" method="post">
								<input name="id" value="${research.research_id}" hidden="true" />
								<select name="funderId">
									<c:forEach items="${research.funders}" var="funder">
										<option value="${funder.funder_id}">${funder.firstName}
											${funder.lastName}</option>
									</c:forEach>
								</select> <input type="submit" value="Remove" />
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>