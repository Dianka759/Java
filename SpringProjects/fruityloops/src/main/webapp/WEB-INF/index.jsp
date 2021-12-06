<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<!DOCTYPE html>
		<html>

		<head>
			<meta charset="ISO-8859-1">
			<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
			<title>The fruitsies store</title>
			<style>
			body {background-color: black; text-align:-webkit-center;}
			th {color: rebeccapurple;}
			</style>
		</head>

		<body>
			<div class="container m-5 w-25 bg-dark border border-danger shadow shadow-1 text-center text-danger">
				<h1>Fruitsies store</h1>
				<table class="table table-dark table-hover border border-danger">
					<tbody>
						<tr>
							<th> Name </th>
							<th> Price </th>
						</tr>
						<c:forEach var="fruit" items="${fruit}">
							<tr>
								<td>
									<c:out value="${fruit.name}"></c:out>
								</td>
								<td>
									<c:out value="$${fruit.price}"></c:out>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</body>

		</html>