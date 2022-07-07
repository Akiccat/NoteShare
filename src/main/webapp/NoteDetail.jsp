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
    <title>NoteDetail</title>
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
<%--<%@include file="usercenter/carModal.jsp" %>--%>
<style>
    ul {
        top: 0px; /* Set the top position of pinned element */
        right: 0px;
        list-style: none;
    }

    #left_cate li {
        float: left;
    }
</style>
</head>
<style>
    em {
        background-color: #ff8c31;
        border-radius: 30px;
        margin-left: 50px;
        font-size: 20px;
    }

    #note {
        margin-left: 40px;
        margin-top: 34px;
        margin-bottom: 34px;
        border-radius: 30px;
        background-color: #4b5cc4;
    }

    #pic {
        border-radius: 30px;
    }

</style>
<body>

<br/>
<h2>
    &nbsp;&nbsp;&nbsp;<strong>${sessionScope['_LOGIN_USER_'].userName }</strong>&nbsp;您好,
    <small>您正在查看的笔记是:${noteDetail.noteName}</small>
</h2>
<br/>
<div style="background-color: #ced4da;border-radius: 25px;margin-top: -25px;" class="hero-unit">
    <h1 style="font-size: xxx-large">
        <br/>
        &nbsp;&nbsp;&nbsp;<strong>笔记详情</strong>
        <br/>&nbsp;&nbsp;&nbsp;<small>Check NoteDetail</small>
        <br/>
    </h1>
    <h3 style="font-size: large">
        <em>NoteShare</em>
    </h3>
    <br/>
    <br/>
</div>


<div>
    <div style="background-color: #afdd22;border-radius: 25px;margin-bottom: -10px;margin-top: -13px">
        <div style="margin-left: 40px">
            <br/>
            <h2 style="margin-top: 8px;font-style: oblique">笔记名称：${noteDetail.noteName}</h2>
            <h4 style="margin-top: 8px;font-style: oblique">笔记上传者：${noteDetail.uploadUser}(${noteDetail.userEmail})
                <a href="#payModal" data-toggle="modal">赞助他</a>
            </h4>
            <h4 style="margin-top: 8px;font-style: oblique">上传时间：${noteDetail.uploadDate}</h4>
            <h4 style="margin-top: 8px;font-style: oblique">笔记编码：${noteDetail.noteCode}</h4>
        </div>
        <br/>
    </div>


    <div style="background-color: #4b5cc4;
        border-radius: 30px;" class="col-md-12">
        <div id="note" class="container-fluid">
            <div class="row center-block"></div>
            <div class="center-block">
                <img loading="lazy" class="center-block" id="pic" alt="暂无图片"
                     src="${pageContext.request.contextPath}${noteDetail.picPath}"/>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="payModal" role="dialog" aria-hidden="true" aria-labelledby="myModalLabel">
    <form role="form" action="${pageContext.request.contextPath}/notePay.action" method="post" target="_blank">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button class="close" aria-hidden="true" type="button"
                            data-dismiss="modal">×
                    </button>
                    <h4 class="modal-title" id="myModalLabel">用户赞助</h4>
                </div>
                <div class="modal-body">
                    <label>该用户的联系方式:${noteDetail.userEmail}</label>
                    <div class="form-group">
                        <label>赞助金额</label><input name="price" type="number" step="10" min="10" max="100"
                                                  class="form-control" id="username"/>
                    </div>
                    <div class="form-group">
                        <label>留言</label><input name="word" type="text" class="form-control" id="password"/>
                    </div>
                    <input name="note_id" class="form-control" type="hidden" value="${noteDetail.noteId}"/>
                    <input name="dstUserid" class="form-control" type="hidden" value="${noteDetail.dstUserId}"/>
                    <input name="noteCode" class="form-control" type="hidden" value="${noteDetail.noteCode}"/>
                </div>
                <div class="modal-footer">

                    <%--<button class="" type="button">
                    </button>--%>
                    <input type="submit" class="btn btn-primary">
                    <button class="btn btn-default" type="button"
                            data-dismiss="modal">关闭
                    </button>

                </div>
            </div>
        </div>
    </form>
</div>

<div class="row" style="border:0px solid red">
    <div class="col-sm-1">
        <div class="row">
            <ul class="list-group" id="left_cate">
                <li><a style="background-color: #3b2e7e;color: #ced4da;border-color: #3b2e7e"
                       href="${pageContext.request.contextPath}/Choose.jsp"
                       class="list-group-item">Choose</a></li>
                <li><a style="background-color: #fa8c35;border-color: #fa8c35"
                       href="${pageContext.request.contextPath}/InputPictureController?subject=Math"
                       class="list-group-item">Math</a></li>
                <li><a style="background-color: #fa8c35;border-color: #fa8c35"
                       href="${pageContext.request.contextPath}/InputPictureController?subject=Paint"
                       class="list-group-item">Paint</a></li>
                <li><a style="background-color: #fa8c35;border-color: #fa8c35"
                       href="${pageContext.request.contextPath}/InputPictureController?subject=Code"
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

    <c:if test="${sessionScope['_LOGIN_USER_'].userId eq noteDetail.dstUserId}">
    <form action="${pageContext.request.contextPath}/delNote.action" method="post">
        <input type="hidden" name="noteCode" value="${noteDetail.noteCode}">
        <div class="col-md-12 column">
            <button type="submit" class="btn btn-danger btn-default btn-block" style="border-radius: 30px">删除笔记</button>
        </div>
    </form>
    </c:if>

    <c:forEach items="${payDetail}" var="n" varStatus="nt">
    <div class="col-md-12" style="margin-bottom: 1px">
        <div class="container-fluid" style="background-color: #ffb636;border-radius: 30px;">
            <div class="row-fluid">
                <div class="span12">
                    <div class="alert alert-error">
                        <button type="button" class="close" data-dismiss="alert">×</button>
                        <h4>
                            用户&nbsp;<strong>${n.srcUsername}</strong>&nbsp;赞助了作者
                        </h4>
                        <br/>
                        并留下了一句话:<strong style="font-size: x-large">${n.word}</strong>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </c:forEach>
</body>
</html>
