<%@ page import="com.burlakov.memoria.model.PrivateMessagesEntity" %>
<%@ page import="com.burlakov.memoria.dao.PrivateMessagesDAO" %>
<%@ page import="com.burlakov.memoria.dao.PrivateMessagesDAOImpl" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../interface/includes.jsp"%>
    <title>Messages | Memoria</title>
</head>
<body>
<%@include file="../interface/header.jsp"%>
<%
    PrivateMessagesDAO privateMessagesDAO = new PrivateMessagesDAOImpl();
    if(request.getParameter("type").equals("in")){
        List<PrivateMessagesEntity> messages = privateMessagesDAO.findAllInMessages((String)request.getSession().getAttribute("email"));
        for(PrivateMessagesEntity message:messages){
%>
        <h3><%=message.getSender()%></h3>
        <p><%=message.getText()%></p>
<%
        }
    }else if(request.getParameter("type").equals("out")){
        List<PrivateMessagesEntity> messages = privateMessagesDAO.findAllOutMessages((String) request.getSession().getAttribute("email"));
            for(PrivateMessagesEntity message:messages){
%>
<h3><%=message.getReciever()%></h3>
<p><%=message.getText()%></p>

<%
        }
    }
%>
<%@include file="../interface/footer.jsp"%>
</body>
</html>