<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<script type="text/javascript" src="js/app.js"></script>
<title>Insert title here</title>
</head>
<body onload="date()" class="bg-dark">
<div class="container w-75 mt-5 text-primary text-center">
	<h1><c:out value="${date}"/></h1>
	<a href="/"> back </a>
	</div>
</body>
</html>