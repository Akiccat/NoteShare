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
    <title>NotePage</title>
    <script src="js/jquery-1.11.3.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"
            integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
          integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">
    <script>
        $(document).ready(function () {
            $("#left_cate").affix({
                offset: {
                    top: -20,
                    bottom: function () {
                        return (this.bottom = $('.bottom').outerHeight(true));
                    }

                }
            });
        });

    </script>
</head>
<%@include file="usercenter/carModal.jsp" %>
<style>
    ul {
        top: 0px; /* Set the top position of pinned element */
        right: 0px;
        list-style: none;
    }
    #left_cate li{
        float: left;
    }
</style>
<style>

    em {
        background-color: #ff8c31;
        border-radius: 30px;
        margin-left: 50px;
        font-size: 20px;
    }

    #Notebackground {

    }

    /*#name{
        background-color: #ff8c31;
        border-radius: 30px;
        font-size: 20px;
    }*/
    #note {
        margin-left: 40px;
        margin-top: 34px;
        margin-bottom: 34px;
        border-radius: 30px;
        background-color: #4b5cc4;

    }

    #pic {
        height: 200px;
        width: 200px;
        border-radius: 30px;
        box-shadow: 1px 1px 5px #3c3c3c;
    }
</style>
<body>
<br/>
<h2>
    &nbsp;&nbsp;&nbsp;<strong>${sessionScope['_LOGIN_USER_'].userName }</strong>&nbsp;${type}您好,
    <small>您正在查看的项目是:${subject}</small>
</h2>
<br/>
<div style="background-color: #ced4da;border-radius: 25px;margin-top: -25px;" class="hero-unit">
    <h1 style="font-size: xxx-large">
        <br/>
        &nbsp;&nbsp;&nbsp;<strong>查看笔记</strong>
        <br/>&nbsp;&nbsp;&nbsp;<small>Check NoteBook</small>
        <br/>
    </h1>
    <h3 style="font-size: large">
        <em>NoteShare</em>
    </h3>
    <br/>
    <br/>
</div>


<div>
    <form style="background-color: #afdd22;border-radius: 25px;margin-bottom: -10px;margin-top: -13px"
          action="UploadHandleServlet?subject=${subject}"
          enctype="multipart/form-data" method="post">
        <div style="margin-left: 40px">
            <br/>
            <h3 style="margin-top: 8px;font-style: oblique">上传笔记：</h3>
            <div style="display: inline-flex">
                <input type="file" name="file1"><br/>
                <input class="btn-group-sm" type="submit" value="提交">

            </div>
        </div>
        <br/>
    </form>
</div>

<%--<div id="Note">
    <c:forEach items="${ImageName}" var="image" begin="1"><img
            style="margin-left: 34px;margin-top: 34px;margin-bottom: 34px;border-radius: 30px;"
            src="${image}"></c:forEach>
</div>--%>

<div style="background-color: #4b5cc4;
        border-radius: 30px;" class="col-lg-12">
    <c:forEach items="${noteList}" var="c" varStatus="vs">
        <div id="note" class="col-md-2">
            <a
                    href="${pageContext.request.contextPath}/notesDetail.action?note_id=${c.noteId}">
                <div style="border-radius: 30px;background-color: #ff8c31;box-shadow: 10px 10px 20px #3c3c3c;border-color: #ff8c31" class="thumbnail homegoods">
                    <img loading="lazy" id="pic" alt="暂无图片"
                         src="${pageContext.request.contextPath}${c.picPath}"/>
                    <div id="name" class="caption caption-style">
                            <%--<p>${c.noteName}</p>--%>
                        <p style="text-align: center;font-size: larger;" class="font-red"> <strong>${c.noteName}</strong></p>
                    </div>
                </div>
            </a>
        </div>
        <%--<img style="margin-left: 34px;margin-top: 34px;margin-bottom: 34px;border-radius: 30px;"
            src="${pageContext.request.contextPath}${c.picPath}">--%>
    </c:forEach>
</div>


<%--<div class="container-fluid" style="background-color: #a1afc9;border-radius: 30px;">
    <div class="row-fluid">
        <div class="span12">
            <div class="alert alert-error" >
                <button type="button" class="close" data-dismiss="alert">×</button>
                <h4>
                    提示!
                </h4> <strong>注意!</strong> 本项目是NoteShare的初个demo,仅包含最基础的功能.
            </div>
        </div>
    </div>
</div>--%>
<div class="row" style="border:0px solid red">
    <div class="col-sm-1">
        <div class="row">
            <ul class="list-group" id="left_cate">
                <li><a style="background-color: #3b2e7e;color: #ced4da;border-color: #3b2e7e" href="${pageContext.request.contextPath}/Choose.jsp"
                       class="list-group-item">Choose</a></li>
                <li><a style="background-color: #fa8c35;border-color: #fa8c35" href="${pageContext.request.contextPath}/InputPictureController?subject=Math"
                       class="list-group-item">Math</a></li>
                <li><a style="background-color: #fa8c35;border-color: #fa8c35" href="${pageContext.request.contextPath}/InputPictureController?subject=Paint"
                       class="list-group-item">Paint</a></li>
                <li><a style="background-color: #fa8c35;border-color: #fa8c35" href="${pageContext.request.contextPath}/InputPictureController?subject=Code"
                       class="list-group-item">Code</a></li>
                <li>
                    <form action="${pageContext.request.contextPath}/searchNote.action" method="post">
                        <input style="margin-bottom: 20px;height: 42px" type="text" name="noteName">
                        <button style="background-color: #fa8c35;border-color: #fa8c35;text-align: center;border-radius: 5px;margin-top: -20px"
                                type="submit"
                                class="list-group-item">搜索
                        </button>
                    </form>
                <li>
            </ul>
        </div>
    </div>
</div>
</body>
</html>
