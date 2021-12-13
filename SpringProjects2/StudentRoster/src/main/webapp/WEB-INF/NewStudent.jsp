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
						<title>New Student</title>
						<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
					</head>

					<body class="bg-dark text-light">
						<div class="container w-50 my-4 p-3 border border-danger text-center">
							<h1>New Student</h1>
							<div>
								<form:form action="/newstudent" method="post" modelAttribute="student">
									<p>
										<form:label path="name">Name: </form:label>
										<form:input path="name" /> <br>
										<form:errors path="name" class="text-danger" />
									</p>
									<form:button class="btn btn-outline-primary">Create</form:button>
								</form:form>
							</div>
							<a href="/dorms" class="mt-2 btn btn-outline-primary">Dashboard</a>

						</div>
					</body>

					</html>