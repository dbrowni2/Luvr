<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Trini
  Date: 3/23/2020
  Time: 9:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page
        import="java.io.*,java.util.*,java.sql.Connection, java.sql.DriverManager, java.sql.SQLException,javax.sql.*" %>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dates</title>
</head>
<body>
<jsp:include page="/Chunks/Nav.jsp"></jsp:include>

<jsp:include page="/Chunks/dates_main.jsp"></jsp:include>



</body>
</html>
