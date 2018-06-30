<%--
  Created by IntelliJ IDEA.
  User: qiyunzhou
  Date: 2018/6/30
  Time: 17:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ include file="queryCondition.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<center>
    <br><br>
    User:${user.userName}
    <br>
    <a href="index.jsp">返回购物</a>
    <table border="1" cellpadding="10" cellspacing="0">
        <c:forEach items="${user.trades}" var="trade">
            <tr>
                <td colspan="3">交易时间${trade.tradeTime}</td>
            </tr>
            <tr>
                <td>书名</td>
                <td>价钱</td>
                <td>数量</td>
            </tr>
            <c:forEach items="${trade.items}" var="tradeItem">
                <tr>
                    <td> ${tradeItem.book.title}</td>
                    <td>${tradeItem.book.price}</td>
                    <td>${tradeItem.quantity}</td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="3">&nbsp;</td>
            </tr>
        </c:forEach>
    </table>

</center>

</body>
</html>
