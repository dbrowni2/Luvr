<jsp:include page="/Chunks/links.jsp"></jsp:include>
<%@ page import="javax.servlet.http.*,javax.servlet.*, java.io.*, java.net.*" %>


<div class="dateFinder" style="padding-left: 30pt; padding-right: 30pt">
    <h1>Find dates near you!</h1>
    <div class="col-sm-4">
        <div class="form-group">
            <label for="zip">Enter your ZIP code (ex: 28223):</label>
            <input type="text" class="form-control" id="zip">
        </div>
    </div>
    <div class="col-sm-8">
        <label>Select the range you'd like to search in: </label>
        <br>
        <label class="radio-inline"><input type="radio" name="optradio" checked>5 miles</label>
        <label class="radio-inline"><input type="radio" name="optradio">10 miles</label>
        <label class="radio-inline"><input type="radio" name="optradio">25 miles</label>
    </div>
    <br>
    <div>

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

    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

    String inputLine;
    while ((inputLine = in.readLine()) != null)
        System.out.println(inputLine);
    in.close();


%>