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
            <title>Make new book</title>
            <!-- Bootstrap -->
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
              crossorigin="anonymous">
          </head>

          <body class="bg-dark">
            <div class="container w-25 border border-danger mt-4 p-3 text-light bg-danger text-center">
              <!-- Beginning of Container -->
              <h1>New Book</h1>
              <form:form action="/books" method="post" modelAttribute="book">
                <p>
                  <form:label path="title">Title</form:label> <br>
                  <form:errors path="title" />
                  <form:input path="title" />
                </p>
                <p>
                  <form:label path="description">Description</form:label> <br>
                  <form:errors path="description" />
                  <form:textarea path="description" />
                </p>
                <p>
                  <form:label path="language">Language</form:label> <br>
                  <form:errors path="language" />
                  <form:input path="language" />
                </p>
                <p>
                  <form:label path="numberOfPages">Pages</form:label> <br>
                  <form:errors path="numberOfPages" />
                  <form:input type="number" path="numberOfPages" />
                </p>
                <div class="text-center">
                <input type="submit" value="Submit" class="btn btn-success"/>
                </div>
              </form:form>
              <a href="/books" class="mt-2 btn btn-primary">Go back to all books</a>
            </div> <!-- End of Container -->
          </body>

          </html>