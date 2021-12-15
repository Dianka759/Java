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
            <div class="container w-50 border border-danger mt-4 p-3 text-light text-center">
              <!-- Beginning of Container -->
              <h1>Edit Mischief</h1>
              <form:form action="/updateMischief/${mischief.id}" method="post" modelAttribute="mischief">
              <form:hidden path="user" value="${user}" />
                <p>
                  <form:label path="name"> Name</form:label> <br>
                  <form:input type="text" path="name" /><br>
                  <form:errors path="name" />
                </p>
                <p>
                  <form:label path="location">Location</form:label> <br>
                  <form:input type="text" path="location" /><br>
                  <form:errors path="location" />
                </p>
                <p>
                  <form:label path="description">Description</form:label> <br>
                  <form:input type="text" path="description" /><br>
                  <form:errors path="description" />
                </p>
                <p>
                  <form:label path="duration">Duration</form:label> <br>
                  <form:input type="number" path="duration" /><br>
                  <form:errors path="duration" />
                </p>
                <input type="submit" class="btn btn-outline-warning" value="submit">
              </form:form>
              <a href="/dashboard" class="mt-3 btn btn-outline-danger">Dashboard</a>
            </div>

</body>
</html>