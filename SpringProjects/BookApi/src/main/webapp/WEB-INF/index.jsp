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
            <title>Title Here</title>
            <!-- Bootstrap -->
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
              crossorigin="anonymous">
          </head>

          <body>
            <div class="container">
              <!-- Beginning of Container -->
              <h1>All Books</h1>
              <table>
                <thead>
                  <tr>
                    <th>ID</th>
                    <th>Title</th>
                    <th>Language</th>
                    <th>Number of Pages</th>
                  </tr>
                </thead>
                <tbody>
                  <!-- loop over all the books to show the details as in the wireframe! -->
                  <c:forEach var="i" items="${books}">
                    <tr>
                      <td>
                        <c:out value="${i.title}"></c:out>
                      </td>
                      <td>
                        <c:out value="${i.description}"></c:out>
                      </td>
                      <td>
                        <c:out value="${i.language}"></c:out>
                      </td>
                      <td>
                        <c:out value="${i.numberOfPages}"></c:out>
                      </td>
                    </tr>
                  </c:forEach>
                </tbody>
              </table>
            </div> <!-- End of Container -->
          </body>

          </html>