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
                span{color:rgb(24, 24, 163);}
            </style>
        </head>

        <body class="bg-dark text-light">
            <div class="mt-5">
                <h3 class="text-center">Here is your Omikuji!</h3>
                <div class="container p-4 border border-danger w-50 text-center">
                    <h4>In <span><c:out value="${number}"></c:out></span> years, you will live in <span><c:out value="${city}"></c:out></span>
                        with <span><c:out value="${person}"></c:out></span> as your roommate, selling <span><c:out value="${hobby}">
                        </c:out></span> for a living. The next time you see a <span><c:out value="${livingThing }"></c:out></span>, you will
                        have good luck. Also, <span><c:out value="${somethingNice}"></c:out></span>.
                    </h4>
                    <div class="mt-4 text-center">
                        <a href="/" class="btn btn-danger">Go back</a>
                    </div>
                </div>
            </div>
        </body>

        </html>