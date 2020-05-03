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
            <h1 class="display-4"> Luvr</h1>
            <p class="lead"><i>So, you finally found a date</i> ❤ <i>...now what?</i></p>
            <p>This is where Luvr comes in. Our mission is to provide anyone, anywhere, no matter their origin,
                background, beliefs or interests, with the ability to find interesting places to take others for dates.
                This includes user-sourced information for what places offer what services, how well suited the location
                is for dates. Using Luvr, users can get date recommendations based on their location, the weather
                conditions, and past “liked” dates. If you’re looking for a short, low price date - you can choose that
                kind of date! If you’re looking to pop the question and want an extravagant date, you can also choose
                that! We give you the ability to quickly find date recommendations based on your preferences, and you
                can even share your favorite dates or find others’ favorite dates.
            </p>
            <br>
            <i><p align="right">-good luck <img
                    src="${pageContext.request.contextPath}/static/images/LuvrLogo_dhrough.png" alt="luvrlogo"
                    id="logo"></p></i>
        </div>
    </div>
</main>
</body>
</html>