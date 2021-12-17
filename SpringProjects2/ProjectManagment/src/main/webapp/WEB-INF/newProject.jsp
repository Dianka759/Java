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
    <div class="container w-50 mt-4 p-4 text-center border border-danger">
        <h1>Create a Project!</h1>
        <form:form action="/newproject" method="post" modelAttribute="project">
            <form:hidden path="user" value="${user}" />
            <p>
                <form:errors path="title" />
            </p>
            <p>
                <form:errors path="description" />
            </p>
            <p>
                <form:errors path="dueDate" />
            </p>
            <p>
                <form:label path="title">Title</form:label> <br>
                <form:input type="text" path="title" />
            </p>
            <p>
                <form:label path="description">Description</form:label> <br>
                <form:textarea path="description" />
            </p>
            <p>
                <form:label path="dueDate">Due Date</form:label> <br>
                <form:input type="date" path="dueDate" />
            </p>
            <input type="submit" class="btn btn-outline-warning" value="submit">
        </form:form>
        <a href="/dashboard"> Cancel </a>
        </div>
</body>
</html>