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
                  <title>Tacos</title>
                  <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
               </head>

               <body class="bg-dark text-light">
                  <div
                     class="text-center mt-4 p-4 border border-danger position-absolute top-0 start-50 translate-middle-x">

                     <h3>Change your Entry</h3>
                     <form:form action="/updatebook/${book.id}" method="post" modelAttribute="book">
                        <form:hidden path="user" value="${user}" />
                        <p>
                           <form:errors path="title" />
                        </p>
                        <p>
                           <form:errors path="author" />
                        </p>
                        <p>
                           <form:errors path="thoughts" />
                        </p>
                        <p>
                           <form:label path="title">Title</form:label> <br>
                           <form:input type="text" path="title" value="${book.title}" />
                        </p>
                        <p>
                           <form:label path="author">Author</form:label> <br>
                           <form:input type="text" path="author" value="${book.author}" />
                        </p>
                        <p>
                           <form:label path="thoughts">Thoughts</form:label> <br>
                           <form:textarea path="thoughts" />
                        </p>
                        <input type="submit" class="btn btn-outline-warning" value="submit">
                     </form:form>
                     <a href="/dashboard" class="mt-3 btn btn-outline-success">Dashboard</a>

                  </div>
               </body>

               </html>