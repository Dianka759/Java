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
            <title>Show one book</title>
            <!-- Bootstrap -->
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
              crossorigin="anonymous">
              <style>
                span{color:blueviolet}
              </style>
          </head>

          <body class="bg-dark">
            <div class="container w-50 border border-danger mt-4 p-3 text-light">
              <!-- Beginning of Container -->
              <h1 class="text-center">Title: <span><c:out value="${book.title}"></c:out></span></h1>
              <div class="bg-dark p-4">
              <h4>Description: <span><c:out value="${book.description}"></c:out></span></h4>
              <h4>Language: <span><c:out value="${book.language}"></c:out></span></h4>
              <h4>Number of Pages: <span><c:out value="${book.numberOfPages}"></c:out></span></h4>
              </div>
              <div class="text-center m-3">
              <a href="/books" class="btn btn-primary">Go back to all books</a>
              <a href="/books/new" class="btn btn-primary">Add a new book</a>
</div>
            </div> <!-- End of Container -->
          </body>

          </html>