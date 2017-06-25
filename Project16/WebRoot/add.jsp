<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>add Book</title>

  </head>
  
  <body>
  	<h2>添加记录</h2>
    <html:form action="/add">
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
 			<tr>
    			<th><html:text property="bid" /></th>
    			<th><html:text property="bname" /></th>
    			<th><html:text property="bauthor" /></th>
    			<th><html:text property="btype" /></th>
    			<th><html:text property="bprice" /></th>
    			<th><html:text property="bamount" /></th>
				<th><html:text property="bdate" /></th>
  			</tr>
    	</table>
    	<html:submit value="添加" />
    </html:form>
    <HR>
    ${msg}
  </body>
</html>
