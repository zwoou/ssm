<%--
  Created by IntelliJ IDEA.
  User: cc
  Date: 2017/7/3
  Time: 14:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/add.do" method="post">
        <input type="text" name="id">
        <input type="text" name="username">
        <input type="submit" value="添加">
    </form>
home
</body>
</html>
