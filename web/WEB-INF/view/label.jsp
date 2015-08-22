<%@ page import="com.burlakov.memoria.dao.LableDAO" %>
<%@ page import="com.burlakov.memoria.dao.LableDAOImpl" %>
<%@ page import="com.burlakov.memoria.model.LableEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.burlakov.memoria.model.CommentaryEntity" %>
<%@ page import="com.burlakov.memoria.dao.CommentaryDAO" %>
<%@ page import="com.burlakov.memoria.dao.CommentaryDAOImpl" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../interface/includes.jsp"%>
    <title>Label | Memoria</title>
    <script>
        $(document).ready(function() {
            $(document).on("click", ".mem-edit-comment", function(){
                $(this).parent().
                        html('<input class="mem-new-commentary-content" type="field" value="'
                        +$(this).parent().parent().find('div.mem-comment-text').text()
                        +'"/><button class="mem-save-comment" value="'
                        +$(this).val()
                        +'">Save</button>');
            });
            $(document).on("click", ".mem-delete-comment", function(){
                var commentaryId = $(this).val();
                $.post('delete_commentary',{commentaryId:commentaryId},function(responseText) {

                });
                $(this).parent().parent().velocity({ opacity: 0 }, 2000).queue(function() {
                    $(this).remove();
                });
            });
            $(document).on("click", ".mem-save-comment", function(){
                var commentaryId = $(this).val();
                var newText = $(this).parent().find('input.mem-new-commentary-content').val();
                $.post('edit_commentary',{commentaryId:commentaryId, newText:newText},function(responseText) {
                });
                $(this).parent().html('<div class="mem-comment-text panel-footer">'+newText+'</div>'
                +'<button class="mem-edit-comment" value="'+commentaryId+'">Edit comment</button>'
                +'<button class="mem-delete-comment" value="'+commentaryId+'">Delete comment</button>');
            });

//            $(document).on("click", ".mem-add-comment", function(){
//                $(this).html('<input class="mem-commentary-text" type="field" placeholder="Label name"/>' +
//                '<button class="mem-save-new-label" value="'
//                +$(this).val()
//                +'">Save label</button>');
//            });
//            $(document).on("click", ".mem-save-new-label", function() {
//                var categoryId = $(this).val();
//                var name = $(this).parent().find('input.mem-label-name').val();
//                var labelId;
//                var button = $(this);
//                $.post('add_label',{categoryId:categoryId, name:name},function(responseText) {
//                    labelId = responseText;
//                    button.parent().parent().find('div.mem-labels').append('<div class="mem-label list-group-item"> <a class="mem-label-name" href="label?labelId='
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
<%@include file="../interface/header.jsp"%>
<%
    BigDecimal roleM = (BigDecimal) (request.getSession().getAttribute("role"));
    if (roleM == null)
        roleM = Roles.GUEST;
    BigDecimal labelId = BigDecimal.valueOf(Long.valueOf(request.getParameter("labelId")));
    LableDAO deskDAO = new LableDAOImpl();
    LableEntity lableEntity = deskDAO.findLable(labelId);
    CommentaryDAO commentaryDAO = new CommentaryDAOImpl();
    List<CommentaryEntity> commentaries = commentaryDAO.findCommentariesByLabel(labelId);
%>
<%
    if (roleM.equals(Roles.GUEST)) {

    } else if (roleM.equals(Roles.USER) || roleM.equals(Roles.ADMIN)) {
%>
<%

    for(CommentaryEntity commentaryEntity:commentaries){
        String writerEmail = commentaryEntity.getEmail();
        BigDecimal commentaryId = commentaryEntity.getIdCommentary();
%>
<div class="mem-commenary panel panel-primary">
    <div class="panel-body">
    <%=commentaryEntity.getEmail()%>
    </div>
    <div class="mem-comment-main">
        <div class="mem-comment-text panel-footer"><%=commentaryEntity.getValue()%></div>
        <%if(request.getSession().getAttribute("email").equals(writerEmail)){%>
        <button class="mem-edit-comment" value="<%=commentaryId%>">Edit comment</button>
        <button class="mem-delete-comment" value="<%=commentaryId%>">Delete comment</button>
        <%}%>
    </div>
</div>
<%
    }
%>
<form action="label" method="post">
    <input type="text" name="value"/>
    <input type="hidden" name="labelId" value="<%=labelId%>"/>
    <button type="submit" name="submit">Add commentary</button>
</form>
<%
    }
%>
<%@include file="../interface/footer.jsp"%>
</body>
</html>