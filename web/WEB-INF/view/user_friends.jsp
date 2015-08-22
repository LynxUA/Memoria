<%@ page import="com.burlakov.memoria.dao.DeskDAO" %>
<%@ page import="com.burlakov.memoria.dao.DeskDAOImpl" %>
<%@ page import="java.util.List" %>
<%@ page import="com.burlakov.memoria.dao.MemoriaUserDAO" %>
<%@ page import="com.burlakov.memoria.dao.MemoriaUserDAOImpl" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../interface/includes.jsp"%>
    <title>User friends | Memoria</title>
</head>
<body>
<%@include file="../interface/header.jsp"%>
<%
%>
<table class="table table-condensed">
    <tr>
        <td style="font-weight: bold">User</td>
        <td style="font-weight: bold">Number of friends</td>
    </tr>
    <%
        MemoriaUserDAO userDAO = new MemoriaUserDAOImpl();
        List<Object[]> userStatistics = userDAO.findNumberOfFriendsForEveryUser();
        for(Object[] user:userStatistics){
    %>
    <tr>
        <td><%=user[0]%></td>
        <td><%=user[1]%></td>
    </tr>
    <%}%>
</table>
<%@include file="../interface/footer.jsp"%>
</body>
</html>