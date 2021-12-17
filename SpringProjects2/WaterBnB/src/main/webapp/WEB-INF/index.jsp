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
    <title>Tacos</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
</head>
<body class="bg-dark text-light">
<div class="container m-4">
<div class="text-end">
<a href="/signup" class="btn btn-outline-warning">Sign up</a>
</div>
		<form action="/location/search">
		<p class="text-danger"><c:out value="${error}"></c:out></p>
		<label>Search Location:</label>
			<input type="text" name="location" class="align-middle">
			<input type="submit" class="btn btn-outline-primary" value="New Search">
		</form>
		
     <table class="mt-4 table table-dark">
	 <thead>
	   <tr>
	     <th>Address</th>
	     <th>Pool Size</th>
	     <th>Cost per night</th>
	     <th>Details</th>
	   </tr>
	 </thead>
	 <tbody>
	 <c:forEach var="i" items="${pools}">
	<tr>
	<td><c:out value="${i.address}"/></td>
	<td><c:out value="${i.poolSize}"/></td>
	<td><c:out value="${i.price}"/></td>						
	<td><a href="/pool/${i.id}">${i.description} - See more</a></td>
	</tr>
	</c:forEach>
	</tbody>
			</table> 
</div>
   
</body>
</html>