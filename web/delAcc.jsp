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
        <div class="panel panel-default">
            <div class="panel-heading">
                <h2 class="panel-title">Delete Luvr Account</h2>
            </div>
            <div class="panel-body">
                <div class="col-sm-12">
                    <h2>Are you sure you want to delete your account?</h2>
                    <div class="btn-group" role="group">
                        <a role="button" class="btn btn-danger"
                           href="<c:url value="/deleteAcc">
                            <c:param name="ID" value="${user.ID}"/>
                            </c:url>">Delete Account</a>
                        <a role="button" class="btn btn-default"
                           href="<%=request.getContextPath()%>/Home?action=home">Keep Account</a>
                    </div>

                </div>
            </div>
        </div>
    </div>
</main>
</body>