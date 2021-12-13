<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update song</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
</head>
<body class="bg-dark text-light">
<div class="container w-75 p-4 border border-danger">
	<div class="container border border-warning p-3 text-center w-50">
		<h1 class="text-danger">Update Song</h1>
		<form:form action="/lookify/updateSong/${song.id}" method="post" modelAttribute="song">
			<p>
				<form:label path="title">Title</form:label> <br>
				<form:errors path="title"/>
				<form:input path="title"/>
			</p>
			<p>
				<form:label path="artist">Artist</form:label> <br>
				<form:errors path="artist"/>
				<form:input path="artist"/>
			</p>
			<p>
				<form:label path="rating">Rating (1-10)</form:label> <br>
				<form:errors path="rating"/>
				<form:input type="number" path="rating"/>
			</p>
			<input type="submit" class="btn btn-outline-success" value="Update"/>
		</form:form>
		   <a href="/lookify/dashboard" class="mt-2 btn btn-outline-primary">Dashboard</a>
	</div>
	</div>
</body>
</html>