<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../interface/includes.jsp"%>
    <title>Main | Memoria</title>
</head>
<body>
<%@include file="../interface/header.jsp"%>
<%
    int roleM = (Integer)(request.getSession().getAttribute("role"));
    switch (roleM){
        case Roles.GUEST:
%>

<%
        break;
        case Roles.USER:
%>
    <div id="scrolly">

    </div>
<%
        case Roles.ADMIN:
%>

<%
        break;
    }
%>
<%@include file="../interface/footer.jsp"%>
</body>
</html>