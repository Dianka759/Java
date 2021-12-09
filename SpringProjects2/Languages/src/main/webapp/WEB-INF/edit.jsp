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
            <title>Edit an expense</title>
            <!-- Bootstrap -->
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
              crossorigin="anonymous">
          </head>

          <body class="bg-dark">
            <div class="container w-25 border border-danger mt-4 p-3 text-light bg-danger text-center">
              <!-- Beginning of Container -->
              <form:form action="/updateLanguage/${language.id}" method="post" modelAttribute="language">
                <input type="hidden" name="_method" value="put">
                <p>
                  <form:errors path="name" />
                </p>
                <p>
                  <form:errors path="creator" />
                </p>
                <p>
                  <form:errors path="currentVersion" />
                </p>
                <p>
              <%--     <form:errors path="id" />
                  <form:input type="hidden" path="id" value="${expense.id}" /> --%>
                </p>
                <p>
                  <form:label path="name">Language</form:label> <br>
                  <form:input path="name" />
                </p>
                <p>
                  <form:label path="creator">Creator</form:label> <br>
                  <form:input path="creator" />
                </p>
                <p>
                  <form:label path="currentVersion">Version</form:label> <br>
                  <form:input type="text" path="currentVersion" />
                </p>
                <div class="text-center">
                  <input type="submit" value="Submit" class="btn btn-success" />
                </div>
              </form:form>
              <a href="/languages" class="btn btn-warning mt-2"> Go Back</a>
            </div>
          </body>

          </html>