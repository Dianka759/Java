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
   <body class="bg-dark text-light">
	<div class="container w-50 mt-4 border border-warning p-4">
	<div class="search-bar">
		<h3>Songs by artist: <span style="color:purple"><c:out value="${artist}"></c:out></span></h3>
	</div>
    <table class="table table-dark">
    			<thead>
        			<tr>
            			<th>ID</th>
            			<th>Name</th>
            			<th>Artist</th>
            			<th>Rating</th>
            			<th class="text-center">Action</th>
        			</tr>
    			</thead>
    			<tbody>
					<c:forEach var="songs" items="${songs}">
					<tr>
						<td><c:out value="${songs.id}"/></td>
						<td><a href="/lookify/song/${songs.id}"><c:out value="${songs.title}"/></a></td>
						<td><c:out value="${songs.artist}"/></td>
						<td><c:out value="${songs.rating}"/> star(s)</td>
						<td class="text-center"><a href="/lookify/song/edit/${songs.id}">Edit</a> |
						<a href="/lookify/song/delete/${songs.id}">Delete</a></td>
					</tr>
					</c:forEach>
				</tbody>
    		</table>
    		<div class="text-center">
    		<a href="/lookify/song/new" class="btn btn-outline-success">Add a new song</a> <br>
    		<a href="/lookify/dashboard" class="mt-2 btn btn-outline-primary">Dashboard</a>
	</div>
	</div>
</body>
</html>