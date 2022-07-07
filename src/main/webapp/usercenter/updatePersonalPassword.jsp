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
    <title>修改密码</title>
    <script type="text/javascript">
        function check() {
            var newUserPass = $("#newUserPass").val();
            var newUserPassConfirm = $("#newUserPassConfirm").val();

            if (newUserPass != newUserPassConfirm) {
                alert("两次密码输入不一致");
                return false;
            } else {
                return true;
            }
        }
    </script>
</head>
<%@include file="carModal.jsp" %>
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
        border-radius: 30px;
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
            <small>您正在查看用户中心</small>
        </h2>
        <br/>
        <div style="background-color: #ced4da;border-radius: 25px;margin-top: -25px;" class="hero-unit">
            <h1 style="font-size: xxx-large">
                <br/>
                &nbsp;&nbsp;&nbsp;<strong>用户中心</strong>
                <br/>&nbsp;&nbsp;&nbsp;<small>Check UserCenter</small>
                <br/>
            </h1>
            <h3 style="font-size: large">
                <em>NoteShare</em>
            </h3>
            <br/>
            <br/>
        </div>


        <div>
            <div id="1"
                 style="background-color: #afdd22;border-radius: 25px;margin-bottom: -10px;margin-top: -20px;float: left;width: 50%;">
                <div style="margin-left: 40px;">
                    <br/>
                    <h4 style="text-align: center; margin-top: 8px;font-style: oblique"><a
                            href="${pageContext.request.contextPath}/usercenter/getPersonalInfo.action"
                            style="color: black">修改个人信息</a></h4>
                </div>
                <br/>
            </div>
            <div id="2"
                 style="background-color: #fa8c35;border-radius: 25px;margin-bottom: -10px;margin-top: -20px;float: right;width: 50%">
                <div style="margin-left: 40px;">
                    <br/>
                    <h4 style="text-align: center; margin-top: 8px;font-style: oblique"><a
                            href="${pageContext.request.contextPath}/usercenter/getPersonalPassword.action"
                            style="color: black;">修改密码</a></h4>
                </div>
                <br/>
            </div>


            <div style="background-color: #fa8c35;
        border-radius: 5px;margin-top: -10px;z-index: 2;margin-bottom: -30px" class="col-md-12">
                <div id="note" class="col-md-12">
                    <div class="col-md-8" style="margin-left: 14%;">
                        <form role="form" id="passForm" method="post"
                              action="${pageContext.request.contextPath}/usercenter/updatePersonalPassword.action"
                              onsubmit="return check()">

                            <div class="form-group">
                                <label for="userPass"> 旧密码 </label> <input class="form-control"
                                                                           name="userPass" id="userPass" type="password"
                                                                           required/>
                            </div>
                            <div class="form-group">
                                <label for="userPass"> 新密码 </label> <input class="form-control"
                                                                           name="newUserPass" id="newUserPass"
                                                                           type="password" required/>
                            </div>
                            <div class="form-group">
                                <label for="userPass1"> 密码确认 </label> <input class="form-control"
                                                                             name="newUserPassConfirm"
                                                                             id="newUserPassConfirm" type="password"
                                                                             required/>
                            </div>
                            <div class="form-group">
                                <button class="btn btn-primary" type="submit">确定</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
</body>
</html>
