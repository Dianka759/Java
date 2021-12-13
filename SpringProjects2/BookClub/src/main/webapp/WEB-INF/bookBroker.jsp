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
            <title>Dashboard</title>
            <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
          </head>

          <body class="bg-dark">
            <div class="container w-50 border border-danger mt-4 p-3 text-light text-center">
              <div class="border border-warning mb-4 p-3">
                <h3> Welcome: <span style="color:purple">
                    <c:out value="${user.name}"></c:out></span> </h3>
                   <a href="/dashboard">Dashboard</a> <br>
                   <a href="/logout">Logout</a>
              </div>
              <p class="text-danger"><c:out value="${message}"></c:out></p>
              <h5 class="text-warning">Available Books To Borrow</h5>
              <table class="table table-dark">
                <thead>
                  <tr>
                    <th>ID</th>
                    <th>Title</th>
                    <th>Author Name</th>
                    <th>Posted by</th>
                    <th>Actions</th>
                  </tr>
                </thead>
                <tbody>
                  <c:forEach var="i" items="${books}">
                    <c:if test="${i.borrower==null}">
                    <tr>
                      <td>
                        <c:out value="${i.id}"></c:out>
                      </td>
                      <td>
                        <a href="/books/${i.id}">
                          <c:out value="${i.title}"></c:out>
                        </a>
                      </td>
                      <td>
                        <c:out value="${i.author}"></c:out>
                      </td>
                      <td>
                        <c:out value="${i.user.name}"></c:out>
                      </td>
                      <c:if test="${i.user.id == user.id}">
                      <td>
                      <a href="/books/edit/${i.id}">Edit</a> |
                      <a href="/books/delete/${i.id}">Delete</a> 
                      </td>
                      </c:if>
                      <c:if test="${i.user.id != user.id}">
                      <td>
                        <a href="/book/${i.id}/borrow">Borrow</a>
                      </td>
                      </c:if>
                      </c:if>
                  </c:forEach>
                </tbody>
              </table>   
              
              <div class="mt-4 text-danger">      
               <h5>Books I'm borrowing</h5>
              <table class="table table-dark">
                <thead>
                  <tr>
                    <th>ID</th>
                    <th>Title</th>
                    <th>Author Name</th>
                    <th>Posted by</th>
                    <th>Actions</th>
                  </tr>
                </thead>
                <tbody>
                  <c:forEach var="i" items="${books}">
                  <c:if test="${i.borrower.id == user.id }">
                    <tr>
                      <td>
                        <c:out value="${i.id}"></c:out>
                      </td>
                      <td>
                        <a href="/books/${i.id}">
                          <c:out value="${i.title}"></c:out>
                        </a>
                      </td>
                      <td>
                        <c:out value="${i.author}"></c:out>
                      </td>
                      <td>
                        <c:out value="${i.user.name}"></c:out>
                      </td>
                      <td>
                        <a href="/book/${i.id}/return">Return</a>
                      </td>
                    </c:if>
                  </c:forEach>
                </tbody>
              </table>
            </div>
			</div>
          </body>

          </html>