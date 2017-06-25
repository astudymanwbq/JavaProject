<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>Query by price</title>
    

  </head>
  
  <body>
    <h2>价格区间查询</h2>
    <form name="queryByPriceForm" action="/Project16/queryprice.do" method="post" >
    	<table>
    		<tr>
    			<th>LOW PRICE</th>
    			<th>HIGH PRICE</th>
  			</tr>
 			<tr>
    			<th><input type="text" name="low" /></th>
    			<th><input type="text" name="high" /></th>
  			</tr>
    	</table>
    	<input type="submit" value="查询"/>
    </form>
    </body>
</html>
