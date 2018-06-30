<%@ page import="domain.Book" %><%--
  Created by IntelliJ IDEA.
  UserDao: qiyunzhou
  Date: 2018/6/20
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page  isELIgnored="false"%>

<%@ include file="queryCondition.jsp" %>
<html>
<head>
    <script src="../../jquery-3.3.1.min.js"></script>
    <title>Title</title>
</head>
<body>
<br><br>
<br><br>
    <center>
        <br><br>
        书名：${book.title}
        <br><br>
        作者：${book.author}
        <br><br>
        价格：${book.price}
        <br><br>
        出版时间：${book.publishingDate}
        <br><br>
        评论：${book.remark}
        <br><br>

        <a href="book.do?method=getBooks&pageNo=${param.pageNo}">继续购物</a>
    </center>
</body>
</html>
