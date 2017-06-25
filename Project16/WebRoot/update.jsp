<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>Update</title>
    

  </head>
  
  <body>
    <h2>数量更新</h2>
    <form name="updateForm" action="/Project16/update.do" method="post">
    	<table>
    		<tr>
    			<th>BID</th>
    			<th>BAMOUNT</th>
  			</tr>
 			<tr>
    			<th><input type="text" name="bid" /></th>
    			<th><input type="text" name="bamount" /></th>
  			</tr>
    	</table>
    	<input type="submit" value="更新"/>
    </form>
    <HR>
    ${msg}
    </body>
</html>
