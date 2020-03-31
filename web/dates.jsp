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
<%
    //jdbc:mysql://{host::localhost}?[:{port::3306}][/{database}?][\?<&,user={user},password={password},{:identifier}={:identifier}>]
    String driver = "com.mysql.cj.jdbc.Driver";
    //Connection conn = DriverManager.getConnection("mysql-luvr.thedanielhead.com");
    driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://mysql-luvr.thedanielhead.com:3306/mysql_luvr";
    try {
        Class.forName(driver);
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
    String dUser = "public_one";
    String dPass = "5C!qAmAw3qJyaRj";


    try {
        Connection cnx = DriverManager.getConnection(url, dUser, dPass);
%>
<jsp:include page="/Chunks/dates_main.jsp"></jsp:include>

<%
    } catch (SQLException e) {
        e.printStackTrace();
    }
    //Class.forName(driver).newInstance();


%>
</body>
</html>
