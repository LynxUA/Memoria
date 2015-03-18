<%@ page import="com.burlakov.memoria.model.DeskEntity" %>
<%@ page import="com.burlakov.memoria.dao.DeskDAOImpl" %>
<%@ page import="com.burlakov.memoria.dao.DeskDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../interface/includes.jsp" %>
    <title>Main | Memoria</title>
</head>
<body>
<%@include file="../interface/header.jsp" %>
<%
    BigDecimal roleM = (BigDecimal) (request.getSession().getAttribute("role"));
    List<DeskEntity> desks = null;
    if (roleM == null)
        roleM = Roles.GUEST;
    else {
        DeskDAO deskDAO = new DeskDAOImpl();
        desks = deskDAO.findDesksByUser((String) (request.getSession().getAttribute("email")));
    }
    if (roleM.equals(Roles.GUEST)) {
%>

<%
} else if (roleM.equals(Roles.USER)) {
%>

<div id="workspace">
    <%
        for (DeskEntity deskEntity : desks) {
    %>
    <a href="desk?idDesk=<%=deskEntity.getIdDesk()%>"><%=deskEntity.getName()%></a>
    <br>
    <%
        }
    %>
    <a href="add_desk">Add new desk</a>
</div>
<%
} else if (roleM.equals(Roles.ADMIN)) {
%>

<%
    }
%>
<%@include file="../interface/footer.jsp" %>
</body>
</html>