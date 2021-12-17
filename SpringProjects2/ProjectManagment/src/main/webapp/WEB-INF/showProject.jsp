<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tacos</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
</head>
   <body class="bg-dark text-light">
    <div class="container w-50 mt-4 p-4 text-center border border-danger">
        <h1>Show Project!</h1>
           
           <h4> Title: ${project.title}</h4>
           <h4> Description: ${project.description}</h4>

           <h4> DueDate: <fmt:formatDate value="${project.dueDate}" pattern="dd/mm/yyyy" /></h4>

           <h4> Team Lead: ${project.user.firstName}</h4>
        <a href="/deleteJoin/${project.id}">Delete</a>
        <a href="/dashboard"> Dashboard </a> <br>
        <a href="tasks"> See Tasks </a>
        
        </div>
</body>
</html>