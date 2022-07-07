<%--
  Created by IntelliJ IDEA.
  User: Aki
  Date: 2022/5/17
  Time: 21:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/Register" method="post">
    <table border="1px">
        <caption>添加用户</caption>
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
        <tr>
            <th>
                电话
            </th>
            <td>
                <input type="text" name="tel">
            </td>
        <tr>
            <th>
                地址
            </th>
            <td>
                <textarea name="addr"></textarea>
            </td>
        <tr>
        <tr>
            <td align="right"><b>类型</b></td>
            <td>
                <select name="usertype">
                    <option value="管理">管理</option>
                    <option value="游客">游客</option>
                    <option value="普通用户">普通用户</option>
                </select>
            </td>
        </tr>
            <td colspan="2" align="center">
                <input type="submit" value="提交">
                <input type="reset" value="重置">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
