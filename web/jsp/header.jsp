<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<script>
    $(function () {
        $.ajax({
            url: '${pageContext.request.contextPath}/category',
            method: 'post',
            dataType: "JSON",
            data: {'method': 'allCategory'},
            success: function (data) {
                $(data).each(function (index, value) {
                    $('#nav').append("<li><a href='${pageContext.request.contextPath}/product?method=findByCategory&page=1&id=" + value.cid + "'>" + value.cname + "</a></li>");
                });
            }
        })
    })
</script>
<!--
时间：2015-12-30
描述：菜单栏
-->
<div class="container-fluid">
    <div class="col-md-4">
        <a href="${pageContext.request.contextPath}/index.jsp"><img
                src="${pageContext.request.contextPath}/img/logo2.png"/></a>
    </div>
    <div class="col-md-5">
        <img src="${pageContext.request.contextPath}/img/header.png"/>
    </div>
    <div class="col-md-3" style="padding-top:20px">
        <c:if test="${not empty user}">
            <ol class="list-inline">
                <li><a href="${pageContext.request.contextPath}/user?method=loginView">欢迎你:${user.username}</a></li>
                <li><a href="${pageContext.request.contextPath}/cart?method=index">购物车</a></li>
                <li><a href="${pageContext.request.contextPath}/order?method=index&page=1">我的订单</a></li>
                <li><a href="${pageContext.request.contextPath}/user?method=logout">退出</a></li>
            </ol>
        </c:if>
        <c:if test="${empty user}">
            <ol class="list-inline">
                <li><a href="${pageContext.request.contextPath}/user?method=loginView">登录</a></li>
                <li><a href="${pageContext.request.contextPath}/user?method=registerView">注册</a></li>
                <li><a href="cart.htm">购物车</a></li>
            </ol>
        </c:if>
    </div>
</div>
<!--
时间：2015-12-30
描述：导航条
-->
<div class="container-fluid">
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">首页</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav" id="nav">
                </ul>
                <form class="navbar-form navbar-right" role="search">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Search">
                    </div>
                    <button type="submit" class="btn btn-default">Submit</button>
                </form>

            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>
</div>

