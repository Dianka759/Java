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
<div class="p-2 border border-danger text-light">
<div class="text-end">
<a href="/" class="btn btn-outline-primary"> Home</a>
<c:if test="${user.id != null}">
<a href="/logout" class="btn btn-outline-danger">Logout</a>
</c:if>
</div>
<div class="container d-flex justify-content-between">
<div class="container">
   <h2>${pool.address}</h2>
   <p>${pool.description}</p>

  </div>
   <div class="container">
   <p>Email: ${pool.user.email}</p>
   <p>Name: ${pool.user.firstName} ${pool.user.lastName}</p>
   <p>Pool size: ${pool.poolSize}</p>
   <p>Cost: $${pool.price}</p>
   </div>
   </div>
   <div class="p-3 border border-warning">
   <div class="d-flex justify-content-between">
   </div>
   <h2> Reviews: (<c:out value="${pool.getAverageRating()}"></c:out>/5)</h2> 
   <a href="/leaveReview/${pool.id}">Leave a review</a>
   <c:forEach var="i" items="${reviews}">
   <div class="p-3 border border-success">
   <h4> ${ i.user.firstName }</h4>
   <p> Rating: ${ i.rating }/5</p>
   <p> ${ i.description }</p>
   </div>
   </c:forEach> 
   </div>
</div>
</body>
</html>