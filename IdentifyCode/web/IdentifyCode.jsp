<%--
  Created by IntelliJ IDEA.
  User: BBQ
  Date: 2017/4/25
  Time: 22:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8"  %>
<html>
<head>
        <title>验证码校验</title>
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <script type="text/javascript">
        function reloadCode(){
            document.getElementById("imageCode").src = document
                    .getElementById("imageCode").src
                + "?nocache=" + new Date().getTime();
        }
    </script>
</head>

<body>
<form action="/LoginServlet" method="get">
    验证码:<input type="text" name="inputCode"/>
    <img alt="验证码" id="imageCode" src="ImageServlet"/>
    <a href="" onclick="reloadCode()">看不清楚,换一个</a>
    <br>
    <input type="submit" value="提交">
</form>
</body>
</html>