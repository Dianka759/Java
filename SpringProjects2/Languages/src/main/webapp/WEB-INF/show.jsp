<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <!-- c:out ; c:forEach ; c:if -->
  <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!-- Formatting (like dates) -->
    <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
      <!-- form:form -->
      <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <!-- for rendering errors on PUT routes -->
        <%@ page isErrorPage="true" %>
          <!DOCTYPE html>
          <html>
          <head>
            <meta charset="UTF-8">
            <title>Show one Language</title>
            <!-- Bootstrap -->
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
              crossorigin="anonymous">
              <style>
                span{color:blueviolet}
              </style>
          </head>

          <body class="bg-dark">
            <div class="container w-25 border border-danger mt-4 p-3 text-light">
              <!-- Beginning of Container -->
              <h2 class="text-center"> Language Details: </h2>
              <div class="bg-dark p-4">
              <h4>Expense: <span><c:out value="${language.name}"></c:out></span></h4>
              <h4>Creator: <span><c:out value="${language.creator}"></c:out></span></h4>
              <h4>Version: <span><c:out value="${language.currentVersion}"></c:out></span></h4>
              </div>
              <div class="text-center m-3">
              <a href="/languages" class="btn btn-primary">Go back</a>
</div>
            </div> <!-- End of Container -->
          </body>

          </html>