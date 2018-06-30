<%--
  Created by IntelliJ IDEA.
  User: qiyunzhou
  Date: 2018/6/18
  Time: 19:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page  isELIgnored="false"%>
<%@ include file="queryCondition.jsp"%>
<html>
<head>
    <title>书本显示</title>
    <script src="../../jquery-3.3.1.min.js"></script>
    <script type="text/javascript">

      $(function () {
        /*跳转页数*/
        $("#pageNo").change(function(){

          //得到当前的页码
          var pageNo = $(this).val();
          //去掉前后空格
          pageNo = $.trim(pageNo);

          //1、正则判断，检验页码是否为数字，而不是1b,qq，3cc之类
          var reg = /^\d+$/g;
          if(!reg.test(pageNo)){
                $(this).val("");
                alert("输入的页数不合法");
                return ;
          }

          //2、判断输入的页数是否在合法的范围内
          var pageNo2 = parseInt(pageNo);
          if(pageNo2<1||pageNo2>parseInt("${bookPage.totalPageNumber}")){
                $(this).val("");
                alert("输入的页码不合法");
                return;
          }

          //3、翻页
          var href = "book.do?method=getBooks&pageNo="+pageNo2+"&"+$(":hidden").serialize();
          window.location.href = href;
        });

      })

    </script>
</head>
<body>

<br><br>
<center>

    <c:if test="${param.title !=null}">
        你已经&nbsp;&nbsp;${param.title}&nbsp;&nbsp;加入购物车
    </c:if>

    <br><br>
    <c:if test="${!empty sessionScope.ShoppingCart.books}">
        购物车中共有${sessionScope.ShoppingCart.bookNumber}本书,<a href="book.do?method=forwardPage&page=cart&pageNo=${bookPage.pageNo}">查看购物车</a>
    </c:if>


    <br><br>
    <form action="book.do?method=getBooks" method="post">
        price:  <input title="text" size="10" name="minPrice"> -
                <input title="text" size="10" name="maxPrice">
                <input type="submit" value="submit">
    </form>
    <br><br>
    <table cellpadding="10">
        <c:forEach items="${ bookPage.list }" var="book">
            <tr>
                <td >
                    <a href="book.do?method=getBook&pageNo=${bookPage.pageNo}&id=${book.bookId}">${book.title}</a>
                    <br>
                    ${book.author}
                </td>
                <td>${book.price}</td>
                <td><a href="book.do?method=addToCart&pageNo=${bookPage.pageNo}&id=${book.bookId}&title=${book.title}">加入购物车</a></td>
            </tr>
        </c:forEach>
    </table>

    <br><br>
    共${bookPage.totalPageNumber}页
    &nbsp;&nbsp;
    当前第${bookPage.pageNo}页
    &nbsp;&nbsp;
    <c:if test="${bookPage.hasPrev}">
        <a href="book.do?method=getBooks&pageNo=1">首页</a>
        &nbsp;&nbsp;
        <a href="book.do?method=getBooks&pageNo=${bookPage.prePage}">上一页</a>
    </c:if>

    <c:if test="${bookPage.hasNext}">
        <a href="book.do?method=getBooks&pageNo=${bookPage.nextPage}">下一页</a>
        &nbsp;&nbsp;
        <a href="book.do?method=getBooks&pageNo=${bookPage.totalPageNumber}">末页</a>
    </c:if>
    &nbsp;&nbsp;

    转到 <input type="text" size="5" id="pageNo" />页


</center>
</body>
</html>
