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
                <h3> Namaste, <span style="color:purple">
                    <c:out value="${user.name}"></c:out>
                  </span> </h3> <a href="/logout">Logout</a>
              </div>
              <p class="text-danger"><c:out value="${message}"></c:out></p>
              <p>Course Schedule:
              <table class="table table-dark">
                <thead>
                  <tr>
                    <th>Class Name</th>
                    <th>Instructor</th>
                    <th>Weekday</th>
                    <th>Price</th>
                    <th>Time</th>
                  </tr>
                </thead>
                <tbody>
                  <c:forEach var="i" items="${classes}">
                    <tr>
                      <td>
                        <a href="/class/${i.id}"><c:out value="${i.name}"></c:out></a>
                        <c:if test="${i.user.id == user.id}">
                        / <a href="/class/edit/${i.id}"> Edit</a>
                        </c:if>
                      </td>
                      <td>
                          <c:out value="${i.user.name}"></c:out>
                      </td>
                      <td>
                        <c:out value="${i.day}"></c:out>
                      </td>
                      <td>
                        <c:out value="$${i.price}"></c:out>
                      </td>
                      <td>
                        <c:out value="${i.convertTo24Hour(i.time)}"></c:out>
                      </td>
                  </c:forEach>
                </tbody>
              </table>
              <a href="/class/new" class="btn btn-outline-success">Make a new class!</a> <br>
            </div>

          </body>

          </html>