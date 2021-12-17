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
                <div class="container text-light mt-4 text-center">
                    <h3 class="text-danger">
                        <c:out value="${error}"></c:out>
                    </h3>
                    <div class="d-flex justify-content-around">
                        <div class="border border-warning p-4">
                            <h3> Register </h3>
                            <form:form action="/register" method="post" modelAttribute="newUser">
                                <div class="form-group">
                                    <label>First name:</label>
                                    <form:input path="firstName" class="form-control" />
                                    <form:errors path="firstName" class="text-danger" />
                                </div>
                                <div class="form-group">
                                    <label>Last Name:</label>
                                    <form:input path="lastName" class="form-control" />
                                    <form:errors path="lastName" class="text-danger" />
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
                                <div class="mt-2 form-group">
                                <label> Guest or Host?</label>
                                <form:select path="status">
                                <form:option value="Host">Host</form:option>
                                <form:option value="Guest">Guest</form:option>
                                </form:select>
                                </div>
                                <input type="submit" value="Register" class="mt-4 btn btn-primary" />
                            </form:form>
                        </div>
                        <div class="border border-warning p-4">
                            <h3> Login </h3>
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
                                <input type="submit" value="Login" class="mt-4 btn btn-success" />
                            </form:form>
                        </div>
                    </div>
                </div>
            </body>
   
</body>
</html>