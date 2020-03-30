<%--
  Created by IntelliJ IDEA.
  User: brown
  Date: 1/30/2020
  Time: 3:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%--<link href="/Luvr.css" rel="stylesheet" type="text/css">--%>
<jsp:include page="/Chunks/links.jsp"></jsp:include>


<html>
<head>
    <title>Login</title>
</head>
<body>
<jsp:include page="/Chunks/Nav.jsp"></jsp:include>

<div id="signin">
    <form method="POST" action="login">
        <label for="Lemail">Email:</label><br>
        <input type="email" name="email" id="Lemail"><br>
        <label for="Lpass">Password:</label><br>
        <input type="password" name="pass" id="Lpass"><br>
        <input type="submit" value="login"><br>
    </form>

</div>
</body>
</html>
