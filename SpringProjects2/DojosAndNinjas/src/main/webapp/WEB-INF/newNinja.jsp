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
            <title>New Ninja</title>
            <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
          </head>

          <body class="bg-dark">
            <div class="container w-50 border border-danger mt-4 p-3 text-light text-center">
              <!-- Beginning of Container -->
              <h1>New Ninja</h1>
              <form:form action="/newninja" method="post" modelAttribute="ninja">
                <p>
                  <form:errors path="firstName" />
                </p>
                <p>
                  <form:errors path="lastName" />
                </p>
                <p>
                  <form:errors path="age" />
                </p>
                <p>
                  <form:errors path="dojo" />
                </p>

                <form:select path="dojo">
                  <%-- <form:option value="">
                    </form:option> --%>
                    <c:forEach var="dojo" items="${dojos}">
                      <form:option value="${dojo.id}">
                        <c:out value="${dojo.name}" />
                      </form:option>
                    </c:forEach>
                </form:select>

                <p>
                  <form:label path="firstName">First Name</form:label> <br>
                  <form:input type="text" path="firstName" />
                </p>
                <p>
                  <form:label path="lastName">Last Name</form:label> <br>
                  <form:input type="text" path="lastName" />
                </p>
                <p>
                  <form:label path="age">Age</form:label> <br>
                  <form:input type="number" path="age" />
                </p>
                <input type="submit" class="btn btn-outline-warning" value="submit">
              </form:form>
              <a href="/dashboard" class="mt-3 btn btn-outline-danger">Dashboard</a>
            </div>

          </body>

          </html>