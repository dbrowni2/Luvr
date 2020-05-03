<%--
  Created by IntelliJ IDEA.
  User: brown
  Date: 1/30/2020
  Time: 3:48 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Luvr</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">
    <jsp:include page="/Chunks/links.jsp"></jsp:include>
</head>
<body>
<header>
    <jsp:include page="/Chunks/Nav.jsp"></jsp:include>
</header>
<main>
    <div class="container">
        <div class="jumbotron" id="leader">
            <h1 class="display-4", h1 style="font-family: Algerian">Luvr</h1>
            <p class="lead", p style="font-family: Georgia"><i>So, you finally found a date</i> ❤ <i>...now what?</i>
            <p>This is where Luvr comes in. Our mission is to provide anyone, anywhere, no matter their origin,
                background, beliefs or interests, with the ability to find interesting places to take others for dates.
                Using Luvr, you can get date recommendations based on your location and past “liked” dates.</p>
            <p> If you’re looking for a short, low price date--here you go! If you’re looking to pop the question and
                want an extravagant date--look no further!</p>
            <p> We give you the ability to quickly find date recommendations based on your preferences and you
                can even share your favorite dates or find others’ favorite dates.</p>
            <br>
            <i><p style="font-family: 'Lucida Calligraphy'", p align="right">~Good Luck<br><img
                    src="${pageContext.request.contextPath}/static/images/LuvrLogo_dhrough.png" alt="luvrlogo"
                    id="logo"></p></i>
        </div>
    </div>
</main>
</body>
</html>