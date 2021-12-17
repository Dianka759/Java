<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!-- c:out ; c:forEach etc. -->
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!-- Formatting (dates) -->
        <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
            <!-- form:form -->
            <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
                <!-- for rendering errors on PUT routes -->
                <%@ page isErrorPage="true" %>
                    <!DOCTYPE html>
                    <html>

                    <head>
                        <meta charset="UTF-8">
                        <title>New class</title>
                        <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
                    </head>

                    <body class="bg-dark text-light">
                        <div class="container w-50 mt-4 p-4 text-center border border-danger">
                            <h1>Create a course!</h1>
                            <form:form action="/newclass" method="post" modelAttribute="class">
                                <form:hidden path="user" value="${user}" />
                                <p>
                                    <form:errors path="name" />
                                </p>
                                <p>
                                    <form:errors path="day" />
                                </p>
                                <p>
                                    <form:errors path="price" />
                                </p>
                                 <p>
                                    <form:errors path="time" />
                                </p> 
                                <p>
                                    <form:errors path="description" />
                                </p>
                                <p>
                                    <form:label path="name">Name</form:label> <br>
                                    <form:input type="text" path="name" />
                                </p>
                                <p>
                                    <form:label path="day">Day of the Week</form:label> <br>
                                    <form:input type="text" path="day" />
                                </p>
                                  <p>
                                    <form:label path="price">Drop-in Price</form:label> <br>
                                    <form:input type="number" path="price" />
                                </p>
                                <p>
                                    <form:label path="time">Time</form:label> <br>
                                    <form:input type="time" path="time" pattern="hh:mm:ss" />
                                </p> 
                                <p>
                                    <form:label path="description">Description</form:label> <br>
                                    <form:textarea path="description" />
                                </p>
                                <input type="submit" class="btn btn-outline-warning" value="submit">
                            </form:form>
                            <a href="/dashboard" class="mt-3 btn btn-outline-success">Cancel</a>
                        </div>
                    </body>

                    </html>