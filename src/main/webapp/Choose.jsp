<%--
  Created by IntelliJ IDEA.
  User: Aki
  Date: 2022/5/19
  Time: 20:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>选择科目</title>
    <script src="js/jquery-1.11.3.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"
            integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
          integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">
<%--    <script>
        function showCenter(baseurl) {
            $.post(baseurl + "/usercenter/index.action", function (result) {
                if(result.success) {
                    $("#cartModal").modal('show');
                    $("#cartTitle").html("用户中心");
                    $("#cart_msg").html("欢迎来到个人中心，您可以修改个人信息");
                    $("#cartBody").html("<%@include file='usercenter/userCenterLeftNav.jsp %>");
                }
            },'json');
        }
    </script>--%>
</head>
<style>
    #div1, #div2, #div3 {
        background-color: #f05654;
        border-radius: 30px;
        width: 300px;
        height: 400px;
        margin-left: 95px;
        margin-right: 90px;
        display: inline-block;
        border-color: #f05654;
        box-shadow: 1px 1px 20px #3c3c3c;
    }

    h3 {
        font-size: 30px;
    }

    em {
        background-color: #ff8c31;
        border-radius: 30px;
        margin-left: 50px;
        font-size: 20px;
    }

    button {
        background-color: #fa8c35;
    }

    img {
        margin-top: 10px;
    }

    a {
        text-decoration: 0px;
    }
</style>
<body>
<br/>
<h2>
    <%@include file="common/loginFormModal.jsp" %>
    &nbsp;&nbsp;&nbsp;<strong>${sessionScope['_LOGIN_USER_'].userName }</strong>&nbsp;您好&nbsp;&nbsp;<small>欢迎使用NoteShare</small>
    <a style="float: right;color: black;margin-right: 20px;margin-top: 10px" href='${pageContext.request.contextPath}/chart.jsp' TARGET="_blank"
    <%--onclick="showCenter('${pageContext.request.contextPath}')"--%>><small>数据统计</small></a>
    <a style="float: right;color: black;margin-right: 20px;margin-top: 10px" href='${pageContext.request.contextPath}/usercenter/index.action' TARGET="_blank"
       <%--onclick="showCenter('${pageContext.request.contextPath}')"--%>><small>个人中心</small></a>
    <a style="float: right;color: black;margin-right: 20px;margin-top: 10px" href="#" onclick="logout('${pageContext.request.contextPath}')"><small>退出登录</small></a>
</h2>
<br/>
<div style="background-color: #ced4da;border-radius: 10px;margin-top: -25px" class="hero-unit">
    <h1 style="font-size: xxx-large">
        <br/>
        &nbsp;&nbsp;&nbsp;<strong>在这里选择感兴趣的科目</strong>
        <br/>&nbsp;&nbsp;&nbsp;<small>Choose Your Favourite SUBJECT</small>
        <br/>
    </h1>
    <h3 style="font-size: large">
        <em>NoteShare</em>
    </h3>
    <br/>
    <br/>
</div>


<div style="background-color: #3b2e7e;border-radius: 10px">
    <br/>
    <div id="div3" class="thumbnail">
        <br/>
        <img alt="300x200" src="images/Theam/正弦曲线_sinusoid.png"/>
        <br/>
        <div class="caption">
            <h3>
                <strong></strong>
            </h3>
            <br/>
            <div class="span12">
                <a style="text-decoration: 0px;color: black"
                   href="InputPictureController?subject=Math">
                    <button class="btn btn-block" style="border-radius: 40px" type="button"><h3><strong>Math</strong></h3></button>
                </a>
            </div>
        </div>
    </div>
    <div id="div2" class="thumbnail">
        <br/>
        <img width="200" height="200" src="images/Theam/调色盘_platte.png"/>
        <br/>
        <div class="caption">
            <h3>
                <strong></strong>
            </h3>
            <br/>
            <div class="span12">
                <a style="text-decoration: 0px;color: black;"
                   href="InputPictureController?subject=Paint">
                    <button class="btn btn-block" style="border-radius: 40px" type="button"><h3><strong>Paint</strong></h3></button>
                </a>
            </div>
        </div>
    </div>
    <div id="div1" class="thumbnail">
        <br/>
        <img alt="300x200" src="images/Theam/代码.png"/>
        <br/>
        <div class="caption">
            <h3>
                <strong></strong>
            </h3>
            <br/>
            <div class="span12">
                <a style="text-decoration: 0px;color: black"
                   href="InputPictureController?subject=Code">
                    <button class="btn btn-block" style="border-radius: 40px" type="button"><h3><strong>Code</strong></h3></button>
                </a>
            </div>
        </div>
    </div>
</div>

<div class="container-fluid" style="background-color: #e9f1f6;border-radius: 10px;margin-top: 5px">
    <div class="row-fluid">
        <div class="span12">
            <div class="alert alert-error">
                <button type="button" class="close" data-dismiss="alert">×</button>
                <h4>
                    提示!
                </h4> <strong>注意!</strong> 本项目是NoteShare的初个demo,仅包含最基础的功能.
            </div>
        </div>
    </div>
</div>

</body>
</html>
