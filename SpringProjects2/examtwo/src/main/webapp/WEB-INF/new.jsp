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
<title></title>
<!-- Bootstrap -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

</head>
<body>
<div class="container"> 
	<form:form action="/home/create" method="post" modelAttribute="babyname">
		<form:hidden path="user" value="${userId}" />
		<form:hidden path="votes" value=0 />
		


		<div class="form-group">
			<label>Name: </label>
			<form:input path="name" class="form-control" />
			<form:errors path="name" class="text-danger" />
		</div>

		<div class="form-group">
			<label>Typical Gender: </label>
			<form:input path="gender" class="form-control" />
			<form:errors path="gender" class="text-danger" />
		</div>

		<div class="form-group">
			<label>Origin: </label>
			<form:input path="origin" class="form-control" />
			<form:errors path="origin" class="text-danger" />
		</div>

		<div class="form-group">
			<label>Meaning: </label>
			<form:input path="meaning" class="form-control" />
			<form:errors path="meaning" class="text-danger" />
		</div>

		<a href="/home">Cancel</a>
		<input class="btn btn-info" type="submit" value="Submit">
	</form:form>
</div>

</body>
</html>