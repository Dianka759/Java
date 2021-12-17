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

<form:form action="/updateListing/${pool.id}" method="put" modelAttribute="editListing">
	<input type="hidden" name="_method" value="put"/>
 		<form:input type="hidden" path="user" value="${pool.user.id}"/>

	
<div class="container mt-5 d-flex justify-content-between">
<div class="container">
	<form:errors path="address"/>
	<form:input type="text" path="address" value="${pool.address}"/> 
   <div class="mt-3 border border-danger p-3">
    <label>Description</label>
	<form:errors path="description"/>
	<form:input type="text" path="description" value="${pool.description}"/>
   </div>
   </div>
   <div class="container">
   <p>Email: ${pool.user.email}</p>
   <p>Name: ${pool.user.firstName} ${pool.user.lastName}</p> <br>
   
 <label>Pool size</label>
	<form:errors path="poolSize"/>
	<form:select path="poolSize" value="${pool.poolSize}">
	<form:option value="${pool.poolSize }"></form:option>
	<form:option value="small"></form:option>
	<form:option value="medium"></form:option>
	<form:option value="large"></form:option>
	</form:select> <br> <br>
	
    <label>Cost per night</label>
	<form:errors path="price"/>
	<form:input type="number" path="price" value="${pool.price}"/> <br> <br>
   
   <input type="submit" value="Save Changes"/>
   </div>
   </div>
</form:form>
	
	<div class="container mt-4 border border-danger">
	<h2> Reviews: ${review.rating}/5</h2>
   <a href="/leaveReview">Leave a review</a>
   <c:forEach var="i" items="${location.reviews}">
   <h2> ${ i.name }</h2>
   <h2> Rating: ${ i.rating }/5</h2>
   <h2> ${ i.name }</h2>
   </c:forEach>
   </div>
</body>
</html>