<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../interface/includes.jsp"%>
    <title>Add desk | Memoria</title>
</head>
<body>
<%@include file="../interface/header.jsp"%>
<form action="add_desk" method="post">
    <input type="text" name="name">
    <button class="btn btn-default" type="submit" name="submit">Create</button>
</form>
<%@include file="../interface/footer.jsp"%>
</body>
</html>