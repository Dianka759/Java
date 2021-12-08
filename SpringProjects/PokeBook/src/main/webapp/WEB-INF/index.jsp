<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
   <!-- c:out ; c:forEach ; c:if -->
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
   <!-- Formatting (like dates) -->
 <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
   <!-- form:form -->
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
   <!-- for rendering errors on PUT routes -->
 <%@ page isErrorPage="true" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Title Here</title>
  <!-- Bootstrap -->
  <link rel="stylesheet"
    href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
    crossorigin="anonymous">
</head>
<body>
    <div class="container"> <!-- Beginning of Container -->
        <h1>All Expenses</h1>
              <table class="table table-dark">
                <thead>
                  <tr>
                    <th>Expense</th>
                    <th>Vendor</th>
                    <th>Amount</th>
                  </tr>
                </thead>
                <tbody>
                <c:forEach var="i" items="${expenses}">
                <tr>
                	<td><c:out value="${i.expense}"></c:out></td>
                	<td><c:out value="${i.vendor}"></c:out></td>
                	<td><c:out value="$${i.amount}"></c:out></td>
                </tr>
				 </c:forEach>
                </tbody>
              </table>
    </div>
    <div class="container w-50 border border-danger mt-4 p-3 text-light bg-danger text-center">
              <!-- Beginning of Container -->
              <h1>Track an expense</h1>
              <form:form action="/expensez" method="post" modelAttribute="expense">
                 <p> <form:errors path="expense" /> </p>
                 <p> <form:errors path="vendor" /> </p>
                 <p> <form:errors path="amount" /> </p>
                <p>
                  <form:label path="expense">Expense</form:label> <br>
                  <form:input path="expense" />
                </p>
                <p>
                  <form:label path="vendor">Vendor</form:label> <br>
                  <form:textarea path="vendor" />
                </p>
                <p>
                  <form:label path="amount">Amount</form:label> <br>
                  <form:input type="number" path="amount" />
                </p>
                <div class="text-center">
                <input type="submit" value="Submit" class="btn btn-success"/>
                </div>
              </form:form>
            </div> <!-- End of Container --> <!-- End of Container -->
</body>
</html>