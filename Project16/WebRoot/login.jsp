<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>JSP for LoginFrom form</title>
  </head>
  
  <body>
    <html:form action="/login">
    please input your account : <html:text property="account" /><br />
    please input your password : <html:password property="password" /> <br />
    <html:submit value="login"/>
    </html:form>
    <HR>
    ${msg}
  </body>
</html>
