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

<div id="signup" class="col-sm-4" style="padding-top: 5pt; padding-left: 20pt">
    <form method="POST" action="register">
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" class="form-control"><br>
            <label for="confirme">Confirm Email:</label>
            <input type="email" id="confirme" name="confirme" class="form-control"><br>
            <label for="name">Full Name:</label>
            <input type="text" id="name" name="name" class="form-control"><br>
            <label for="pass">Password:</label>
            <input type="password" id="pass" name="pass" class="form-control"><br>
            <label for="cpass">Confirm Password:</label>
            <input type="password" id="cpass" name="cpass" class="form-control"><br>
            <input type="submit" class="btn btn-default"><br>
        </div>
    </form>

</div>
</body>
</html>
