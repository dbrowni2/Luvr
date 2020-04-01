<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/Chunks/links.jsp"></jsp:include>
<%@ page import="javax.servlet.http.*,javax.servlet.*, java.io.*, java.net.*, org.json.*" %>



<div class="dateFinder" style="padding-left: 30pt; padding-right: 30pt">
    <form Method="post" action="<%= request.getContextPath()%>/dates">
    <h1>Find dates near you!</h1>
    <div class="col-sm-4">
        <div class="form-group">
            <label for="zip">Enter your ZIP code (ex: 28223):</label>
            <input type="zip" class="form-control" id="zip" name="zip" required="true">
        </div>
    </div>
        <input type="submit" value="findDates" class="btn btn-default"><br>
</form>

    <div class="col-sm-8">
        <label>Select the range you'd like to search in: </label>
        <br>
        <label class="radio-inline"><input type="radio" name="optradio" checked>5 miles</label>
        <label class="radio-inline"><input type="radio" name="optradio">10 miles</label>
        <label class="radio-inline"><input type="radio" name="optradio">25 miles</label>
    </div>
    <br>
    <div>

        <c:choose>
            <c:when test="${dates != null}">
                <table border="2" cellpadding="10">
                    <caption><h2>Dates in: <c:out value="${param.get('zip')}"/></h2></caption>
                    <tr>
                        <th>Image</th>
                        <th>Name</th>
                        <th>Rating</th>
                        <th>Phone</th>
                        <th>Price</th>
                        <th>Location</th>
                        <th>Link</th>

                    </tr>
                    <c:forEach var = "i" begin = "0" end = "${dates.size() - 1}">

                    <tr>
                        <td><img source="${dates.get(i).getImg_url()}"></td>
                        <td><c:out value="${dates.get(i).getName()}"/> </td>
                        <td><c:out value="${dates.get(i).getRating()}"/> </td>
                        <td><c:out value="${dates.get(i).getPhone()}"/> </td>
                        <td><c:out value="${dates.get(i).getPrice()}"/> </td>
                        <td><c:out value="${dates.get(i).getLocation()}"/> </td>
                        <td><a href="${dates.get(i).getUrl()}">Yelp</a> </td>
                    </tr>

               </c:forEach></p>
                </table>
            </c:when>
            <c:otherwise>
            <p>Search for a location!</p>
            </c:otherwise>
        </c:choose>
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