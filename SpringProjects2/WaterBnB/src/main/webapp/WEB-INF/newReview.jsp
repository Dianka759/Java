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
<body>
   <a href="/"> Home </a>
   <a href="/logout"> Logout</a>
   
   <h2> Review of ${pool.address} </h2>
   <form:form action="/createReview/${pool.id}" method="post" modelAttribute="newReview">
<%--    	<form:input type="hidden" path="user" value="${user.id}"/>
   	<form:input type="hidden" path="pool" value="${pool.id}"/> --%>
   
   <label>Description:</label>
   <form:textarea path="description"/>
   <label> Rating:</label>
   <select name="rating">
   <option value="1">1</option>
   <option value="2">2</option>
   <option value="3">3</option>
   <option value="4">4</option>
   <option value="5">5</option>
   </select>
   <input type="submit" value="Submit Review"/>
   </form:form>
</body>
</html>