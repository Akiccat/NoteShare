<%--
  Created by IntelliJ IDEA.
  User: Aki
  Date: 2022/5/18
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="Login" method="post">
    <table border="1px">
        <caption>Login</caption>
        <tr>
            <th>
                用户名
            </th>
            <td>
                <input type="text" name="username">
            </td>
        <tr>
            <th>
                密码
            </th>
            <td>
                <input type="text" name="pw">
            </td>
        <td colspan="2" align="center">
            <input type="submit" value="提交">
            <input type="reset" value="重置">
        </td>
        </tr>
    </table>
</form>
</body>
</html>
