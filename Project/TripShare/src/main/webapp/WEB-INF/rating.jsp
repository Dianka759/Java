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
    <link
        href="https://fonts.googleapis.com/css2?family=Josefin+Sans:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;1,100;1,200;1,300;1,400;1,500;1,600;1,700&family=Nanum+Pen+Script&display=swap"
        rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/css/mainstyles.css">
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

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
        <input type="text" placeholder="search for friends">
    </div>
    <!-- Beginning of Container -->
    <div class="container">
        <div class="row">
            <div class="col-sm">
                <br>
                <h2>${location.name}</h2>

                <div class="text-right cross"> <i class="fa fa-times mr-2"></i> </div>
                <div class="card-body text-center"> 
                    <img src=" https://i.imgur.com/d2dKtI7.png" height="100" width="100">
                    <br>
                    <form:form action="/leaverating/${location.id}/${trip.id}" method="post" modelAttribute="postReview">
                    
                    <div class="rating">
                        <form:radiobutton path="rating" value="1" id="1"/>
                        <label for="1">1 </label> 
                        <form:radiobutton path="rating" value="2" id="2"/>
                        <label for="2">2 </label>
                        <form:radiobutton path="rating" value="3" id="3"/>
                        <label for="3">3</label>
                        <form:radiobutton path="rating" value="4" id="4"/>
                        <label for="4">4 </label>
                        <form:radiobutton path="rating" value="5" id="5"/>
                        <label for="5">5</label> <br>
                        
                    </div>
                    <br>
                    <br>
                    <div class="comment-box text-center">
                        <h4>Add a comment (Optional)</h4>
                        <div class="comment-area">
                            <form:textarea class="form-control" style="width: 700px;" path="content" rows="4"></form:textarea>
                        </div>
                        <div class="text-center mt-4"> 
                            <button class="btn btn-success send px-5">Submit Review
                                <i class="fa fa-long-arrow-right ml-1"></i>
                            </button>    
                        </div>
                    </div>
                        </form:form>
                        <a href="/trip/${trip.id}"><button class="mt-3 btn btn-success">Go Back</button></a>
                </div>
                <div class="container mt-5 mb-5">
                    <div class="row d-flex justify-content-center">
                        <div class="col-md-6">
                        <h2> What other people said:</h2>
                        <c:forEach var="i" items="${location.ratings}">
                            <div class="mt-2 comment-section">
                                <div class="card p-3 border">
                                    <div class="d-flex justify-content-between mt-2">
                                        <div class="d-flex flex-row">
                                            <div class="d-flex flex-column">
                                                <h6 class="mb-0">${i.user.firstName} ${i.user.lastName}</h6>
                                                <div>${i.rating}</div>
                                            </div>
                                        </div>
                                    </div>
                                    <p class="content"> ${i.content}
                                    </p>
                                </div>
                                </div>
                       </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- End of Container -->
</body>
<footer>
    <p>Any issues?</p>
    <p>Contact us at:</p>
    <a href="/">tripsharehelp@gmail.com</a>
</footer>

</html>