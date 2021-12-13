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
    <title>Show one song</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
</head>
<body class="bg-dark text-light">
	<div class="container border border-danger p-4 mt-4 text-center" style="width:400px">
		<h1 style="color:pink"><c:out value="${song.title}"/></h1>
		<h4>Artist: <span style="color:purple"><c:out value="${song.artist}"/></span></h4>
		<h4>Rating: <span style="color:green"><c:out value="${song.rating}"/> </span>star(s)</h4>
		<div class="d-flex justify-content-evenly mt-4 mb-2">
		<a href="/lookify/song/delete/${song.id}" class="btn btn-outline-danger">Delete</a>
	    <a href="/lookify/song/edit/${song.id}" class="btn btn-outline-warning">Edit</a>
		</div>
		<a href="/lookify/dashboard" class="btn btn-outline-primary">Dashboard</a>
	</div>
</body>
</html>