<%--
  Created by IntelliJ IDEA.
  User: qiyunzhou
  Date: 2018/6/27
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page  isELIgnored="false"%>
<%@ include file="queryCondition.jsp"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<center>
    <br><br>
    您的购物车中一共有${sessionScope.ShoppingCart.bookNumber}本书
    <br>
    应付：¥${sessionScope.ShoppingCart.totalMoney}元
    <br><br>

    <c:if test="${requestScope.errors!=null}">
        <front color="red"> ${requestScope.errors}</front>
    </c:if>



<form name="from" action="book.do?method=cash" method="post">
    <table cellpadding="10">
        <tr>
            <td>信用卡账号：</td>
            <td><input type="text" name="accountId"  /></td>
        </tr>
        <tr>
            <td>信用卡姓名：</td>
            <td><input type="text"  name="userName" /></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="submit"/></td>
        </tr>

    </table>

</form>
</center>



</body>
</html>
