<%@ page import="ua.training.constant.Attributes" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="<%=session.getAttribute(Attributes.LOCALE)%>"/>
<fmt:setBundle basename="localization" var="msg"/>


<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post">
    <input type="hidden" name="command" value="LogOut"/>
    <button type="submit"><fmt:message bundle="${msg}" key="LogOut"/></button>
</form>
<c:if test="${not tickets.isEmpty()}" >
    <table class="table">
        <caption><fmt:message bundle="${msg}" key="ticket"/></caption>
        <tr>
            <th><fmt:message bundle="${msg}" key="place"/></th>
            <th><fmt:message bundle="${msg}" key="departure"/></th>
            <th><fmt:message bundle="${msg}" key="destination"/></th>
            <th><fmt:message bundle="${msg}" key="date"/></th>
        </tr>
        <c:forEach items="${tickets}" var="ticket"><tr>
            <td>${ticket.getPlace()}</td>
            <td>
                ${flights.get(ticket.flightId-1).departure}
            </td>
            <td>${flights.get(ticket.flightId-1).destination}</td>
            <td>${flights.get(ticket.flightId-1).date}</td>
        </tr></c:forEach>
    </table>
</c:if>
<c:if test="${tickets.isEmpty()}">
    <h2 class="text-center"><fmt:message bundle="${msg}" key="noTickets"/></h2>
</c:if>
</form>
<form method="post">
    <input type="hidden" name="command" value="showFlight">
    <button type="submit" ><fmt:message bundle="${msg}" key="showFlights"/></button>
</form>
<c:if test="${error!=null}">${error}</c:if>
</body>
</html>
