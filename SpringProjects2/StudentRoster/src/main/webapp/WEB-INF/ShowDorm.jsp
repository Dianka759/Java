<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<!-- c:out ; c:forEach etc. -->
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<!-- Formatting (dates) -->
		<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
			<!-- form:form -->
			<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
				<!-- for rendering errors on PUT routes -->
				<%@ page isErrorPage="true" %>
					<!DOCTYPE html>
					<html>

					<head>
						<meta charset="UTF-8">
						<title>Show Dorm</title>
						<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
					</head>

					<body class="bg-dark text-light">
						<div class="container w-50 my-4 p-3 border border-danger text-center">
							<h1><span class="text-warning">
									<c:out value="${dorm.name}" />
								</span> Dormitory</h1>
							<div>
								<form action="/dorms/${dorm.id}/add" method="post">
									<label class="align-middle">Students: </label>
									<select name="student" class="align-middle">
										<c:forEach var="student" items="${students}">
												<option value="${student.id}">
													<c:out value="${student.name}" />
												</option>	
										</c:forEach>
									</select>
									<button type="submit" class="btn btn-outline-warning">Add Student</button>
								</form>
							</div>
							<div>
								<table class="table table-dark mt-3">
									<thead>
										<tr>
											<th>Name</th>
											<th>Action</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="student" items="${dorm.students}">
											<tr>
												<td>
													<c:out value="${student.name}"></c:out>
												<td><a href="/dorms/${dorm.id}/remove?student=${student.id}">Remove</a>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<a href="/dorms/new" class="btn btn-outline-success">New Dorm</a>
							<a href="/students/new" class="btn btn-outline-success">New Student</a> <br>
							<a href="/dorms" class="mt-2 btn btn-outline-primary">Dashboard</a>
						</div>
					</body>

					</html>