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
    <title>Sign Up</title>
</head>
<body>
<jsp:include page="/Chunks/Nav.jsp"></jsp:include>

<div id="signup">
    <form method="POST" action="register">
        <label for="email">Email:</label>
        <input type="email" id="email" name="email"><br>
        <label for="confirme">Confirm Email:</label>
        <input type="email" id="confirme" name="confirme"><br>
        <label for="name">Full Name:</label>
        <input type="text" id="name" name="name"><br>
        <label for="pass">Password:</label>
        <input type="password" id="pass" name="pass"><br>
        <label for="cpass">Confirm Password:</label>
        <input type="password" id="cpass" name="cpass"><br>
        <input type="submit"><br>

    </form>

</div>
</body>
</html>
