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
            <title>Expense Tracker</title>
            <!-- Bootstrap -->
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
              crossorigin="anonymous">
          </head>

          <body class="bg-dark">
            <div class="container">
              <!-- Beginning of Container -->
              <h1 class="text-center text-light">All Expenses</h1>
              <table class="table table-dark">
                <thead>
                  <tr>
                    <th>Expense</th>
                    <th>Vendor</th>
                    <th>Amount</th>
                    <th>Actions</th>
                  </tr>
                </thead>
                <tbody>
                  <c:forEach var="i" items="${expenses}">
                    <tr>
                      <td>
                        <c:out value="${i.name}"></c:out>
                      </td>
                      <td>
                        <c:out value="${i.vendor}"></c:out>
                      </td>
                      <td>
                        <c:out value="$${i.amount}"></c:out>
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
            <div class="container w-50 border border-danger mt-4 p-3 text-light text-center">
              <!-- Beginning of Container -->
              <h1>Track an expense</h1>
              <form:form action="/makeExpense" method="post" modelAttribute="expense">
                <p>
                  <form:errors path="name" />
                </p>
                <p>
                  <form:errors path="vendor" />
                </p>
                <p>
                  <form:errors path="amount" />
                </p>
                <p>
                  <form:errors path="description" />
                </p>
                <p>
                  <form:label path="name">Expense</form:label> <br>
                  <form:input type="text" path="name" />
                </p>
                <p>
                  <form:label path="vendor">Vendor</form:label> <br>
                  <form:input path="vendor" />
                </p>
                <p>
                  <form:label path="amount">Amount</form:label> <br>
                  <form:input type="number" path="amount" />
                </p>
                <p>
                  <form:label path="description">Description</form:label> <br>
                  <form:textarea path="description" />
                </p>
                <div class="text-center">
                  <input type="submit" value="Submit" class="btn btn-success" />
                </div>
              </form:form>
            </div> <!-- End of Container -->
            <!-- End of Container -->
          </body>

          </html>