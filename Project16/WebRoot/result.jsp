<%@page import="java.sql.Date, java.util.ArrayList"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%
request.setCharacterEncoding("utf-8");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>deal with operator</title>
  </head>
  
  <body>
  	<h1>查询结果</h1>
    		<table>
    		<tr>
    			<th>BID</th>
    			<th>BNAME</th>
    			<th>BAUTHOR</th>
    			<th>BTYPE</th>
    			<th>BPRICE</th>
    			<th>BAMOUNT</th>
    			<th>BDATE</th>
  			</tr>
  		<logic:iterate id="book" name="list">
  		<tr>
  			<th><bean:write name="book" property="bid"/></th>
    		<th><bean:write name="book" property="bname"/></th>
    		<th><bean:write name="book" property="bauthor"/></th>
    		<th><bean:write name="book" property="btype"/></th>
   			<th><bean:write name="book" property="bprice"/></th>
   			<th><bean:write name="book" property="bamount"/></th>
   			<th><bean:write name="book" property="bdate"/></th>
   		</tr>
		</logic:iterate>

  		</table>

  </body>
</html>
