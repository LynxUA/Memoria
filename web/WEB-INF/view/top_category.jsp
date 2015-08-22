<%@ page import="com.burlakov.memoria.dao.DeskDAO" %>
<%@ page import="com.burlakov.memoria.dao.DeskDAOImpl" %>
<%@ page import="java.util.List" %>
<%@ page import="com.burlakov.memoria.dao.MemoriaUserDAO" %>
<%@ page import="com.burlakov.memoria.dao.MemoriaUserDAOImpl" %>
<%@ page import="com.burlakov.memoria.model.MemoriaUserEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../interface/includes.jsp"%>
    <title>Top category | Memoria</title>
</head>
<body>
<%@include file="../interface/header.jsp"%>
<%
%>
<table class="table table-condensed">
    <tr>
        <td style="font-weight: bold">Category name</td>
    </tr>
    <%
        BigDecimal deskId = BigDecimal.valueOf(Integer.valueOf(request.getParameter("deskId")));
        MemoriaUserDAO memoriaUserDAO = new MemoriaUserDAOImpl();
        List<String> memoriaUserEntities = memoriaUserDAO.findTopCategoryOn(deskId);
        for(String memoriaUserEntity:memoriaUserEntities){
    %>
    <tr>
        <td><%=memoriaUserEntity%></td>
    </tr>
    <%}%>
</table>
<%@include file="../interface/footer.jsp"%>
</body>
</html>