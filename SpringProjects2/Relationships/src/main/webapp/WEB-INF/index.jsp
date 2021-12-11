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

<body>
<h1>Person Details:></h1>
<table class="table">
    <thead class="thead-dark">
        <tr>
            <th>Name</th>
            <th>License Number</th>
            <th>State</th>
            <th>Exp Date</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>
                <c:out value="${person.firstName}"/>
                <c:out value="${person.lastName}"/>
            </td>
            <td><c:out value="${person.license.state}"/></td>
            <td><c:out value="${person}"/></td>
            <td>
                <c:out value="${person.license.expirationDate}"/>
            </td>
        </tr>
    </tbody>
</table>

<!--- inside the form:form --->
     <form:form action="/licenses" method="post" modelAttribute="license">
    <!--- Drop down select menu --->
    <form:select path="person">
        <c:forEach var="onePerson" items="${persons}">
            <!--- Each option VALUE is the id of the person --->
            <form:option value="${onePerson.id}" path="person">
            <!--- This is what shows to the user as the option --->
                <c:out value="${onePerson.firstName}"/>
                <c:out value="${onePerson.lastName}"/>
            </form:option>
        </c:forEach>
    </form:select>
<!--- ... --->
</form:form>
</body>
</html>