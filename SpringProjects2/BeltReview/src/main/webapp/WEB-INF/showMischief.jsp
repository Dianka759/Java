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
       <div class="mt-4 p-4 border border-danger position-absolute top-0 start-50 translate-middle-x">
       <h2 class="text-center">Name: <span class="text-danger"><c:out value="${mischief.name}"></c:out></span></h2>
       <p>Location: <span class="text-danger"><c:out value="${mischief.location}"></c:out></span></p>
       <p>Duration: <span class="text-danger"><c:out value="${mischief.duration}"></c:out></span></p>
       <p>Description: <span class="text-danger"><c:out value="${mischief.description}"></c:out></span></p>
       <p>Created by: <span class="text-danger"><c:out value="${mischief.user.firstName}"></c:out> <c:out value="${mischief.user.lastName}"></c:out></span></p>
       <div class="mt-4 text-center">
       <c:if test="${mischief.user.id == user.id}">
       <a href="/edit/${mischief.id}" class="btn btn-outline-warning"> Edit</a>
       <a href="/delete/${mischief.id}" class="btn btn-outline-danger"> Delete</a> <br>
       </c:if>
       <a href="/dashboard" class="mt-3 btn btn-outline-primary"> Dashboard</a>
	   </div>
</div>
</body>
</html>