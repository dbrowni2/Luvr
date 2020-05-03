<%--
  Created by IntelliJ IDEA.
  User: brown
  Date: 1/30/2020
  Time: 3:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--<link href="/Luvr.css" rel="stylesheet" type="text/css">--%>
<jsp:include page="/Chunks/links.jsp"></jsp:include>


<html>
<head>
    <title>Login</title>
</head>
<body>
<jsp:include page="/Chunks/Nav.jsp"></jsp:include>

<div id="signin" class="col-sm-4 col-sm-offset-4" style="padding-top: 5pt; padding-left: 20pt">
    <h2 style="color:red;"><c:out value="${error}"/></h2>
    <form method="POST" action="<%= request.getContextPath()%>/login">
        <div class="form-group">
            <label for="Lemail">Email:</label><br>
            <input type="email" name="email" id="Lemail" class="form-control"><br>

            <label for="Lpass">Password:</label><br>
            <input type="password" name="pass" id="Lpass" class="form-control"><br>
            <input type="submit" value="login" class="btn btn-default"><br>
        </div>
    </form>

</div>
</body>
</html>
