<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../interface/includes.jsp"%>
    <title>Add label | Memoria</title>
</head>
<body>
<%@include file="../interface/header.jsp"%>
<form action="add_label" method="post">
    <input type="text" name="name">
    <input type="hidden" name="categoryId" value="<%=request.getParameter("categoryId")%>">
    <button class="btn btn-default" type="submit" name="submit">Create</button>
</form>
<%@include file="../interface/footer.jsp"%>
</body>
</html>