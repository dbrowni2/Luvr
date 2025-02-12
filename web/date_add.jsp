<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Add Date</title>
</head>
<body>
<jsp:include page="/Chunks/Nav.jsp"></jsp:include>
<div class="row d-flex justify-content-center text-center">
    <div class="col-sm-4 col-sm-offset-4 d-flex justify-content-center text-center">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h1><c:out value="${name}"/></h1>
            </div>
            <div class="panel-body">
                <div class="form-group">
                    <form method="post" action="<%= request.getContextPath()%>/add_date">
                        <div class="col-xs-4 col-xs-offset-4">
                            <label for="datevis">When did you go there?</label><br>
                            <input type="date" name="datevis" id="datevis" required class="form-control"><br>
                            <label for="rate">How would you rate it? (1 to 5)</label>
                            <input type="number" name="rate" id="rate" max="5" min="1" required
                                   class="form-control"><br>
                            <input type="hidden" name="date" value="${id}">
                            <input type="submit" value="Add date to history" class="btn btn-default">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>