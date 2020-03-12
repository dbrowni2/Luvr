<%--
  Created by IntelliJ IDEA.
  User: brown
  Date: 1/30/2020
  Time: 3:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<link href="/Luvr.css" rel="stylesheet" type="text/css">--%>
<jsp:include page="/Chunks/links.jsp"></jsp:include>


<html>
<head>
    <title>Login</title>
</head>
<body>
<jsp:include page="/Chunks/Nav.jsp"></jsp:include>

<div id="signin">
    <form method="POST">
        <label for="Lemail">Email:</label><br>
        <input type="email" name="email" id="Lemail"><br>
        <label for="Lpass">Password:</label><br>
        <input type="password" name="pass" id="Lpass"><br>
        <input type="submit"><br>
    </form>

</div>
</body>
</html>
