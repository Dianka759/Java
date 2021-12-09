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
            <title>Languages</title>
            <!-- Bootstrap -->
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
              crossorigin="anonymous">
          </head>

          <body class="bg-dark">
            <div class="container">
              <!-- Beginning of Container -->
              <h2 class="text-center text-light">All Languages</h2>
              <table class="table table-dark">
                <thead>
                  <tr>
                    <th>Name</th>
                    <th>Creator</th>
                    <th>Version</th>
                    <th>Actions</th>
                  </tr>
                </thead>
                <tbody>
                  <c:forEach var="i" items="${languages}">
                    <tr>
                      <td>
                        <c:out value="${i.name}"></c:out>
                      </td>
                      <td>
                        <c:out value="${i.creator}"></c:out>
                      </td>
                      <td>
                        <c:out value="${i.currentVersion}"></c:out>
                      </td>
                      <td>
                        <a href="/show/${i.id}">Show</a> |
                        <a href="/edit/${i.id}">Edit</a> |
                        <a href="/delete/${i.id}">Delete</a>
                      </td>

                    </tr>
                  </c:forEach>
                </tbody>
              </table>
            </div>
            <div class="container w-75 border border-danger mt-4 p-3 text-light text-center">
              <!-- Beginning of Container -->
              <h2>Make a new language or something idk</h2>
              <form:form action="/makeLanguage" method="post" modelAttribute="language">
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
                  <form:label path="name">Name</form:label> <br>
                  <form:input type="text" path="name" />
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
            </div> <!-- End of Container -->
          </body>

          </html>