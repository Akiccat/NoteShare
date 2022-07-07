<%--
  Created by IntelliJ IDEA.
  User: Aki
  Date: 2022/5/19
  Time: 16:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
    <title>NoteShare</title>
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"
            integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
          integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">
</head>
<style>
    #div1, #div2, #div3 {
        border-radius: 30px;
        width: 300px;
        height: 400px;
        margin-left: 95px;
        margin-right: 90px;
        display: inline-block;
        box-shadow: 3px 3px 10px #3c3c3c;
    }

    #logo {
        margin-top: -20px;
        margin-bottom: 40px;
        font-size: 80px;
        margin-left: 50px;
    }
</style>

<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <div class="page-header">
                <h1>
                    &nbsp;&nbsp; 您还未登录 &nbsp;&nbsp;&nbsp;<small>登陆后将跳转至科目选择界面</small>
                </h1>
            </div>
            <div style="background-color: #ced4da;border-radius: 20px" class="hero-unit">
                <h1 id="logo">
                    <br/>
                    <strong>NoteShare</strong>
                    <br/>
                </h1>
                <h3 style="font-size: large">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<em>这是一个笔记分享与查看网站,你可以查看并上传自己的笔记,同时享受来自别人上传的笔记</em>
                </h3>
                <br/>
                <br/>
                <p>
                    <%@include file="common/loginFormModal.jsp" %>
                    <%@include file="common/regFormModal.jsp" %>
                    <c:if test="${sessionScope['_LOGIN_USER_']==null}">
                        <a style="margin-left: 50px" href="#loginFormModal" class="btn btn-primary btn-large" data-toggle="modal">登录</a>&nbsp;&nbsp;&nbsp;
                        <a href="#regFormModal" class="btn btn-primary btn-large" data-toggle="modal">注册</a>
                    </c:if>

                    <%--<c:if test="${sessionScope['_LOGIN_USER_']!=null}">
                        <a class="btn btn-primary btn-large" href='${pageContext.request.contextPath}/usercenter/index.action'>个人中心</a>
                        <a class="btn btn-primary btn-large" href="#" onclick="logout('${pageContext.request.contextPath}')">退出登录</a>
                    </c:if>--%>
                </p>
                <br/>
                <br/>
            </div>

            <div style="background-color: #21a675;border-radius: 20px">
                <br/>
                <div id="div3" class="thumbnail">
                    <br/>
                    <img alt="300x200" src="${pageContext.request.contextPath}/images/Theam/上传.png"/>
                    <br/>
                    <div class="caption">
                        <h3 style="text-align: center">
                            <strong>上传笔记</strong>
                        </h3>
                        <br/>
                        <p>
                            您可以在不同的学科中选择上传您的笔记文件,将它分享给所有人,感谢您的付出!
                        </p>
                    </div>
                </div>
                <div id="div2" class="thumbnail">
                    <br/>
                    <img alt="200x200" src="${pageContext.request.contextPath}/images/Theam/笔记.png"/>
                    <br/>
                    <div class="caption">
                        <h3 style="text-align: center">
                            <strong>浏览不同科目的笔记</strong>

                        </h3>
                        <br/>
                        <p>
                            我们暂时仅提供了三个科目的笔记,如果您有更多的需求,请联系我们!
                        </p>
                    </div>
                </div>
                <div id="div1" class="thumbnail">
                    <br/>
                    <img alt="200x200" src="${pageContext.request.contextPath}/images/Theam/download.png"/>
                    <br/>
                    <div class="caption">
                        <h3 style="text-align: center">
                            <strong>下载你需要的笔记</strong>
                        </h3>
                        <br/>
                        <p>
                            将您需要的笔记下载至您的电脑或手机,无需任何费用,享受您获得的笔记!
                        </p>
                    </div>
                </div>
            </div>
            <br/><br/>

            <blockquote>
                <p>
                    NoteShare是一个简单的课程项目
                </p> <small>关键词 项目</small>
            </blockquote>
        </div>
    </div>
</div>
</body>
</html>
