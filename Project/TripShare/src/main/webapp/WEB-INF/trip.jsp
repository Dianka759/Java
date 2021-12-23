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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"crossorigin="anonymous">
    <!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="/css/trip.css">

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TripShare</title>
    
</head>
<body>
    <div id="nav-bar">
        <div>
            <h1><a href="/dashboard" style="text-decoration:none; color:black;">Trip<span class="title">Share</span></a></h1>
        </div>
        <div class="friendsearch">
            <input class="friends" type="text" placeholder="search for friends">
            <img class="search-btn" src="/img/icons8-enter-24.png" alt="enter icon">
            <a href="/logout" id="logout" class="ms-4">Logout</a>
        </div>
    </div>
    <!-- Beginning of Container -->
    <div class="container">
        <div class="row">
                <p>
                    <span style="color: rgb(79, 112, 68); font-weight: 500; font-size: 30px;"> <c:out value="${trip.name}"></c:out> </span>
                    <br>Hosted by: <c:out value="${trip.user.firstName}"></c:out> <c:out value="${trip.user.lastName}"></c:out>
                </p>
        </div>

        
        <div class="row">
            <div class="col-sm">
<div class="row">
            <div class="col-sm">
                <h3>Locations</h3>
                <table class="table table-striped table-light" style="width: max-content;">
                    <thead>
                        <tr>
                            <th scope="col">Name</th>
                            <th scope="col">Address</th>
                            <th scope="col">Cost</th>
                            <th scope="col">Link</th>
                            <th scope="col">Avg Rating</th>

                        </tr>
                        </thead>
                    <tbody>
                    <c:forEach var="i" items="${trip.tripLocations}">
                    <tr>
                        <td class="align-middle border-right">
                            <a href="${i.outsideLink}" target="_blank" id="GFG">${i.name}</a>
                        </td>
                        <td class="align-middle border-right">
                            ${i.address}
                        </td>
                        <td class="align-middle border-right">
                            $${i.cost}
                        </td>
                        <td class="align-middle border-right">
                            <a href="${i.outsideLink}" target="_blank" id="GFG">AirBnB Link</a>
                        </td>
                        <td class="align-middle text-center">
                            ${i.getAvgerageRating()}/5 <a href="/rating/${i.id}/${trip.id}"> <br><button class="btn btn-outline-success">Leave Rating</button></a>
                        </td>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <h4 class="text-center"> Comments </h4>
	             <c:forEach var="i" items="${comments}">
	             <c:if test="${trip.id == i.trip.id }">
	             <div class="border mb-1 p-1 comments">
	             <h4> ${i.user.firstName}</h4>
	             <p> ${i.description} </p>
	             </div>
	             </c:if>
	             </c:forEach>
	            
                <br>
                <div class="form-outline">
                    <label class="form-label" for="textAreaExample2">Discuss amongst friends:</label>
                    <form:form action="/comment/${trip.id}" method="post" modelAttribute="addComment">
                    <form:hidden path="user" value="${user.id}"/>
                    <form:hidden path="trip" value="${trip.id}"/>
                    <form:errors path="description" class="text-danger"/>
                    <form:textarea class="form-control" id="textAreaExample2" rows="8" placeholder="leave a comment..." path="description"></form:textarea>
                    <br>
                    <div class="text-center">
                    <input type="submit" value="Submit" class="btn btn-outline-success">
                    </div>
                    </form:form>
                  </div>
                <br>
            </div>
            <div class="col-sm">
                <div class="thumbnail text-center">
                    <img src="/img/showpageimg.jpeg" alt="image here" class="img-responsive">
                    <div class="caption">
                        <table class="table table-striped table-light center">
                            <thead>
                                <tr>
                                    <th scope="col">TripSharers</th>
                                </tr>
                            </thead>    
                            <tbody>
                            <c:forEach var="i" items="${trip.usersParticipating}"> 
                                <tr>
                                    <td class="align-middle">
                                    <a href="/">${i.firstName}</a>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <br>
                        <c:if test="${trip.user.id == user.id }">
                        <div class="d-flex justify-content-center">
                            <a href="/trip/edit/${trip.id}" style="color:white"><button>Edit This TripShare</button></a>
                        </div>
                        </c:if>
                    </div>

                    <br>

                </div>
                <!-- Only host of trip can view this button -->
                <br>

            </div>   
        </div>

    <!-- End of Container -->
    </div>
</body>
<footer class="mt-5">
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