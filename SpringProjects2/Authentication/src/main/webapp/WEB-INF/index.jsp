<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
    href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
    crossorigin="anonymous">

<title>Login & Registration</title>
</head>
<body>
    <div class="container mt-3">
    <form:form action="/register" method="post" modelAttribute="newUser">
    <h1 class="text-danger"><c:out value="${error}"></c:out></h1>
        <div class="form-group">
            <label>User Name:</label>
            <form:input path="userName" class="form-control" />
            <form:errors path="userName" class="text-danger" />
        </div>
        <div class="form-group">
		<label> Gender:</label> <br>
		<div class="d-flex justify-content-between">
        <p><form:radiobutton path="gender" value="Female"/> Female</p>
		<p><form:radiobutton path="gender" value="Male"/> Male</p>
		<p><form:radiobutton path="gender" value="Nonbinary"/> Nonbinary</p>
		<p><form:radiobutton path="gender" value="other"/> Other</p>
        </div>
        <p><form:errors path="gender" class="text-danger" /></p>
        </div>
        <label> Favorite Language: </label>
        <div class="form-group">
        <form:select path="language">
        <form:option value="" path="language">  </form:option>
	    <form:option value="HTML/CSS" path="language"> HTML/CSS </form:option>
	    <form:option value="Python" path="language"> Python</form:option>
	    <form:option value="Java" path="language"> Java</form:option>
		</form:select>
		<p><form:errors path="language" class="text-danger" />
		</div>
        <div class="form-group">
            <label>Email:</label>
            <form:input path="email" class="form-control" />
            <form:errors path="email" class="text-danger" />
        </div>
        <div class="form-group">
            <label>Password:</label>
            <form:password path="password" class="form-control" />
            <form:errors path="password" class="text-danger" />
        </div>
        <div class="form-group">
            <label>Confirm Password:</label>
            <form:password path="confirm" class="form-control" />
            <form:errors path="confirm" class="text-danger" />
        </div>
        <input type="submit" value="Register" class="btn btn-primary" />
    </form:form>
    
    <form:form action="/login" method="post" modelAttribute="newLogin">
        <div class="form-group">
            <label>Email:</label>
            <form:input path="email" class="form-control" />
            <form:errors path="email" class="text-danger" />
        </div>
        <div class="form-group">
            <label>Password:</label>
            <form:password path="password" class="form-control" />
            <form:errors path="password" class="text-danger" />
        </div>
        <input type="submit" value="Login" class="btn btn-success" />
    </form:form>
    </div>
</body>
</html>