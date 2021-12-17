<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <!-- c:out ; c:forEach etc. -->
  <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!-- Formatting (dates) -->
    <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
      <!-- form:form -->
      <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
      <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
        <!-- for rendering errors on PUT routes -->
        <%@ page isErrorPage="true" %>
          <!DOCTYPE html>
          <html>

          <head>
            <meta charset="UTF-8">
            <title>Show course</title>
            <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
          </head>

          <body class="bg-dark text-light">
             <div class="container w-50 mt-4 p-4 border border-danger">
              <h3 class="text-center">
                <c:out value="${yogaclass.name}"></c:out> with <c:out value="${yogaclass.user.name}"></c:out>
              </h3>
              <h3>Day: <c:out value="${yogaclass.day}"></c:out> </h3>
              <h3>Cost: $<c:out value="${yogaclass.price}"></c:out> </h3>
              <h3>Time: <c:out value="${yogaclass.convertTo24Hour(yogaclass.time)}"></c:out> </h3>
 
              <h3><c:out value="${yogaclass.description}"></c:out> </h3>
              <div class="text-center">
                <a href="/dashboard" class="btn btn-outline-success">Dashboard</a>
              </div>
            </div>
            
             <div class="container w-50 mt-4 p-4 text-center border border-danger">
            <h4> Students</h4>
            <c:forEach var="i" items="${allJoins}">
            <c:if test="${i.studentInClass.id == yogaclass.id}">
            <p> ${i.student.name} - ${i.student.email} </p>
            </c:if>
            </c:forEach>
            </div>
      			
            <div class="container w-75 mt-4 p-4 text-center border border-danger d-flex justify-content-evenly">
            <div>
            <h2> Add a Student </h2>
            <p class="text-danger"> New Student </p>
            <form:form action="/newstudent" method="post" modelAttribute="student">
            <input type="hidden"  name="yogaclass" value="${yogaclass.id}"/>
            <label> Student Name</label> <br>
            <form:input type="text" path="name"/> <br><br>
            <label>Email</label> <br>
            <form:input type="email" path="email"/> <br><br>
            <input type="submit" value="Add Student">       
            </form:form>
            </div>
            <div>
            <h2> Existing Students </h2>
            <form:form action="/createJoin" method="post" modelAttribute="addStudentToClass">
            <input type="hidden"  name="yogaclass" value="${yogaclass.id}"/>
            <form:select path="student">
            <c:forEach var="i" items="${studentList}">
            <form:option value="${i.id}" name="student"> ${i.name} - ${i.email}</form:option>
            </c:forEach>
            </form:select> <br><br>
            <input type="submit" value="Add Student">
            </form:form>
            </div>
            </div>

          </body>

          </html>