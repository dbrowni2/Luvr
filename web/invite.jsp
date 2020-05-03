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
    <title>Invite</title>
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
                <h2 class="panel-title">Invite someone to: <c:out value="${name}"/></h2>
            </div>
            <div class="panel-body">
                <div class="col-sm-12">
                    <form method="post" action="<%= request.getContextPath()%>/invite">
                        <div class="form-group">
                            <label for="from">From:</label>
                            <input type="text" value="${user.uName}" readonly name="from" id="from" required
                                   class="form-control"><br>
                            <label for="who">Name of Recipient:</label>
                            <input type="text" id="who" name="who" required class="form-control"><br>
                            <label for="to">Email of Recipient:</label>
                            <input type="email" id="to" name="to" required class="form-control"
                                   placeholder="example@email.com"><br>
                            <label for="when">When:</label>
                            <input type="date" id="when" name="when" required class="form-control"><br>
                            <label for="cus">Custom Message:</label><br>
                            <textarea name="cus" id="cus" class="form-control" size="lg"
                                      placeholder="Enter custom message here..."></textarea><br>
                            <input type="submit" value="Invite to Date!" class="btn btn-default" align="center">
                            <input type="hidden" name="invDate" value="${id}">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</main>
</body>
</html>