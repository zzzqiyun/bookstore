<%--
  Created by IntelliJ IDEA.
  User: qiyunzhou
  Date: 2018/6/18
  Time: 18:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
response.sendRedirect(request.getContextPath()+"/book.do?method=getBooks");
%>

</body>
</html>
