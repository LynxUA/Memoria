<%@ page import="com.burlakov.memoria.dao.DeskDAO" %>
<%@ page import="com.burlakov.memoria.dao.DeskDAOImpl" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../interface/includes.jsp"%>
    <title>Users of every desk | Memoria</title>
</head>
<body>
<%@include file="../interface/header.jsp"%>
<%
%>
<table class="table table-condensed">
    <tr>
        <td style="font-weight: bold">Name</td>
        <td style="font-weight: bold">Number of users</td>
    </tr>
    <%
        DeskDAO deskDAO = new DeskDAOImpl();
        List<Object[]> deskStatistics = deskDAO.findNumberOfUsersForEveryDesk();
        for(Object[] desk:deskStatistics){
    %>
    <tr>
        <td><%=desk[0]%></td>
        <td><%=desk[1]%></td>
    </tr>
    <%}%>
</table>
<%@include file="../interface/footer.jsp"%>
</body>
</html>