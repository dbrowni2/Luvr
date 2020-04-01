<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/Chunks/links.jsp"></jsp:include>
<%@ page import="javax.servlet.http.*,javax.servlet.*, java.io.*, java.net.*, org.json.*" %>



<div class="dateFinder" style="padding-left: 30pt; padding-right: 30pt">
    <form Method="post" action="<%= request.getContextPath()%>/dates">
        <h1>Find dates near you!</h1>
        <div class="col-sm-4">
            <div class="col-sm-6">
                <div class="form-group">
                    <label for="zip">Enter your ZIP code (ex: 28223):</label>
                    <input type="zip" class="form-control" id="zip" name="zip" required="true">
                </div>
            </div>
            <div class="col-sm-6">
                <br>
                <input type="submit" value="Find Dates" class="btn btn-default">
            </div>
        </div>
    </form>
    <div class="col-sm-4">
        <label>Select the range you'd like to search in: </label>
        <br>
        <label class="radio-inline"><input type="radio" name="optradio" checked>5 miles</label>
        <label class="radio-inline"><input type="radio" name="optradio">10 miles</label>
        <label class="radio-inline"><input type="radio" name="optradio">25 miles</label>
    </div>
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
                    <caption><h2>Dates in: <c:out value="${param.get('zip')}"/></h2></caption>
                    <thead class="">
                    <tr>
                        <th scope="col">Image</th>
                        <th scope="col">Name</th>
                        <th scope="col">Rating</th>
                        <th scope="col">Phone</th>
                        <th scope="col">Price</th>
                        <th scope="col">Location</th>
                        <th scope="col">Link</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="i" begin="0" end="${dates.size() - 1}">

                        <tr>
                            <td><img source="${dates.get(i).getImg_url()}"></td>
                            <td><c:out value="${dates.get(i).getName()}"/></td>
                            <td><c:out value="${dates.get(i).getRating()}"/></td>
                            <td><c:out value="${dates.get(i).getPhone()}"/></td>
                            <td><c:out value="${dates.get(i).getPrice()}"/></td>
                            <td><c:out value="${dates.get(i).getLocation()}"/></td>
                            <td><a href="${dates.get(i).getUrl()}" class="img-responsive">Yelp</a></td>
                        </tr>

                    </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
            <p>Search for a location!</p>
            </c:otherwise>
        </c:choose>
        </div>
    </div>
</div>

<%
    StringBuilder res = new StringBuilder();
    URL url = new URL("https://api.yelp.com/v3/businesses/search?location=28223&radius=5000");
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setDoInput(true);
    conn.setDoOutput(true);
    conn.setRequestMethod("GET");
    conn.setRequestProperty("Authorization", "Bearer MSTNVPev1fJLQUOBG-Ck-sBgnRomemMu6urbfvbzNF_S_RdD1CIs4W0sruIstSMNw3A1d749wVs4QFoSibdJwXC6mjwIZ2MhZVshXKtIJejLRIVm3XKXTzcfzCdYXnYx");


%>