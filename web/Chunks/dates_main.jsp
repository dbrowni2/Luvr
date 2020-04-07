<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/Chunks/links.jsp"></jsp:include>



<div class="dateFinder" style="padding-left: 30pt; padding-right: 30pt">
    <form Method="post" action="<%= request.getContextPath()%>/dates">
        <h1>Find dates near you!</h1>
        <div class="col-sm-8 col-lg-4">
            <div class="col-sm-6">
                <div class="form-group">
                    <label for="zip">Enter your ZIP code (ex: 28223):</label>
                    <input type="zip" class="form-control" id="zip" name="zip" required="true"
                           pattern="(^\d{5}([ \-]\d{4})?$)">
                </div>
            </div>
            <div class="col-sm-6">
                <br>
                <input type="submit" value="Find Dates" class="btn btn-default">
            </div>
        </div>
    </form>
    <div class="col-sm-1 col-lg-4">
        <div class="col-sm-4">
            <label>Select the range you'd like to search in: </label>
            <br>
            <label class="radio-inline"><input type="radio" name="optradio" value="8046">5 miles</label>
            <label class="radio-inline"><input type="radio" name="optradio" value="16093">10 miles</label>
            <label class="radio-inline"><input type="radio" name="optradio" value="40000">25 miles</label>
        </div>
    </form>

    <div class="col-sm-4">

    </div>
    <br>
</div>
<div style="padding: 20pt; align-content: center">
    <div class="col-12 col-sm-12 col-lg-12">
        <div class="table table-responsive">
            <c:choose>
            <c:when test="${dates != null}">
                <table class="table table-striped table-hover">
                    <caption><h2>Dates Found:</h2></caption>
                    <thead class="">
                    <tr>
                        <th scope="col">Name</th>
                        <th scope="col">Rating</th>
                        <th scope="col">Phone</th>
                        <th scope="col">Price</th>
                        <th scope="col">Location</th>
                        <th scope="col">Directions</th>
                        <th scope="col">Yelp Page</th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:if test="${dates.size() != 0}">
                        <c:set var="datenum" scope="request" value="${dates.size() - 1}"/>

                    </c:if>
                    <c:forEach var="i" begin="0" end="${datenum}">

                        <tr>
                            <td><c:out value="${dates.get(i).getName()}"/></td>
                            <td><c:out value="${dates.get(i).getRating()}"/></td>
                            <td><a href="tel:${dates.get(i).getPhone()}">${dates.get(i).getPhone()}</a></td>
                            <td><c:out value="${dates.get(i).getPrice()}"/></td>
                            <td><c:out value="${dates.get(i).getLocation()}"/></td>
                            <td><a href="https://maps.google.com?saddr=Current+Location&daddr=${dates.get(i).location}"
                                   target="_blank">Get Directions</a></td>
                            <td><a href="${dates.get(i).getUrl()}">Yelp</a></td>
                            <c:choose>
                                <c:when test="${user != null}">
                            <td><a href="<c:url value="${pageContext.request.contextPath}/Home?action=add_date">
                            <c:param name="date" value="${dates.get(i).getId()}"/>
                            </c:url>">I've been here!</a></td>
                                </c:when>
                                <c:otherwise> <td><a href="<c:url value="${pageContext.request.contextPath}/Home?action=login">
                            </c:url>">You must be logged in record a date!</a></td></c:otherwise>
                            </c:choose>
                        </tr>

                    </c:forEach>
                    </tbody>
                </table>
        </div>

        </c:when>
            <c:otherwise>
                <div style="padding-left: 30%; padding-right: 30%; padding-top: 10pt">
                    <div class="panel panel-default">
                        <div class="panel-heading">Search for a date location!</div>
                        <div class="panel-body">Enter your location using a zipcode, and then search for dates in the
                            area!
                            You can check the directions, call them, and check our their Yelp page before you decide to
                            go there!
                        </div>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
        </div>
    </div>
</div>