<%@ page import="com.burlakov.memoria.model.MemoriaUserEntity" %>
<%@ page import="com.burlakov.memoria.dao.MemoriaUserDAO" %>
<%@ page import="com.burlakov.memoria.dao.MemoriaUserDAOImpl" %>
<%@ page import="com.burlakov.memoria.dao.FriendsDAOImpl" %>
<%--
  Created by IntelliJ IDEA.
  User: denysburlakov
  Date: 22.03.15
  Time: 21:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String uemail = request.getParameter("email");
    MemoriaUserDAO userDAO = new MemoriaUserDAOImpl();
    MemoriaUserEntity user = userDAO.findUser(uemail);
%>
<html>
<head>
    <%@include file="../interface/includes.jsp"%>
    <title>Denied | Memoria</title>
    <style>
        .user-row {
            margin-bottom: 14px;
        }

        .user-row:last-child {
            margin-bottom: 0;
        }

        .dropdown-user {
            margin: 13px 0;
            padding: 5px;
            height: 100%;
        }

        .dropdown-user:hover {
            cursor: pointer;
        }

        .table-user-information > tbody > tr {
            border-top: 1px solid rgb(221, 221, 221);
        }

        .table-user-information > tbody > tr:first-child {
            border-top: 0;
        }


        .table-user-information > tbody > tr > td {
            border-top: 0;
        }
        .toppad
        {margin-top:20px;
        }
    </style>
</head>
<body>
<%@include file="../interface/header.jsp"%>
<div class="container">
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad" >


            <div class="panel panel-info">
                <div class="panel-heading">
                    <h3 class="panel-title"><%=user.getEmail()%></h3>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-md-3 col-lg-3 " align="center"> <img alt="User Pic" src="https://lh5.googleusercontent.com/-b0-k99FZlyE/AAAAAAAAAAI/AAAAAAAAAAA/eu7opA4byxI/photo.jpg?sz=100" class="img-circle"> </div>
                        <div class=" col-md-9 col-lg-9 ">
                            <table class="table table-user-information">
                                <tbody>
                                <tr>
                                    <td>Name:</td>
                                    <td><%=user.getName()%></td>
                                </tr>
                                <tr>
                                    <td>Status:</td>
                                    <td>
                                        <%if(user.getIdRole().equals(BigDecimal.valueOf(1l))){%>
                                            User
                                        <%}else if(user.getIdRole().equals(BigDecimal.valueOf(2l))){%>
                                            Admin
                                        <%}%>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Point</td>
                                    <td><%=user.getPoits()%></td>
                                </tr>

                                <tr>
                                    <td>Is online</td>
                                    <td><%if(user.getIsOnline().equals("Y")){%>
                                        User is online
                                            <%}else{%>User is offline<%}%></td>
                                </tr>

                                </tbody>
                            </table>
                            <%if((new FriendsDAOImpl()).isFriends((String) request.getSession().getAttribute("email"), user.getEmail())){ %>
                            <a href="remove_friend?email=<%=user.getEmail()%>" class="btn btn-primary">Delete from friends</a>
                            <%}else{%>
                            <a href="add_friend?email=<%=user.getEmail()%>" class="btn btn-primary">Add to friends</a>
                            <%}%>
                            <a href="send_message?email=<%=user.getEmail()%>" class="btn btn-primary">Send message</a>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
<%@include file="../interface/footer.jsp"%>
</body>
</html>
<script>
    $(document).ready(function() {
        var panels = $('.user-infos');
        var panelsButton = $('.dropdown-user');
        panels.hide();

        //Click dropdown
        panelsButton.click(function() {
            //get data-for attribute
            var dataFor = $(this).attr('data-for');
            var idFor = $(dataFor);

            //current button
            var currentButton = $(this);
            idFor.slideToggle(400, function() {
                //Completed slidetoggle
                if(idFor.is(':visible'))
                {
                    currentButton.html('<i class="glyphicon glyphicon-chevron-up text-muted"></i>');
                }
                else
                {
                    currentButton.html('<i class="glyphicon glyphicon-chevron-down text-muted"></i>');
                }
            })
        });


        $('[data-toggle="tooltip"]').tooltip();

        $('button').click(function(e) {
            e.preventDefault();
            alert("This is a demo.\n :-)");
        });
    });
</script>