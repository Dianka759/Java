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
            <title>One book</title>
            <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
          </head>

          <body class="bg-dark text-light">
            <div class="mt-4 p-4 border border-danger position-absolute top-0 start-50 translate-middle-x">
              <h1 class="text-center">
                <c:out value="${book.title}"></c:out>
              </h1>

              <c:choose>
                <c:when test="${book.user.id == user.id}">
                  <p><span class="text-danger">You</span> read <span class="text-primary">
                      <c:out value="${book.title}"></c:out>
                    </span> by <span class="text-warning">
                      <c:out value="${book.author}"></c:out>
                    </span> </p>
                  <p><span class="text-danger">Your</span> thoughts: </p>
                </c:when>
                <c:otherwise>
                  <p><span class="text-danger">
                      <c:out value="${book.user.name}"></c:out>
                    </span> read <span class="text-primary">
                      <c:out value="${book.title}"></c:out>
                    </span> by <span class="text-warning">
                      <c:out value="${book.author}"></c:out>
                    </span> </p>
                  <p><span class="text-danger">
                      <c:out value="${book.user.name}"></c:out>
                    </span> Thoughts: </p>
                </c:otherwise>
              </c:choose>

              <p> _________________________________________________</p>
              <c:out value="${book.thoughts}"></c:out>
              <p> _________________________________________________</p>

              <c:if test="${book.user.id == user.id}">
                <div class="d-flex justify-content-around">
                  <a href="/books/edit/${book.id}" class="btn btn-outline-warning">Edit</a>
                  <a href="/books/delete/${book.id}" class="btn btn-outline-danger">Delete</a>
                </div>
              </c:if>
              <c:if test="${book.user.id != user.id}">
                can't edit/delete, it's not ya book.
              </c:if>
              <div class="mt-3 text-center">
                <a href="/dashboard" class="btn btn-outline-success">Dashboard</a>
              </div>
            </div>
          </body>

          </html>