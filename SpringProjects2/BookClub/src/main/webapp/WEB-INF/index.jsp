<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
                    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
                    crossorigin="anonymous">

                <title>Login & Registration</title>
            </head>

            <body class="bg-dark">
                <div class="container text-light mt-4 text-center">
                    <h1 class="text-danger"> Book Club</h1>
                    <p> A place for friends to share thoughts on books.</p>
                    <h3 class="text-danger">
                        <c:out value="${error}"></c:out>
                    </h3>
                    <div class="d-flex justify-content-around">
                        <div class="border border-warning p-4">
                            <h3> Register </h3>
                            <form:form action="/register" method="post" modelAttribute="newUser">
                                <div class="form-group">
                                    <label>Name:</label>
                                    <form:input path="name" class="form-control" />
                                    <form:errors path="name" class="text-danger" />
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
                                <input type="submit" value="Login" class="btn btn-success" />
                            </form:form>
                        </div>
                    </div>
                </div>
            </body>

            </html>