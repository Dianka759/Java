<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- c:out ; c:forEach ; c:if -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (like dates) -->
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Josefin+Sans:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;1,100;1,200;1,300;1,400;1,500;1,600;1,700&family=Nanum+Pen+Script&display=swap" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/css/mainstyles.css">

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TripShare</title>
</head>
<body>
    <div id="nav-bar">
        <h1>Trip<span class="title">Share</span></h1>
        <div class="dropdown" style="float:right;">
            <button class="dropbtn"><img src="/img/icons8-menu-30.png" alt="hamburger menu"></button>
            <div class="dropdown-content">
                <a href="/newtrip">Plan a TripShare</a>
                <a href="/dashboard">TripShares</a>
                <a href="/dashboard">Invite friends</a>
            </div>
        </div>
    </div>
    <img class="img">
    <h2>-Your plans start here-</h2>
    <p class="error"><c:out value="${error}"></c:out></p>
    <div class="center-contain">
        <div class="log-reg">
            <div class="tabs">
            </div>
            <div class="reg">
                <h3>Register</h3>
                <form:form action="/register" method="post" modelAttribute="newUser">
                <form:errors path="firstName" id="error" /> 
                <form:input type="text" path="firstName" placeholder="first name"/> <br>
                <form:errors path="lastName" id="error" />
                <form:input type="text" path="lastName" placeholder="last name"/> <br>
                <form:errors path="email" id="error" />
                <form:input type="text" path="email" placeholder="email"/> <br>
                <form:errors path="password" id="error" />
                <form:input type="password" path="password" placeholder="password"/> <br>
                <form:errors path="confirm" id="error" />
                <form:input type="password" path="confirm" placeholder="confirm password"/> 
                <button type="submit">Join</button>
                </form:form>
            </div>
            <div class="log">
                <h3>Login</h3>
            <form:form action="/login" method="post" modelAttribute="newLogin">
                <form:errors path="email" id="error" />
                <form:input type="text" path="email" placeholder="email"/> <br>
                <form:errors path="password" id="error" />
                <form:input type="password" path="password" placeholder="password"/> <br>
                <button type="submit">Enter</button>
             </form:form>
             </div>
        </div>
    </div>
</body>
<footer>
    <div class="foot">
        <div class="foot-left">
            <p>Any issues? Feel free to reach out at:</p>
            <a href="">tripsharehelp@gmail.com</a>
        </div>
        <div class="foot-right">
            <img src="https://img.icons8.com/fluency/48/000000/facebook-new.png"/>
            <img src="https://img.icons8.com/color/48/000000/instagram-new--v1.png"/>
            <img src="https://img.icons8.com/fluency/48/000000/twitter.png"/>
        </div>
    </div>
</footer>
</html>