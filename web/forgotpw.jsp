<%--
  Created by IntelliJ IDEA.
  User: mdhac
  Date: 4/20/2020
  Time: 12:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%--<link href="/Luvr.css" rel="stylesheet" type="text/css">--%>
<jsp:include page="/Chunks/links.jsp"></jsp:include>


<html>
<head>
    <title>ResetPassword</title>
</head>
<body>
<jsp:include page="/Chunks/Nav.jsp"></jsp:include>

<div id="signup" class="col-sm-4 col-sm-offset-4" style="padding-top: 5pt; padding-left: 20pt; alignment: center">
    <h2 style="color:red;"><c:out value="${error}"/></h2>
    <h2 style="color:green;"><c:out value="${confirmation}"/></h2>
    <form action="<%= request.getContextPath()%>/forgotpw" method="post" >
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" class="form-control"><br>

            <label for="confirme">Confirm Email:</label>
            <input type="email" id="confirme" name="confirme" class="form-control"><br>
            <input type="submit" value="confirm" class="btn btn-default"><br>
        </div>
    </form>
</div>

</body>
</html>
