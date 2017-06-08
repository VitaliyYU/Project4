<%@ page import="ua.training.constant.Attributes" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="<%=session.getAttribute(Attributes.LOCALE)%>"/>
<fmt:setBundle basename="localization" var="msg"/>


<html>
<head>
    <title><fmt:message bundle="${msg}" key="title"/></title>
</head>
<body>
<form method="post">
    <input type="hidden" name="command" value="LogOut"/>
    <button type="submit"><fmt:message bundle="${msg}" key="LogOut"/></button>
</form>
<form name="addFlight" method="post"  >
    <label for="name-box" ><fmt:message bundle="${msg}" key="departure"/></label><br/>
    <input name="departure" id="name-box" type="text" size="40"/><br/>
    <label for="surname-box" ><fmt:message bundle="${msg}" key="destination"/></label><br/>
    <input name="destination" id="surname-box" type="text" size="40"/><br/>

    <label for="username-box" ><fmt:message bundle="${msg}" key="date"/></label><br/>
    <input name="date" id="username-box" type="datetime-local" size="40"/><br/>
    <label for="username-box" ><fmt:message bundle="${msg}" key="cost"/></label><br/>
    <input name="cost" id="password-box" type="text" size="40"/><br/>
    <label for="ticket-box" ><fmt:message bundle="${msg}" key="ticketCount"/></label><br/>
    <input name="ticketsCount" id="ticket-box" type="text" size="40"/><br/>
    <input type="hidden" name="command" value="addFlight"/>
    <button type="submit"><fmt:message bundle="${msg}" key="submit"/></button>
</form>
<c:if test="${error!=null}">${error}</c:if>
</body>
</html>
