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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Josefin+Sans:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;1,100;1,200;1,300;1,400;1,500;1,600;1,700&family=Nanum+Pen+Script&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="/css/dashboardstyles.css">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TripShare</title>
</head>
<body>
    <div id="nav-bar">
        <h1><a href="/dashboard" style="text-decoration:none; color:black;">Trip<span class="title">Share</span></a></h1>
        <div class="friendsearch">
            <input class="friends" type="text" placeholder="search for friends">
            <img class="search-btn" src="/img/icons8-enter-24.png" alt="enter icon">
            <a href="/logout" class="ms-4">Logout</a>
           
        </div>
    </div>

    <div class="header">
        <h2>Welcome to TripShare, <span class="text-success">${user.firstName} ${user.lastName}</span></h2>
        <div class="buttons">
          <a href="/new/tripshare"><button>+ create a TripShare</button></a>
        </div>
    </div>
    <h3>Check out these links for inspiration</h3>
    <div class="row">
        <div class="col-sm">
            <table class="table table-striped table-light">
                <thead>
                <tr>
                    <th scope="col">Locations</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="i" items="${locations}">
                <tr>
                    <td class="align-middle">
                        <a id="GFG" href="${i.outsideLink}" target="_blank">${i.name} - ${i.address}</a>
                    </td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
                <table class="table-right table table-striped table-light">
                    <thead>
                    <tr>
                        <th scope="col">Friends</th>
                    </tr>
                    </thead>
                    <tbody>
                     <c:forEach var="i" items="${allJoins}"> 
                     <c:if test="${i.user.id == user.id }">
                                <tr>
                                    <td class="align-middle">
                                    <a id="GFG" href="/">${i.friend.firstName}</a> </td>
                                    <td class="align-middle">
                                    <a href="/deleteFriendship/${i.id}">Delete</a>
                                    </td>
                                </tr>
                     </c:if>
                            </c:forEach>
                    </tbody>
                </table>
        </div>
        <div class="col-right">
            <table class="table table-striped table-light">
                <thead>
                    <tr>
                        <th scope="col">TripShares</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="i" items="${user.participating}">
                    <tr>
                        <td class="align-middle">
                            <a id="GFG" href="/trip/${i.id}">${i.name}</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
       
            <table class="table table-striped table-light">
                <thead>
                    <tr>
                        <th scope="col">Potential Friends</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="i" items="${potentialFriends}">
                <c:if test="${user.id != i.id }">
                <c:if test="${!user.myFriends.contains(i)}">
                    <tr>
                        <td class="align-middle">
                            ${i.firstName}
                        </td>
                        <td class="align-middle">
                        <a href="/addFriend/${i.id}">Add Friend</a>
                        </td>
                    </tr>
                    </c:if>
                </c:if>
                </c:forEach>
                </tbody>
            </table>
        </div>
        </div>
    <div class="img"></div>
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