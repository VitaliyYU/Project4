<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vitaliy
  Date: 06.06.17
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form name="addWallet" method="get"  >
    <label for="name-box" >code</label><br/>
    <input name="code" id="name-box" type="text" size="40"/><br/>
    <input type="hidden" name="command" value="addWallet"/>
    <button type="submit">submit</button>
</form>
<c:if test="${error!=null}">${error}</c:if>
</body> 
</html>
