<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="ISO-8859-1">
            <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
            <title>Omikuji Fortune</title>
            <style>
                .container{background: blueviolet;}
            </style>
        </head>

        <body class="bg-dark text-light">
            <div class="mt-5">
                <h3 class="text-center">Here is your Omikuji!</h3>
                <div class="container p-4 border border-danger w-50">
                    <h3> In <c:out value="${number}"></c:out> years, you will live in <c:out value="${city}"></c:out>
                        with <c:out value="${person}"></c:out> as your roommate, selling <c:out value="${hobby}">
                        </c:out> for a living. The next time you see a <c:out value="${livingThing }"></c:out>, you will
                        have good luck. Also, <c:out value="${somethingNice}"></c:out>.
                    </h3>
                    <div class="mt-4 text-center">
                        <a href="/" class="btn btn-danger">Go back</a>
                    </div>
                </div>
            </div>
        </body>

        </html>