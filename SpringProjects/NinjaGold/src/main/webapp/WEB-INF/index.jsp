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
            <title>Ninja Gold</title>
            <!-- Bootstrap -->
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
              crossorigin="anonymous">
            <style>
              .green {
                color: green;
              }

              .red {
                color: red;
              }
            </style>
          </head>

          <body>
            <div class="container mt-4">
              <!-- Beginning of Container -->
              <p class="text-warning">Total Gold: <c:out value="${gold}"></c:out>
              </p>

              <div class="row text-center gap-3">
                <div class="col border border-danger p-3 mt-2 shadow-sm">
                  <h3>Farm</h3>
                  <p>(earns 10-20 golds) </p>
                  <input type="hidden" name="building" value="farm" />
                  <form action="process" method="post">
                    <input type="hidden" name="message" value="'>You have entered a farm and ">
                    <input type="hidden" name="min" value="10">
                    <input type="hidden" name="max" value="10">
                    <button type="submit" class="btn btn-outline-warning">Find Gold!</button>
                  </form>
                </div>


                <div class="col border border-danger p-3 mt-2 shadow-sm">
                  <h3>Cave</h3>
                  <p>(earns 5-10 golds) </p>
                  <form action="process" method="post">
                    <input type="hidden" name="message" value="'>You have entered a cave and ">
                    <input type="hidden" name="min" value="5">
                    <input type="hidden" name="max" value="5">
                    <button type="submit" class="btn btn-outline-warning">Find Gold!</button>
                  </form>
                </div>


                <div class="col border border-danger p-3 mt-2 shadow-sm">
                  <h3>House</h3>
                  <p>(earns 2-5 golds) </p>
                  <form action="process" method="post">
                    <input type="hidden" name="message" value="'>You have entered a house and ">
                    <input type="hidden" name="min" value="2">
                    <input type="hidden" name="max" value="3">
                    <button type="submit" class="btn btn-outline-warning">Find Gold!</button>
                  </form>
                </div>


                <div class="col border border-danger p-3 mt-2 shadow-sm">
                  <h3>Casino</h3>
                  <p>(earns/takes 0-50 golds) </p>
                  <form action="process" method="post">
                    <input type="hidden" name="message" value="'>You have entered a casino and ">
                    <input type="hidden" name="min" value="-50">
                    <input type="hidden" name="max" value="100">
                    <button type="submit" class="btn btn-outline-warning">Find Gold!</button>
                  </form>
                </div>
              </div>

              <div class="mt-5">
                <label for="activites">Activities:</label>
                <div class="p-2 border border-3 border-warning ">
                  <p>
                    <c:forEach var="item" items="${log}">
                      ${item}<br>
                    </c:forEach>
                  <p>
                </div>
              </div>
              <div class="mt-4 d-flex justify-content-center">
                <form action="process" method="post">
                  <input type="hidden" name="message">
                  <input type="hidden" name="min" value="0">
                  <input type="hidden" name="max" value="0">
                  <input class="form-control" type="hidden" name="reset" value="reset">
                  <button class="btn btn-danger" type="submit"> Restart the Game!</button>
                </form>
              </div>
            </div> <!-- End of Container -->
          </body>

          </html>