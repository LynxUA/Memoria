<%@ page import="com.burlakov.memoria.dao.FriendsDAO" %>
<%@ page import="com.burlakov.memoria.dao.FriendsDAOImpl" %>
<%@ page import="com.burlakov.memoria.model.FriendsEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.burlakov.memoria.dao.MemoriaUserDAO" %>
<%@ page import="com.burlakov.memoria.dao.MemoriaUserDAOImpl" %>
<%@ page import="com.burlakov.memoria.model.MemoriaUserEntity" %>
<%--
  Created by IntelliJ IDEA.
  User: denysburlakov
  Date: 25.03.15
  Time: 22:06
  To change this template use File | Settings | File Templates.
--%>
<%
    FriendsDAO friendsDAO = new FriendsDAOImpl();
    List<FriendsEntity> friends = friendsDAO.findFriendsByEmail((String) request.getSession().getAttribute("email"));
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../interface/includes.jsp"%>
    <title>Friends | Memoria</title>
</head>
<body>
<%@include file="../interface/header.jsp"%>
<table class="table table-condensed">
    <tr>
        <td style="font-weight: bold">Email</td>
        <td style="font-weight: bold">Name</td>
        <td style="font-weight: bold">Points</td>
        <td style="font-weight: bold"></td>
    </tr>
    <%for(FriendsEntity friendsEntity:friends){
        MemoriaUserDAO dao = new MemoriaUserDAOImpl();
        MemoriaUserEntity user =  dao.findUser(friendsEntity.getEmail2());
    %>
    <tr>
        <td><%=user.getEmail()%></td>
        <td><%=user.getName()%></td>
        <td><%=user.getPoits()%></td>
        <td><a href="user_info?email=<%=user.getEmail()%>">Get detailed info</a></td>
    </tr>
    <%}%>
</table>
<%@include file="../interface/footer.jsp"%>
</body>
</html>