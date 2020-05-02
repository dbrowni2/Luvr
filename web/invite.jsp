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
        <div class="jumbotron" id="leader">
            <h2>Invite someone to <c:out value="${name}"/></h2>
           <form method="post" action="<%= request.getContextPath()%>/invite">
              <label for="from">From:</label>
               <input type="text" value="${user.uName}" readonly name="from" id="from" required><br>
               <label for="who">Name of Recipient:</label>
               <input type="text" id="who" name="who" required><br>
               <label for="to">To:</label>
               <input type="email" id="to" name="to" required><br>
               <label for="when">When:</label>
               <input type="date" id="when" name="when" required>
               <label for="cus">Custom Message:</label><br>
               <input type="textarea" name="cus" id="cus" style="height: 100px; width: 100px"><br>
               <input type="submit" valu="Invite to Date!">
                <input type="hidden" name="invDate" value="${id}">


           </form>
        </div>
    </div>
</main>
</body>
</html>