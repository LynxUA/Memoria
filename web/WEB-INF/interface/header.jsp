<%@ page import="com.burlakov.memoria.system.Roles" %>
<%@ page import="java.math.BigDecimal" %>
<%--
  Created by IntelliJ IDEA.
  User: denysburlakov
  Date: 23.11.14
  Time: 04:14
  To change this template use File | Settings | File Templates.
--%>
<%
    /** TODO:
     * /users (link to /profile)
     * /groups (link to /group)
     * /instances (link to /user_si)
     * /orders  (link to /user_so)
     * /tasks (link to /task)
     * */
    BigDecimal role;
    String email = (String) request.getSession().getAttribute("email");
    if(email !=null){
        role = (BigDecimal) (request.getSession().getAttribute("role"));
    }else{
        email = "Guest";
        role = Roles.GUEST;
    }
%>
<div id="wrap">
    <div id="header">
        <div class="bs-example">
            <nav id="myNavbar" class="navbar navbar-default" role="navigation">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="container">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a href="http://localhost:8080/MemoriaHibernate_war_exploded/" class="navbar-brand">
                            Memoria
                        </a>
                    </div>
                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav">
                            <%
                                if(role.equals(Roles.GUEST)) {
                            %>
                            <li><a href="login">Login</a></li>
                            <%
                            }else if(role.equals(Roles.USER)){
                            %>
                            <%--<li><a href="/MemoriaHibernate_war_exploded/">Home</a></li>--%>
                            <%--<li><a href="catalog">Services</a></li>--%>
                            <%--<li><a href="order">Make an order</a></li>--%>
                            <%
                            }else if(role.equals(Roles.ADMIN)){
                            %>
                            <%--<li><a href="/MemoriaHibernate_war_exploded/">Home</a></li>--%>
                            <%--<li><a href="panel">Admin panel</a></li>--%>
                            <%
                                }
                            %>

                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                            <li class="dropdown">
                                <a href="#" data-toggle="dropdown" class="dropdown-toggle"><%=email%><b class="caret"></b></a>
                                <ul class="dropdown-menu">
                                    <%
                                        if(role.equals(Roles.GUEST)) {
                                    %>
                                    <li><a href="login">Log in</a></li>
                                    <%--<li><a href="location">My location</a></li>--%>
                                    <%
                                        }else if(role.equals(Roles.USER)){
                                    %>
                                    <li><a href="friends">Friends</a></li>
                                    <%--<li><a href="user_si">My instances</a></li>--%>
                                    <%--<li><a href="user_so">My orders</a></li>--%>
                                    <%--<li><a href="profile">My profile</a></li>--%>
                                    <li><a href="messages?type=out">Output messages</a></li>
                                    <li><a href="messages?type=in">Input messages</a></li>
                                    <li class="divider"></li>
                                    <li><a href="exit">Exit</a></li>
                                    <%
                                        }else if(role.equals(Roles.ADMIN)){
                                    %>
                                    <li><a href="friends">Friends</a></li>
                                    <li><a href="panel">Admin panel</a></li>
                                    <%--<li><a href="reg_emp">Register employee</a></li>--%>
                                    <%--<li><a href="profile">My profile</a></li>--%>
                                    <%--<li class="divider"></li>--%>
                                    <li><a href="messages?type=out">Output messages</a></li>
                                    <li><a href="messages?type=in">Input messages</a></li>
                                    <li class="divider"></li>
                                    <li><a href="exit">Exit</a></li>
                                    <%
                                        }
                                    %>
                                </ul>
                            </li>
                        </ul>
                    </div><!-- /.navbar-collapse -->
                </div>
            </nav>
        </div>
    </div>