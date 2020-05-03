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
        <caption><h2>&nbsp;My Dates</h2></caption>
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

    <table class="table table-striped table-hover" id="locations">
        <caption><h2>&nbsp;Luvr Recommendations</h2>
            <div class="col-sm-2">
                <form method="post" action="<%= request.getContextPath()%>/userdates">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="col-sm-6">
                                <label for="zip">Zip code:</label>
                                <input type="text" id="zip" name="zip" required class="form-control"><br>
                            </div>
                            <div class="col-sm-6">
                                <br>
                                <input type="submit" value="Gets!" class="btn btn-default" align="center">
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </caption>
        <thead class="">
        <tr>
            <th scope="col" onclick="arrowed()">Name <i class="fas fa-sort"></i></th>
            <th scope="col" onclick="arrowed()">Rating <i class="fas fa-sort"></i></th>
            <th scope="col" onclick="arrowed()">Price <i class="fas fa-sort"></i></th>
            <th scope="col" data-sorter="false">Phone</th>
            <th scope="col" data-sorter="false">Location</th>
            <th scope="col" data-sorter="false">Directions</th>
            <th scope="col" data-sorter="false">Yelp Page</th>
        </tr>
        </thead>
        <tbody>

        <c:choose>
            <c:when test="${recDates != null}">
                <c:set var="datenum" scope="session" value="${recDates.size() - 1}"/>

                <c:forEach var="i" begin="0" end="${datenum}">

                    <tr>
                        <td><c:out value="${recDates.get(i).getName()}"/></td>
                        <td><c:out value="${recDates.get(i).getRating()}"/></td>
                        <td style="color: limegreen"><c:out value="${recDates.get(i).getPrice()}"/></td>
                        <td><a href="tel:${recDates.get(i).getPhone()}">${recDates.get(i).getPhone()}</a></td>
                        <td><c:out value="${recDates.get(i).getLocation()}"/></td>
                        <td><a href="https://maps.google.com?saddr=Current+Location&daddr=${recDates.get(i).location}"
                               target="_blank"><i class="fas fa-directions"></i></a></td>
                        <td><a href="${recDates.get(i).getUrl()}"><i class="fab fa-yelp"></i></a></td>
                    </tr>

                </c:forEach>
            </c:when>

        </c:choose>
        </tbody>
    </table>
</div>
</div>
</body>
</html>
