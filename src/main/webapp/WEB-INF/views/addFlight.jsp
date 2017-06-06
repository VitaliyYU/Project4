<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vitaliy
  Date: 05.06.17
  Time: 18:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form name="addFlight" method="post"  >
    <label for="name-box" >departure</label><br/>
    <input name="departure" id="name-box" type="text" size="40"/><br/>
    <label for="surname-box" >destination</label><br/>
    <input name="destination" id="surname-box" type="text" size="40"/><br/>

    <label for="username-box" >date</label><br/>
    <input name="date" id="username-box" type="datetime-local" size="40"/><br/>
    <label for="username-box" >startcost</label><br/>
    <input name="cost" id="password-box" type="text" size="40"/><br/>
    <label for="ticket-box" >ticket count</label><br/>
    <input name="ticketsCount" id="ticket-box" type="text" size="40"/><br/>
    <input type="hidden" name="command" value="addFlight"/>
    <button type="submit">submit</button>
</form>
<c:if test="${error!=null}">${error}</c:if>
</body>
</html>
