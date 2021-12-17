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
<body class="bg-dark">
<div class="container mt-4 border border-danger">
   <a href="/">Home</a>
   <a href="/logout"> Logout</a>
   <h2> Find your pool </h2>
   <input type="search" value="Search">
   <table class="table table-dark">
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