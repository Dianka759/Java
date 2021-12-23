<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- c:out ; c:forEach ; c:if -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (like dates) -->
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard; first page when you log in.</title>
<!-- Bootstrap -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h1 display="inline" class="text-info">
			Welcome,
			<c:out value="${user.userName}"></c:out>
			here are some name suggestions..
		</h1>
		<a href="/logout">Logout</a>
		<div class="d-flex justify-content-between">
			<h2>Baby Names</h2>
			<h2>Votes</h2>
		</div>
		<div class="container ">
			<c:forEach var="i" items="${babynames}">
		<div class="d-flex justify-content-between">
			<form:form action="/vote/${i.id}" method="post">
				<input type="submit" value="Upvote!"/>
			</form:form>
				<a href="/view/${i.id}">${i.name}</a>
				<p>${i.gender}</p>
				<p>Origin: ${i.origin}</p>
				<p> ${i.votes}</p>
		</div>
			</c:forEach>

		</div>
		<button> <a href="/home/new">New Name</a></button>
	</div>
</body>
</html>