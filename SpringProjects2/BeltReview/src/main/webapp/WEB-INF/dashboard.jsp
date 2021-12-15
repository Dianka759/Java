<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tacos</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
</head>
  <body class="bg-dark">
            <div class="container w-50 border border-danger mt-4 p-3 text-light text-center">
              <div class="border border-warning mb-4 p-3">
                <h3> Welcome: <span style="color:purple">
                    <c:out value="${user.firstName}"></c:out>
                    <c:out value="${user.lastName}"></c:out>
                  </span> </h3> <a href="/logout">Logout</a>
              </div>
              <p class="text-danger"><c:out value="${message}"></c:out></p>
              <p>Tablez.</p>
              <table class="table table-dark">
                <thead>
                  <tr>
                    <th>Submitted by</th>
                    <th>Location</th>
                    <th>Duration</th>
                    <th>Name</th>
                    <th>Actions</th>
                  </tr>
                </thead>
                <tbody>
                  <c:forEach var="i" items="${allMis}">
                    <tr>
                      <td>
                        <c:out value="${i.user.firstName}"></c:out> <c:out value="${i.user.lastName}"></c:out>
                      </td>
                      <td>
                          <c:out value="${i.location}"></c:out> 
                      </td>
                      <td>
                        <c:out value="${i.duration}"></c:out> - Hours
                      </td>
                      <td>
                      <a href="/mischief/${i.id}">
                        <c:out value="${i.name}"></c:out>
                      </a>
                      </td>
                      <td>
                      <c:if test="${i.user.id == user.id}">
                      <a href="/edit/${i.id}"> Edit </a> |
                      <a href="/delete/${i.id}"> Delete </a>
                      </c:if>
                      <c:if test="${i.user.id != user.id}">
                      Admire.
                      </c:if>
                      </td>
                  </c:forEach>
                </tbody>
              </table>
              <a href="/newMischief" class="btn btn-outline-success">New mischief</a>
            
            <div class="mt-5">
            <form:form action="/createJoin" method="post" >            
            <label>Select a User</label>
            <select name="user">
            <c:forEach var="i" items="${users}">
            <option value="${i.id}">${i.firstName}</option>
            </c:forEach>
            </select>
            <label>Select a Mischief</label>
            <select name="mischief">
            <c:forEach var="i" items="${allMis}">
            <option value="${i.id}">${i.name}</option>
            </c:forEach>
            </select> <br>
            <input type="submit" value="Create Join" class="mt-4 btn btn-outline-warning">
            </form:form>
            </div>
            
            <div class="mt-5">
             <h2>All the joins</h2>
              <table class="table table-dark">
                <thead>
                  <tr>
                    <th>User</th>
                    <th>Mischief</th>
                    <th>Count of peeps joining the mischief</th>
                    <th>Action </th>
                  </tr>
                </thead>
                <tbody>
                  <c:forEach var="i" items="${allJoins}">
                    <tr>
                      <td>
                        <c:out value="${i.user.firstName}"></c:out> <c:out value="${i.user.lastName}"></c:out>
                      </td>
                      <td>
                        <c:out value="${i.mis.users.size()}"></c:out>
                      </td>
                       <td>
                        <c:out value="${i.mis.name}"></c:out>
                      </td>
                      <td>
                      <a href="/deleteJoin/${i.id}"> Delete </a>
                         </c:forEach>
                </tbody>
              </table>
            </div>
            </div>

</body>
</html>