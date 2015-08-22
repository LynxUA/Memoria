<%@ page import="com.burlakov.memoria.dao.MemoriaUserDAO" %>
<%@ page import="com.burlakov.memoria.dao.DeskDAO" %>
<%@ page import="com.burlakov.memoria.dao.DeskDAOImpl" %>
<%@ page import="com.burlakov.memoria.model.DeskEntity" %>
<%@ page import="java.util.List" %><%
    DeskDAO deskDAO = new DeskDAOImpl();
    List<DeskEntity> deskEntities = deskDAO.allDesks();
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../interface/includes.jsp"%>
    <title>Admin panel | Memoria</title>
</head>
<body>
<%@include file="../interface/header.jsp"%>
<%
%>
<a href="desk_statistics">Desk statistics</a></br>
<a href="user_posts">Users and number of their posts</a></br>
<a href="user_friends">Users and number of their friends</a>
<p>Find top user on desk</p>
<form action="top_users" method="post">
    <select style="width: 100px" class="form-control" name = "deskId">
        <%
            for(DeskEntity deskEntity:deskEntities){
        %>
        <option value="<%=deskEntity.getIdDesk()%>"><%=deskEntity.getName()%></option>
        <%
            }
        %>
    </select>
    <button style="margin-top: 10px" class="btn btn-default" type="submit">Find top user</button>
</form>
<p>Find top category on desk</p>
<form action="top_category" method="post">
    <select style="width: 100px" class="form-control" name = "deskId">
        <%
            for(DeskEntity deskEntity:deskEntities){
        %>
        <option value="<%=deskEntity.getIdDesk()%>"><%=deskEntity.getName()%></option>
        <%
            }
        %>
    </select>
    <button style="margin-top: 10px" class="btn btn-default" type="submit">Find top category</button>
</form>
<%@include file="../interface/footer.jsp"%>
</body>
</html>