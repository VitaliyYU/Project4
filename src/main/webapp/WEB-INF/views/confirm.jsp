<%@ page import="ua.training.constant.Attributes" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="<%=session.getAttribute(Attributes.LOCALE)%>"/>
<fmt:setBundle basename="localization" var="msg"/>



<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><fmt:message bundle="${msg}" key="title"/></title>
</head>
<body>
<table class="table">
    <caption><fmt:message bundle="${msg}" key="ticket"/></caption>
    <form method ="post">
        <input type="hidden" name="command" value="LogOut"/>
        <button type="submit"><fmt:message bundle="${msg}" key="LogOut"/></button>
    </form>
    <form method="post">
    <tr>
        <th><fmt:message bundle="${msg}" key="baggage"/></th>
        <th><fmt:message bundle="${msg}" key="priority_boarding"/></th>
        <th><fmt:message bundle="${msg}" key="priority_registration"/></th>
    </tr>
        <td><input name="baggage" type="text"/>+${0.05*cost}<fmt:message bundle="${msg}" key="for_kg"/> </td>
        <td>
            <input type="checkbox" name="boarding" value="true"><fmt:message bundle="${msg}" key="priority_boarding"/>=${cost*0.2}</td>
        </td>
        <td>
            <input type="checkbox" name="registration" value="true"><fmt:message bundle="${msg}" key="priority_registration"/>=${cost*0.3}</td>
        </td>
    <td>${cost}</td>
        <td>
            <input type="hidden" name="ticketId" value="${ticketId}"/>
            <input type="hidden" name="cost" value="${cost}"/>
            <input type="hidden" name="place" value="${place}"/>
            <input type="hidden" name="command" value="buy">
            <button type="submit"  ><fmt:message bundle="${msg}" key="submit"/></button>
        </td>
    </tr>
    </form>
    <c:if test="${error!=null}">${error}</c:if>
</table>
</body>
</html>
