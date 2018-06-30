<%--
  Created by IntelliJ IDEA.
  User: qiyunzhou
  Date: 2018/6/21
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page  isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page  isELIgnored="false"%>
<%@ include file="queryCondition.jsp"%>
<html>
<head>
    <script src="../../jquery-3.3.1.min.js"></script>
    <title>购物车</title>
    <script>
        $(function () {

          $(".delete").click(function () {
           var $tr = $(this).parent().parent();
           var title = $.trim($tr.find("td:first").text());
           var flag = confirm("确定要删除"+title+"的信息吗？");
           if(flag)
             return true;
           return false;
          });

    //AjAx修改购物车内单个商品的数量
          $(":text").change(function(){
            var quantityVal = $.trim(this.value);
            //校验quantity是否合法
            var flg = false;
            var reg = /^\d+$/g;
            var quantity = -1;
            if(reg.test(quantityVal)){
              quantity = parseInt(quantityVal);
              if(quantity >= 0){
                flg = true;
              }
            }
            if(!flg){
              alert("你输入的数量不合法！");
              $(this).val($(this).attr("class"));
              return;
            }
            var $tr = $(this).parent().parent();
            var title = $.trim($tr.find("td:first").text());
            if(quantity==0){
              var flag2 = confirm("确定要删除"+title+"的信息吗？");
              if(flag2){
                var $a = $tr.find("td:last").find("a");
                //执行a节点的响应函数
                $a[0].onclick();
                return;
              }
            }
           //1、获取页面中所有的text,并为text加上onchangge响应函数,弹出确认：确定要修改吗
            var flag = confirm("确定要修改"+title+"的数量？");
            if(!flag){
              //修改数量不成功，将原数值还原
              $(this).val($(this).attr("class"));
              return;
            }
              //2、请求地址：book.do
              var url = "book.do";
              //3、请求参数：method=updateItemQuantity,id:text中name的属性值，quantity：text中的value值，time:new Date()
              var idVal = $.trim(this.name);
              var args = {"method":"updateItemQuantity","bookId":idVal,"quantity":quantityVal,"time":new Date()};
              //6、更新当前页面的bookNumber.totalMoney
              $.post(url,args,function(data){
                var bookNumber = data.bookNumber;
                var totalMoney = data.totalMoney;
                $("#totalMoney").text("总金额 ¥"+totalMoney);
                $("#bookNumber").text("你的购物车共有"+bookNumber+"本书");
              },"JSON");
          });

        })

    </script>

</head>
<body>

<center>
    <br><br>
    <div id="bookNumber">
    你的购物车共有${sessionScope.ShoppingCart.bookNumber}本书
    </div>
    <table cellpadding="10">
        <tr>
            <td>书名</td>
            <td>价格</td>
            <td>数量</td>
            <td>&nbsp;</td>
        </tr>

        <c:forEach items="${sessionScope.ShoppingCart.items}" var="sci">
            <tr>
                <td>${sci.book.title}</td>
                <td>${sci.book.price}</td>
                <td>
                <input type="text" class="${sci.quantity}" size="10" name="${sci.book.bookId}" value="${sci.quantity}"/>
                </td>
                <td ><a class="delete" href="book.do?method=removeItem&pageNo=${param.pageNo}&id=${sci.book.bookId}" >删除</a></td>
            </tr>

        </c:forEach>

        <tr>
            <td colspan="4" id="totalMoney">总金额：¥ ${sessionScope.ShoppingCart.totalMoney}</td>
        </tr>
        <tr>
            <td colspan="4">
                <a href="book.do?method=getBooks&pageNo=${param.pageNo}">继续购物</a>
                &nbsp;&nbsp;

                <a href="book.do?method=removeAllItem&pageNo=${param.pageNo}">清空购物车</a>
                &nbsp;&nbsp;

                <a href="book.do?method=forwardPage&page=checkOut&pageNo=${param.pageNo}">结账</a>

            </td>
        </tr>
    </table>
</center>

</body>
</html>
