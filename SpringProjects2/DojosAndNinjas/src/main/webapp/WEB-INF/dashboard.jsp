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

          <body>

            <body class="bg-dark">
              <div class="container w-50 border border-danger mt-4 p-3 text-light text-center">
                <h1> All Dojos and Ninjas </h1>
                <p class="text-danger">
                  <c:out value="${message}"></c:out>
                </p>
                <table class="table table-dark">
                  <thead>
                    <tr>
                      <th>First name</th>
                      <th>Last name</th>
                      <th>Age</th>
                      <th>Dojo</th>
                      <th>Actions</th>
                    </tr>
                  </thead>
                  <tbody>
                    <c:forEach var="i" items="${ninjas}">
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
                        <td>
                          <c:out value="${i.dojo.name }"></c:out>
                        </td>
                        <td><a href="/delete/<c:out value=" ${i.id}" />">Delete</a></td>
                      </tr>
                    </c:forEach>
                  </tbody>
                </table>
                <a href="/dojos/new" class="btn btn-outline-danger">Make a new dojo</a>
                <a href="/ninjas/new" class="btn btn-outline-danger">Make a new ninja</a>
              </div>

            </body>

          </html>