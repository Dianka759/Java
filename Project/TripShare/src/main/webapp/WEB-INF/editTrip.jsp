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
    <link rel="stylesheet" type="text/css" href="/css/createstyle.css">
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
        </div>
    </div>
    <c:if test="${trip.name == null }">
    <h2>Update your trip</h2>
    <form:form action="/updatetrip/${trip.id}" method="post" modelAttribute="updateTripshare">
    <form:hidden path="user" value="${user}"/>
        <label>Name your trip:</label>
        <form:input type="text" path="name"/>
        <label>Start Date</label>
        <form:input type="date" path="startDate"/>
        <label>End Date</label>
        <form:input type="date" path="endDate"/>
        <input type="submit" value="Create Trip">
        </form:form>
    </c:if>
    
        <c:if test="${trip.name != null}">
    <h2> Update your <span class="fw-bold" style="color:rgb(79, 112, 68)">${trip.name}</span> trip!</h2>
        <div class="row">
        <div class="col-sm">
                <table class="table table-striped table-light">
                    <thead>
                    <tr>
                        <th scope="col">Locations</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="i" items="${trip.tripLocations}">
                    <tr>
                        <td class="align-middle">
                            ${i.name} - ${i.address} - $${i.cost} -
                             <a href="${i.outsideLink}" id="GFG">Outside Link</a> | 
                            <a href="/delete/location/${i.id}/${trip.id}" id="GFG">Delete</a>
                        </td>
                    </tr>
                    </c:forEach>
                    <tr>
                    <td> 
                    <form:form action="/addLocation/${trip.id}" method="post" modelAttribute="newLocation">
                    <form:hidden path="trip" value="${trip.id}"/>
                    <form:errors path="name"/>
                    <form:input style="width:120px" type="text" path="name" placeholder="name"/>
                    <form:errors path="address"/>
                    <form:input style="width:120px" type="text" path="address" placeholder="address"/>
                    <form:errors path="cost"/>
                    <form:input style="width:120px" type="text" path="cost" placeholder="cost"/>
                    <form:errors path="outsideLink"/>
                    <form:input style="width:120px" type="text" path="outsideLink" placeholder="outside link"/>
                    <input type="submit" value="add" class="btn btn-outline-success">
                    </form:form>
                    </td>
                    </tr>
                    </tbody>
                </table>
                    <table class="table-right table table-striped table-light">
                        <thead>
                        <tr>
                            <th scope="col">Airbnb Suggestions</th>
                            <th scope="col">Add to Trip</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td class="align-middle">
                                <a id="GFG" href="/">Suggestion link</a>
                            </td>
                            <td class="align-middle">
                                <a id="GFG" href="/">+</a>
                            </td>
                        </tr>
                        <tr>
                            <td class="align-middle">
                                <a id="GFG" href="/">Suggestion link</a>
                            </td>
                            <td class="align-middle">
                                <a id="GFG" href="/">+</a>
                            </td>
                        </tr>
                        <tr>
                            <td class="align-middle">
                                <a id="GFG" href="/">Suggestion link</a>
                            </td>
                            <td class="align-middle">
                                <a id="GFG" href="/">+</a>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
              <%--   <form:form action="/addLocation/${trip.id}" method="post" modelAttribute="newLocation">
                    <label>Add a few destinations by entering links below:</label>
                    <form:input type="text" path="name" placeholder="name"/>
                    <button type="submit">Add</button>
                </form:form> --%>
                <div class="friend-table">
                    <table class="table-right table table-striped table-light">
                        <thead>
                        <tr>
                            <th scope="col">Participants</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="i" items="${participants}">
                        <c:if test="${i.trip.id == trip.id }">
                        <tr>
                            <td class="align-middle">
                                <a id="GFG" href="/">${i.user.firstName}</a>
                                </td>
                                  <td class="align-middle">
                                  <c:if test="${i.user.id != user.id}">
                                <a id="GFG" href="/deleteParticipant/${i.id}/${trip.id}">Remove</a>
                                  </c:if>
                            </td>                 
                        </tr>
                        </c:if>
                        </c:forEach>
                        </tbody>
                    </table>
                    <table class="table-right table table-striped table-light">
                        <thead>
                        <tr>
                            <th scope="col">Friends</th>
                            <th scope="col">Add to Trip</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="i" items="${friendsNot}"> 
                         <c:if test="${user.myFriends.contains(i)}"> 
                        <tr>
				            <td class="align-middle">
                               <a id="GFG" href="/">${i.firstName }</a>
                            </td>
                            <td class="align-middle">
                            <form:form action="/addToTrip2/${i.id}/${trip.id}" method="post" modelAttribute="addToTrip">
                                <input type="submit" value="+" class="btn btn-outline-success">
                            </form:form>
                            </td>
                            </tr>
                            </c:if> 
                            </c:forEach>
<%--                            <c:if test="${friendsNot.size() <= user.myFriends.size()}">
                           <tr>
                           <td><h2> Ya bringing all of your friends with'cha!</h2></td>
                           </tr>
                           </c:if> --%>
                        </tbody>
                    </table>
                </div>
            </div>
            <a href="/trip/${trip.id}"><button type="submit">Update</button></a>
        <div class="img"></div> 
        </c:if>
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