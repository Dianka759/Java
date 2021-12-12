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
            <title>Tacos</title>
            <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
            <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
            <script src="/webjars/jquery/jquery.min.js"></script>
            <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
          </head>

          <body class="bg-dark">
            <div class="container w-50 border border-danger mt-4 p-3 text-light text-center">
              <h1>
                <c:out value="${dojo.name}"></c:out> Location Ninjas
              </h1>
              <table class="table table-dark">
                <thead>
                  <tr>
                    <th>First name</th>
                    <th>Last name</th>
                    <th>Age</th>
                  </tr>
                </thead>
                <tbody>
                  <c:forEach var="i" items="${dojo.ninjas}">
                    <tr>
                      <td>
                        <c:out value="${i.firstName}"></c:out>
                      </td>
                      <td>
                        <c:out value="${i.lastName}"></c:out>
                      </td>
                      <td>
                        <c:out value="${i.age}"></c:out>
                      </td>
                    </tr>
                  </c:forEach>
                </tbody>
              </table>
              <a href="/dashboard" class="btn btn-outline-danger">Go Back</a>
            </div>

          </body>

          </html>