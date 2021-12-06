<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<title>Hopper's Receipt</title>
<style>
body{color:white; background-color:black;}</style>
</head>
<body>
	<div class="container w-50 mt-4 border border-danger">
  		<h1> Customer name: <c:out value="${name}"/></h1>
  		<h4> Price: <c:out value="$${price}"/></h4>
  		<h4> Description: <c:out value="${description}"/></h4>
  		<h4> Vendor: <c:out value="${vendor}"/></h4>
  	</div>	
</body>
</html>