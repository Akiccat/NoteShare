<%--
  Created by IntelliJ IDEA.
  User: Aki
  Date: 2022/5/19
  Time: 22:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
    <title>支付界面</title>
    <script src="js/jquery-1.11.3.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"
            integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
          integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">
</head>
<style>
    em {
        background-color: #ff8c31;
        border-radius: 30px;
        margin-left: 50px;
        font-size: 20px;
    }

    #note{
        width:200px;
        height:200px;
        display: table-cell;
        vertical-align: middle;
        text-align: center;
    }

    #1, #2 {
        position: absolute;
        left: 0px;
        top: 0px;
        z-index: 1;
    }


    .text-center {
        text-align: center
    }


</style>
<body>
<div class="col-lg-12">
    <div class="row ">
        <br/>
        <h2>
            &nbsp;&nbsp;&nbsp;<strong>${sessionScope['_LOGIN_USER_'].userName }</strong>&nbsp;您好,
            <small>您正在查看支付详情</small>
        </h2>
        <br/>
        <div style="background-color: #ced4da;border-radius: 25px;margin-top: -25px;" class="hero-unit">
            <h1 style="font-size: xxx-large">
                <br/>
                &nbsp;&nbsp;&nbsp;<strong>支付详情</strong>
                <br/>&nbsp;&nbsp;&nbsp;<small>Check PayDetail</small>
                <br/>
            </h1>
            <h3 style="font-size: large">
                <em>NoteShare</em>
            </h3>
            <br/>
            <br/>
        </div>

        <c:if test="${msg==null}">
        <div>
            <div style="background-color: #afdd22;border-radius: 25px;margin-bottom: -10px;margin-top: -13px">
                <div style="margin-left: 30px">
                    <br/>
                    <h2 style="text-align: center"><strong>确认您的支付信息</strong></h2>
                    <h2 style="margin-top: 8px;font-style: oblique"><strong>笔记代码：</strong>${payDetail.noteCode}</h2>
                    <h2 style="margin-top: 8px;font-style: oblique"><strong>支付订单：</strong>${payDetail.payCode}</h2>
                    <h2 style="margin-top: 8px;font-style: oblique"><strong>支付价格：</strong>${payDetail.price}￥</h2>
                    <h2 style="margin-top: 8px;font-style: oblique"><strong>支付留言：</strong>${payDetail.word}</h2>
                    <h2 style="margin-top: 8px;font-style: oblique"><strong>接收人：</strong>${dstUser}</h2>
                    <h2 style="margin-top: 8px;font-style: oblique"><strong>支付者：</strong>${srcUser}</h2>
                </div>
                <br/>
            </div>



            <div style="background-color: #4b5cc4;
        border-radius: 30px;" class="col-md-12">
                <div  class="container-fluid" style="margin-bottom: 30px">
                    <h1 style="text-align: center;"><strong
                            style="color: #ff8c31;">支付二维码</strong></h1>
                    <div class="center-block">
                        　　　　　　 <img loading="lazy" class="center-block" id="pic" alt="暂无图片"
                                    src="images/Theam/NoteShare.png"/>
                        <br/>
                        <form action="${pageContext.request.contextPath}/payConfirm.action" method="post">
                            <input type="hidden" value="${payDetail.payCode}" name="payCode">
                            <input type="hidden" value="${payDetail.noteId}" name="note_id">
                            <button class="btn btn-default center-block"
                                    style="background-color: #ff8c31;border-color:#ff8c31 "
                                    type="submit">
                                完成支付
                            </button>
                        </form>
                    </div>
                </div>
                <br/>
            </c:if>
            <c:if test="${msg!=null}">
                <div>
                    <div style="background-color: #afdd22;border-radius: 25px;margin-bottom: -10px;margin-top: -13px">
                        <div>
                            <br/>
                            <h2 style="text-align: center"><strong>感谢您的支付</strong></h2>
                        </div>
                        <br/>
                    </div>
                </div>

                <div style="background-color: #4b5cc4;
        border-radius: 30px;" class="col-md-12">
                    <div  class="container-fluid" style="margin-bottom: 30px">
                        <div class="center-block">
                            <br/>
                            <br/>
                        　　　　　　　<img class="center-block" loading="lazy"
                                    alt="暂无图片"
                                    src="images/Theam/校验_check-one.png"/>
                            <br/>
                            <p style="text-align: center;">
                                <strong style="color: #42b85e;font-size: xxx-large">支付成功</strong></p>
                            <p style="text-align: center">您现在可以关闭这个标签页了</p>
                        </div>
                    </div>
                    <br/>
                    <br/>
            </c:if>

        </div>
    </div>
</div>
</body>
</html>
