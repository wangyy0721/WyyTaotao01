<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>JSONP页面测试-http://localhost:8081/a.jsp</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body>
    <div id="divCustomers">
    </div>
    <script type="text/javascript">
        function onCustomerLoaded(result, methodName) {
            var html = '<ul><li>JSONP_TEST</li>';
            html += '<li>' + result.status + '</li>';
            html += '<li>' + result.msg + '</li>';
            html += '<li>' + result.data + '</li>';
            html += '</ul>';
            //alert(html);
            //alert(result.data);
           // alert(methodName);
            document.getElementById('divCustomers').innerHTML = html;
        }
    </script>
    <script type="text/javascript" src="http://localhost:8084/user/check/zhangsan/1?callback=onCustomerLoaded"></script>
  </body>
</html>
