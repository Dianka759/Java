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
    <title>Dashboard</title>
        <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    
</head>
<body class=" bg-dark text-light">               
<div class="container text-light mt-4 text-center">
<h1>Current Listings</h1>
<a href="/logout">Logout</a>

<table class="table table-dark">
	 <thead>
	   <tr>
	     <th>Address</th>
	     <th>Pool Size</th>
	     <th>Cost per night</th>
	     <th>Details</th>
	   </tr>
	 </thead>
	 <tbody>
	 <c:forEach var="i" items="${pools}">
	<tr>
	<td><c:out value="${i.address}"/></td>
	<td><c:out value="${i.poolSize}"/></td>
	<td><c:out value="$${i.price}"/></td>						
	<td><a href="/edit/${i.id}"> - edit</a></td>
	</tr>
	</c:forEach>
	</tbody>
	</table>
	
	<div class="container">
	<h2> New Listing </h2>
	
	<form:form action="/makeListing" method="post" modelAttribute="newListing">
	<form:input type="hidden" path="user" value="${user.id}"/>
	
	<label> Address </label>
	<form:errors path="address"/>
	<form:input type="text" path="address"/> <br> <br>
	<label>Description</label>
	<form:errors path="description"/>
	<form:textarea path="description"/> <br> <br>
	<label>Cost per night</label>
	<form:errors path="price"/>
	<form:input type="number" path="price"/> <br> <br>
	<label>Pool size</label>
	<form:errors path="poolSize"/>
	<form:select path="poolSize">
	<form:option path="poolSize" value="small">Small</form:option>
	<form:option path="poolSize" value="medium">Medium</form:option>
	<form:option path="poolSize" value="large">Large</form:option>
	</form:select> <br> <br>
	<input type="submit" value="Add Listing"/>
	</form:form>
	
	</div>
   </div>
</body>
</html>