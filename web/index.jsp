<%--
  Created by IntelliJ IDEA.
  User: jiee
  Date: 2019-03-11
  Time: 23:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<%
    request.getRequestDispatcher("/product?method=findList").forward(request, response);
%>
</body>
</html>
