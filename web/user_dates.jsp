<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/Chunks/links.jsp"></jsp:include>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Date History</title>
</head>
<body>
<jsp:include page="/Chunks/Nav.jsp"></jsp:include>
<div class="table table-responsive">


      <table class="table table-striped table-hover">
        <caption><h2>Dates Found:</h2></caption>
        <thead class="">
        <tr>
          <th scope="col">Name</th>
          <th scope="col">Rating</th>
          <th scope="col">Date Visited</th>

        </tr>
        </thead>
        <tbody>
        <c:choose>
        <c:when test="${user_dates.size() != 0}">
        <c:forEach var="i" begin="0" end="${user_dates.size() - 1}">

          <tr>
            <td><c:out value="${user_dates.get(i).getName()}"/></td>
            <td><c:out value="${user_dates.get(i).getNumRating()}"/></td>
            <td><c:out value="${user_dates.get(i).getDateVisited()}"/></td>
          </tr>

        </c:forEach>
        </c:when>
        <c:otherwise>
          <h2>No dates found! Go out and find some!</h2>

        </c:otherwise>
        </c:choose>
        </tbody>
      </table>



</div>
</body>
</html>
