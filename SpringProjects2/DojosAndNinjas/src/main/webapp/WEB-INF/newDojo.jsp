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
            <title>Dojos and Ninjas</title>
            <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
          </head>

          <body class="bg-dark">
            <div class="container w-50 border border-danger mt-4 p-3 text-light text-center">
              <!-- Beginning of Container -->
              <h1>New Dojo</h1>
              <form:form action="/newdojo" method="post" modelAttribute="dojo">
                <p>
                  <form:errors path="name" />
                </p>
                <p>
                  <form:label path="name">Name</form:label> <br>
                  <form:input type="text" path="name" />
                </p>
                <input type="submit" class="btn btn-outline-warning" value="submit">
              </form:form>
              <a href="/dashboard" class="mt-3 btn btn-outline-danger">Dashboard</a>
            </div>

          </body>

          </html>