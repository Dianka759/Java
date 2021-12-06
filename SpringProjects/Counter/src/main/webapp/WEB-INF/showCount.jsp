<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<title>Visited counter page</title>
</head>
<body class="bg-dark">
<div class="container w-75 p-4 mt-5 border border-danger text-center text-light">
    <p> You have visited the previous page: <span class="text-danger">
        <c:out value="${countToShow}"/>
        </span></p>
    <div class="d-flex justify-content-evenly">    
    <a href="/">Visit it again!</a>
    <a href="/addTwo">Add two visits to the counter!</a>
    <form action="/reset" method="post">
    <button class="btn-danger" type="submit">RESET</button>
    </form>
    </div>
</div>
</body>
</html>