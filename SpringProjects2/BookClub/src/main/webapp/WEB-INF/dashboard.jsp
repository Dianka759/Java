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
                    <c:out value="${user.name}"></c:out>
                  </span> </h3> <a href="/logout">Logout</a>
              </div>
              <c:out value="${message}"></c:out>
              <p>Books from everyone's shelves:
              <table class="table table-dark">
                <thead>
                  <tr>
                    <th>ID</th>
                    <th>Title</th>
                    <th>Author Name</th>
                    <th>Posted by</th>
                  </tr>
                </thead>
                <tbody>
                  <c:forEach var="i" items="${books}">
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
                  </c:forEach>
                </tbody>
              </table>
              <a href="/books/new" class="btn btn-outline-success">Make a new book</a>
            </div>

          </body>

          </html>