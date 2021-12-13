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
            <title>All Dorms</title>
            <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
          </head>

          <body class="bg-dark text-light">
            <div class="d-flex justify-content-around">
              <div class="w-50 mt-4 p-3 border border-danger text-center">
                <h1>All Dormitories</h1>
                <table class="table table-dark">
                  <thead>
                    <tr>
                      <th>ID</th>
                      <th>Name</th>
                    </tr>
                  </thead>
                  <tbody>
                    <c:forEach var="i" items="${dorms}">
                      <tr>
                        <td>
                          <c:out value="${i.id}"></c:out>
                        </td>
                        <td>
                          <a href="/dorms/${i.id}">
                            <c:out value="${i.name}"></c:out>
                          </a>
                        </td>
                    </c:forEach>
                  </tbody>
                </table>
                <a href="/dorms/new" class="btn btn-outline-warning">Add a new dorm</a>
              </div>
              <div class="w-25 mt-4 p-3 border border-danger text-center">
                <h1>All students: </h1>
                <c:forEach var="student" items="${students}">
                  <p>
                    <c:out value="${student.name}"></c:out>
                  </p>
                </c:forEach>
                <a href="/students/new" class="btn btn-outline-warning">Add a new Student</a>
              </div>
            </div>
          </body>

          </html>