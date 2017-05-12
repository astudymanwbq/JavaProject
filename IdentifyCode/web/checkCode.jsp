<%@ page import="com.google.code.kaptcha.Constants" %>
<%--
  Created by IntelliJ IDEA.
  User: BBQ
  Date: 2017/4/26
  Time: 19:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html" charset="utf-8">
    <title>Check Code</title>
</head>
<body>
    <%
        //检查是否是正确的验证码
        String rightCode=(String)session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        String inputCode=request.getParameter("inputCode");
        if(rightCode.equalsIgnoreCase(inputCode))
          out.println("true");
          out.println(rightCode+"---"+inputCode);
    %>
</body>
</html>
