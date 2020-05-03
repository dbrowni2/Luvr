<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Delete Account</title>
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
            <h1 class="display-4">Luvr</h1>
        <h2>Are you sure you want to delete your account?</h2>
         <a href="<c:url value="/deleteAcc">
             <c:param name="ID" value="${user.ID}"/>
         </c:url>">Yes, Delete my Account</a>
            <a href="/Home?action=home">No, Keep my Account</a>
        </div>
    </div>
</main>
</body>