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
    <img style="width: 50%; height: 50%" src="http://www.smart-shop.ua/images/bmw-announces-i-collection-accessories-for-the-new-era-photo-gallery_4.jpg"/>
<%
} else{
%>

<div id="mem-workspace">
    <div class="list-group">
        <%
            for (DeskEntity deskEntity : desks) {
        %>
        <div class="list-group-item">
            <a href="desk?idDesk=<%=deskEntity.getIdDesk()%>"><%=deskEntity.getName()%></a><br>
            <a href="delete_desk?idDesk=<%=deskEntity.getIdDesk()%>">Delete</a>
        </div>
        <br>
        <%
            }
        %>
        <a href="add_desk" class="list-group-item">Add new desk</a>
    </div>
</div>
<%
    }
%>
<%@include file="../interface/footer.jsp" %>
</body>
</html>