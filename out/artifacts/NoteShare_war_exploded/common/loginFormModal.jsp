<%--
  Created by IntelliJ IDEA.
  User: Aki
  Date: 2022/6/14
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script>
        function login(redirUrl, baseurl) {
            var userName = $("#username").val();
            var userPass = $("#password").val();

            $.post(baseurl + "/login.action", {userName: userName, userPass: userPass},
                function (result) {
                    if (result.login == true) {
                        location.reload();
                        $('#loginFormModal').modal('hide');
                        alert("欢迎登陆")
                        window.location.replace("http://localhost:8080/NoteShare_war_exploded/Choose.jsp");

                    } else {
                        //alert("false");

                        $("#msgTitle").html("登录失败");
                        $("#msgBody").html("用户名或密码错误");
                        $("#msgModal").modal();

                    }
                }, "json");
        }

    </script>
    <script>
        function logout(baseURL) {
            $.post(baseURL + "/logout.action", function () {
                window.location.replace("http://localhost:8080/NoteShare_war_exploded/UserIndex.jsp");
            });
        }

    </script>
</head>
<body>
<div class="modal fade" id="loginFormModal" role="dialog" aria-hidden="true" aria-labelledby="myModalLabel">
    <form role="form" action="${pageContext.request.contextPath}/login.action" method="post">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button class="close" aria-hidden="true" type="button"
                            data-dismiss="modal">×
                    </button>
                    <h4 class="modal-title" id="myModalLabel">用户登录</h4>
                </div>
                <div class="modal-body">

                    <div class="form-group">
                        <label>用户名</label><input type="text" class="form-control" id="username"/>
                    </div>
                    <div class="form-group">
                        <label>密码</label><input type="password" class="form-control" id="password"/>
                    </div>


                </div>
                <div class="modal-footer">

                    <button class="btn btn-primary" type="button"
                            onclick="login('${redirUrl}','${pageContext.request.contextPath}')">登录
                    </button>
                    <button class="btn btn-default" type="button"
                            data-dismiss="modal"
                            <c:if test="${!empty backUrl}">onclick=closeLogForm('${backUrl}')</c:if>   >关闭窗口
                    </button>

                </div>
            </div>
        </div>
    </form>
</div>

<!-- 登陆情况信息提示模态框 -->
<div class="modal fade" id="msgModal" role="dialog" aria-hidden="true" aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" aria-hidden="true" type="button" data-dismiss="modal">
                    ×
                </button>
                <h4 class="modal-title" id="msgTitle">

                </h4>
            </div>
            <div class="modal-body" id="msgBody">

            </div>
            <div class="modal-footer">
                <button class="btn btn-default" type="button" data-dismiss="modal" >关闭窗口</button>
            </div>
        </div>

    </div>

</div>

</body>
</html>
