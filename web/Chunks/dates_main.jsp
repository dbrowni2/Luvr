<!-- TESTING SITE BEGINS-->
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<!-- TESTING SITE ENDS-->


<jsp:include page="/Chunks/links.jsp"></jsp:include>
<script src="${pageContext.request.contextPath}/static/scripts/jquery.tablesorter.min.js"></script>
<script>
    $(function() {
        $("#locations").tablesorter();
    });
    function arrowed() {
        let i = 0;
        for (let h of $("#locations")[0].config.sortVars) {
            if (h.sortedBy === "user") {
                if (h.count === 0)
                    document.getElementsByTagName("TH")[i].getElementsByTagName("I")[0].className = "fas fa-sort-up";
                else
                    document.getElementsByTagName("TH")[i].getElementsByTagName("I")[0].className = "fas fa-sort-down";
            } else {
                document.getElementsByTagName("TH")[i].getElementsByTagName("I")[0].className = "fas fa-sort";
            }
            i++;
        }
    }
</script>
<%@ page import="javax.servlet.http.*,javax.servlet.*, java.io.*, java.net.*, org.json.*" %>

<div class="dateFinder" style="padding-left: 30pt; padding-right: 30pt">
    <form Method="post" action="<%= request.getContextPath()%>/dates">
        <h1>Find dates near you!</h1>
        <div class="col-sm-8 col-lg-4">
            <div class="col-sm-6">
                <div class="form-group">
                    <label for="zip">Enter your ZIP code (ex: 28223):</label>
                    <input type="text" class="form-control" id="zip" name="zip" required
                           pattern="(^\d{5}([ \-]\d{4})?$)">
                </div>
            </div>
            <div class="col-sm-6">
                <br>
                <input type="submit" value="Find Dates" class="btn btn-default">
            </div>
        </div>

        <div class="col-sm-1 col-lg-4">
            <div class="col-sm-4">
                <label>Select the range you'd like to search in: </label>
                <br>
                <label class="radio-inline"><input type="radio" name="optradio" value="8046"checked>5 miles</label>
                <label class="radio-inline"><input type="radio" name="optradio" value="16093">10 miles</label>
                <label class="radio-inline"><input type="radio" name="optradio" value="40000">25 miles</label>
            </div>


            <br>
        </div>
    </form>
    <div style="padding: 20pt; align-content: center">
    <div class="col-12 col-sm-12 col-lg-12">
        <div class="table table-responsive">
            <c:choose>
            <c:when test="${dates != null}">
                <table class="table table-striped table-hover" id="locations">
                    <caption><h2>Dates Found:</h2></caption>
                    <thead class="">
                    <tr>
                        <th scope="col" onclick="arrowed()">Name <i class="fas fa-sort"></i></th>
                        <th scope="col" onclick="arrowed()">Rating <i class="fas fa-sort"></i></th>
                        <th scope="col" onclick="arrowed()">Price <i class="fas fa-sort"></i></th>
                        <th scope="col" data-sorter="false">Phone</th>
                        <th scope="col" data-sorter="false">Location</th>
                        <th scope="col" data-sorter="false">Directions</th>
                        <th scope="col" data-sorter="false">Yelp Page</th>
                        <th scope="col" data-sorter="false">Record</th>
                        <th scope="col" data-sorter="false">Invite</th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:if test="${dates != null}">
                        <c:set var="datenum" scope="session" value="${dates.size() - 1}"/>


                    <c:forEach var="i" begin="0" end="${datenum}">

                        <tr>
                            <td><c:out value="${dates.get(i).getName()}"/></td>
                            <td><c:out value="${dates.get(i).getRating()}"/></td>
                            <td style="color: limegreen"><c:out value="${dates.get(i).getPrice()}"/></td>
                            <td><a href="tel:${dates.get(i).getPhone()}">${dates.get(i).getPhone()}</a></td>
                            <td><c:out value="${dates.get(i).getLocation()}"/></td>
                            <td><a href="https://maps.google.com?saddr=Current+Location&daddr=${dates.get(i).location}"
                                   target="_blank"><i class="fas fa-directions"></i></a></td>
                            <td><a href="${dates.get(i).getUrl()}"><i class="fab fa-yelp"></i></a></td>
                            <c:choose>
                                <c:when test="${user != null}">
                            <td><a href="<c:url value="/Home?action=add_date">
                            <c:param name="date" value="${dates.get(i).getId()}"/>
                            </c:url>" title="I've been here!"><i class="fas fa-clipboard"></i></a></td>

                                    <td><a href="<c:url value="/Home?action=invite">
                            <c:param name="date" value="${dates.get(i).getId()}"/>
                            </c:url>" title="Invite Someone!"><i class="fas fa-clipboard"></i></a></td>

                                </c:when>
                                <c:otherwise> <td><a href="<c:url value="/Home?action=login">
                            </c:url>" title="Must be logged in" disabled><i class="fas fa-clipboard"></i></a></td></c:otherwise>
                            </c:choose>
                        </tr>

                    </c:forEach>
                    </c:if>
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
