<%@ page import="com.burlakov.memoria.dao.DeskDAO" %>
<%@ page import="com.burlakov.memoria.dao.DeskDAOImpl" %>
<%@ page import="com.burlakov.memoria.model.DeskEntity" %>
<%@ page import="java.util.List" %>
<html>
<body>

<h1>${message}</h1>
<%
    DeskDAO deskDao = new DeskDAOImpl();
    List<DeskEntity> desks = deskDao.allDesks();
    response.getWriter().println(desks.size());
%>
</body>
</html>