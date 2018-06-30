<%--
  Created by IntelliJ IDEA.
  User: qiyunzhou
  Date: 2018/6/20
  Time: 19:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="jquery-3.3.1.min.js"></script>
<script type="text/javascript">

  /*$(function () {
    $("a").click(function () {
      var serializeVal = $(":hidden").serialize();
      var href = this.href + "&" + serializeVal;
      window.location.href = href;
      return false;
    });

  })*/

  $(function(){
    $("a").each(function(){
      this.onclick = function () {
        var serializeVal = $(":hidden").serialize();
        var href = this.href + "&" + serializeVal;
        window.location.href = href;
        return false;
      };
    });
  })


</script>

<input type="hidden" name="minPrice" value="${param.minPrice}"/>
<input type="hidden" name="maxPrice" value="${param.maxPrice}"/>

