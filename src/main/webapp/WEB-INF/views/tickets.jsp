<%@ page import="ua.training.constant.Attributes" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="<%=session.getAttribute(Attributes.LOCALE)%>"/>
<fmt:setBundle basename="localization" var="msg"/>




<html>
<head>
    <title><fmt:message bundle="${msg}" key="LogOut"/></title>
</head>
<body>
<div class="container-fluid">
    <form method="post">
        <input type="hidden" name="command" value="LogOut"/>
        <button type="submit"><fmt:message bundle="${msg}" key="LogOut"/></button>
    </form>
        <c:if test="${not tickets.isEmpty()}" >
            <table class="table">
                <caption><fmt:message bundle="${msg}" key="ticket"/></caption>
                <tr>
                    <th>place</th>
                </tr>
                <c:forEach items="${tickets}" var="ticket"><tr>
                    <td>${ticket.getPlace()}</td>
                    <td>
                        <form method="post">
                        <button name="submit" id="buttonId" value="${ticket.id}" type="submit"  >Confirm</button>
                        <input type="hidden" name="command" value="confirm" />
                        <input type="hidden" name="flightId" value="${ticket.getFlightId()}"/>
                        <input type="hidden" name="cost" value="${cost}"/>
                        <input type="hidden" name="allTicketCount" value="${allTicketCount}"/>
                        <input type="hidden" name="ticketId" value="${ticket.id}"/>
                        <input type="hidden" name="date" value="${date}">
                        <input type="hidden" name="place" value="${ticket.getPlace()}"/>
                        </form>
                    </td>
                </tr></c:forEach>
            </table>
        </c:if>
        <c:if test="${tickets.isEmpty()}">
            <h2 class="text-center"><fmt:message bundle="${msg}" key="noTickets"/></h2>
        </c:if>
    </form>
    <c:if test="${error!=null}">${error}</c:if>
</div>
</body>
</html>
