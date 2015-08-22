<%@ page import="java.util.List" %>
<%@ page import="com.burlakov.memoria.dao.*" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="com.burlakov.memoria.model.*" %>
<%
    BigDecimal roleM = (BigDecimal) (request.getSession().getAttribute("role"));
    if (roleM == null)
        roleM = Roles.GUEST;
    BigDecimal idDesk = BigDecimal.valueOf(Long.valueOf(Math.round(Double.valueOf(request.getParameter("idDesk")))));
    DeskDAO deskDAO = new DeskDAOImpl();
    DeskEntity deskEntity = deskDAO.findDesk(idDesk);
    CategoryDAO categoryDAO = new CategoryDAOImpl();
    List<CategoryEntity> categories = categoryDAO.findCategotiesByDesk(idDesk);

    MemoriaUserDAO memoriaUserDAO = new MemoriaUserDAOImpl();
    List<MemoriaUserEntity> memoriaUserEntities = memoriaUserDAO.findUsersNotFromBoard(idDesk);
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../interface/includes.jsp" %>
    <title><%=deskEntity.getName()%> desk | Memoria</title>
    <script>
        $(document).ready(function() {
            $(document).on("click", ".mem-rename-label", function(){
                $(this).parent().parent().
                        html('<input class="mem-new-label-name" type="field" placeholder="Label name" value="'
                        +$(this).parent().parent().find('a.mem-label-name').text()
                        +'"/><button class="mem-save-label" value="'
                        +$(this).val()
                        +'">Save</button>');
            });
            $(document).on("click", ".mem-delete-label", function(){
                var labelId = $(this).val();
                $.post('delete_label',{labelId:labelId},function(responseText) {

                });
                $(this).parent().velocity({ opacity: 0 }, 2000).queue(function() {
                            $(this).remove();
                        });
            });
            $(document).on("click", ".mem-save-label", function(){
                var labelId = $(this).val();
                var newName = $(this).parent().find('input.mem-new-label-name').val();
                $.post('edit_label',{labelId:labelId, newName:newName},function(responseText) {
                });
                $(this).parent().html('<a class="mem-label-name" href="label?labelId='
                +labelId
                +'">'
                +newName
                +'</a><div class="mem-edit-label"> <button class="mem-rename-label" value="'
                +labelId
                +'">Edit</button> </div> <button class="mem-delete-label" value="'
                +labelId
                +'">Delete</button>');
            });
            $(document).on("click", ".mem-delete-category", function(){
                var categoryId = $(this).val();
                $.post('delete_category',{categoryId:categoryId},function(responseText) {

                });
                $(this).parent().remove();
            });
            $(document).on("click", ".mem-add-label", function(){
                $(this).parent().html('<input class="mem-label-name" type="field" placeholder="Label name"/>' +
                '<button class="mem-save-new-label" value="'
                +$(this).val()
                +'">Save label</button>');
            });
            $(document).on("click", ".mem-save-new-label", function() {
                var categoryId = $(this).val();
                var name = $(this).parent().find('input.mem-label-name').val();
                var labelId;
                var button = $(this);
                $.post('add_label',{categoryId:categoryId, name:name},function(responseText) {
                    labelId = responseText;
                    button.parent().parent().find('div.mem-labels').append('<div class="mem-label list-group-item"> <a class="mem-label-name" href="label?labelId='
                    +labelId
                    +'">'
                    +name
                    +'</a><div class="mem-edit-label"><button class="mem-rename-label" value="'
                    +labelId
                    +'">Edit</button></div> <button class="mem-delete-label" value="'
                    +labelId
                    +'">Delete</button></div>');
                    button.parent().html('<button class="mem-add-label" value="'+categoryId+'">Add new label</button>');
                });
            });
//
//            $(document).on("click", ".mem-add-category", function(){
//                $(this).parent().html('<input class="mem-category-name" type="field" placeholder="Category name"/>' +
//                '<button class="mem-save-new-category" value="'
//                +$(this).val()
//                +'">Save category</button>');
//            });
//            $(document).on("click", ".mem-save-new-category", function() {
//                var idDesk = $(this).val();
//                var name = $(this).parent().find('input.mem-category-name').val();
//                var categoryId;
//                var button = $(this);
//                $.post('add_category',{idDesk:idDesk, name:name},function(responseText) {
//                    categoryId = responseText;
//                    button.parent().parent().find('div.mem-category').append('<div class="mem-label list-group-item"> <a class="mem-label-name" href="label?labelId='
//                    +labelId
//                    +'">'
//                    +name
//                    +'</a><div class="mem-edit-label"><button class="mem-rename-label" value="'
//                    +labelId
//                    +'">Edit</button></div> <button class="mem-delete-label" value="'
//                    +labelId
//                    +'">Delete</button></div>');
//                    button.parent().html('<button class="mem-add-label" value="'+categoryId+'">Add new label</button>');
//                });
//            });
        });
    </script>
</head>
<body>
<%@include file="../interface/header.jsp" %>

<%
    if (roleM.equals(Roles.GUEST)) {

    } else if (roleM.equals(Roles.USER) || roleM.equals(Roles.ADMIN)) {
%>
<div id="mem-desk">
    <%
        DeskUserDAO deskUserDAO = new DeskUserDAOImpl();
        List<DeskUsersEntity> deskUsers = deskUserDAO.findDeskUsersByDeskId(idDesk);
    %>
    <div id="mem-members" class="mem-category list-group">
    <%
    for(DeskUsersEntity deskUser:deskUsers){
    %>
        <a href="user_info?email=<%=deskUser.getEmail()%>" class="list-group-item"><%=(new MemoriaUserDAOImpl()).
                getNameByEmail(deskUser.getEmail())%></a><br>
    <%
        }
    %>
        <form action="add_to_desk" method="post">
            <input type="hidden" name="idDesk" value="<%=idDesk%>"/>
            <select style="width: 100px" class="form-control" name = "email">
                <%
                    for(MemoriaUserEntity userEntity:memoriaUserEntities){
                %>
                <option value="<%=userEntity.getEmail()%>"><%=userEntity.getName()%></option>
                <%
                    }
                %>
            </select>
            <button style="margin-top: 10px" class="btn btn-default" type="submit">Add user to the desk</button>
        </form>
        </div>
    <%
        for (CategoryEntity categoryEntity : categories) {
    %>
    <div class="mem-category list-group">
        <p><%=categoryEntity.getName()%></p>
        <div class="mem-labels">
        <%
            LableDAO labelDAO = new LableDAOImpl();
            List<LableEntity> lables = labelDAO.findLablesByCategory(categoryEntity.getIdCatagory());
            for (LableEntity label : lables) {
        %>
        <div class="mem-label list-group-item">
            <a class="mem-label-name" href="label?labelId=<%=label.getIdLabel()%>"><%=label.getName()%></a>
            <div class="mem-edit-label">
            <button class="mem-rename-label" value="<%=label.getIdLabel()%>">Edit</button>
            </div>
            <button class="mem-delete-label" value="<%=label.getIdLabel()%>">Delete</button>
        </div>
        <%
            }
        %>
        </div><br/>
        <button class="mem-delete-category" value="<%=categoryEntity.getIdCatagory()%>">Delete category</button>
        <div class="mem-input-label">
        <button class="mem-add-label" value="<%=categoryEntity.getIdCatagory()%>">Add new label</button>
        </div>
    </div>
    <%
        }
    %>
    <div class="mem-category list-group">
        <a href="add_category?idDesk=<%=deskEntity.getIdDesk()%>" class="mem-add-category">Add new category</a>
    </div>
    <%
        }
    %>
</div>
<%@include file="../interface/footer.jsp" %>
</body>
</html>