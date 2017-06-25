<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>Query by name</title>
    

  </head>
  
  <body>
    <h2>名字模糊查询</h2>
    <html:form action="/queryname">
    	<table>
    		<tr>
    			<th>Name</th>
  			</tr>
 			<tr>
    			<th><html:text property="name" /></th>
  			</tr>
    	</table>
    	<html:submit value="查询"/>
    </html:form>
    </body>
</html>
