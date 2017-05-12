<%--
  Created by IntelliJ IDEA.
  User: BBQ
  Date: 2017/4/26
  Time: 19:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>identifyCode by Kaptcha </title>
    <meta http-equiv="content-type" content="text/html" charset="utf-8">
    <script type="text/javascript">
        function changeIdentifyCode(node){
            //点击产生不同的验证码
            node.src="IdentifyCode.jpg?time="+new Date().getTime();

        }
    </script>
</head>
<body>
    <img alt="random" src="IdentifyCode.jpg" onclick="changeIdentifyCode(this)" style="cursor: pointer">
    <form action="checkCode.jsp">
        <input type="text" name="inputCode">
        <input type="submit" value="confirm">
    </form>
</body>
</html>
