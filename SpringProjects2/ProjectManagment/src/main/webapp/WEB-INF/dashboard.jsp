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
            <title>Dashboard</title>
            <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
          </head>

          <body class="bg-dark">
            <div class="container w-50 border border-danger mt-4 p-3 text-light text-center">
              <div class="border border-warning mb-4 p-3">
                <h3> Welcome: <span style="color:purple">
                    <c:out value="${user.firstName}"></c:out></span> </h3>
                   <a href="/logout">Logout</a> <br>
                   <a href="/project/new">New Project</a>
              </div>
              <p class="text-danger"><c:out value="${message}"></c:out></p>
              <h5 class="text-warning">All Projects</h5>
              <table class="table table-dark">
                <thead>
                  <tr>
                    <th>Project</th>
                    <th>Team Lead</th>
                    <th>Due Date</th>
                    <th>Actions</th>
                  </tr>
                </thead>
                <tbody>
                  <c:forEach var="i" items="${projects}">
                  <c:if test="${i.user.id != user.id}">
                    <tr>
                      <td>
                       <a href="/showProject/${i.id}">
                        <c:out value="${i.title}"></c:out>
                        </a>
                      </td>
                      <td>
                          <c:out value="${i.user.firstName}"></c:out>
                      </td>
                      <td>
                        <fmt:formatDate value="${i.dueDate}" pattern="MMM dd"/>
                      </td>                  
                      <td>
                       <form action="/createJoin" method="post">
                        <input type="hidden" name="project" value="${i.id}">
                        <input type="hidden" name="user" value="${user.id}">
                        <input type="submit" value="Join Team">
                      </form>
                      </td> 
                      </c:if>                  
                  </c:forEach>
                </tbody>
              </table>   
              
              <div class="mt-4 text-danger">      
               <h5>Your Projects</h5>
              <table class="table table-dark">
                <thead>
                  <tr>
                    <th>Project</th>
                    <th>Team Lead</th>
                    <th>Due Date</th>
                    <th>Actions</th>
                  </tr>
                </thead>
                <tbody>
                  <c:forEach var="i" items="${allJoins}">
                   <c:if test="${i.user.id == user.id}">
                    <tr>
                      <td>
                       <a href="/project/${i.project.id}">
                        <c:out value="${i.project.title}"></c:out>
                        </a>
                      </td>
                      <td>
                          <c:out value="${i.project.user.firstName}"></c:out>
                      </td>
                      <td>
                       <fmt:formatDate value="${i.project.dueDate}" pattern="MMM dd"/>
                      </td>  
                      <c:if test="${i.project.user.id != user.id}">                
                      <td>
                        <a href="/deleteJoin/${i.id }">Leave Team</a>
                      </td>
                      </c:if>
                       <c:if test="${i.project.user.id == user.id}">
                       <td>
                        <a href="/edit/${i.project.id}">Edit</a>                                           
                      </td>
                      </c:if>
                      </c:if>
                  </c:forEach>
                  
                  
                </tbody>
              </table>
            </div>
			</div>
          </body>

          </html>