<%@ page import="ua.training.constant.Attributes" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="<%=session.getAttribute(Attributes.LOCALE)%>"/>
<fmt:setBundle basename="localization" var="msg"/>


<html>
<head>
    <title><fmt:message bundle="${msg}" key="LogOut"/> </title>
</head>
<body>
<c:if test="${sessionScope.user.userRole.roleName=='client'}">
<div class="container-fluid">
    <form method="get">
        <input type="hidden" name="command" value="LogOut"/>
        <button type="submit"><fmt:message bundle="${msg}" key="LogOut"/></button>
    </form>
    <form method="post">

        <label for="from-box" ><fmt:message bundle="${msg}" key="from"/></label><br/>
        <input name="from" id="from-box" type="text" size="40"/><br/>
        <label for="to-box" ><fmt:message bundle="${msg}" key="to"/></label><br/>
        <input name="to" id="to-box" type="text" size="40"/><br/>
        <input type="hidden" name="command" value="search"/>
        <button type="submit" ><fmt:message bundle="${msg}" key="submit"/></button>
    </form>

    <c:if test="${not flights.isEmpty()}" >
        <table class="table">
            <caption><fmt:message bundle="${msg}" key="flights"/></caption>
            <tr>
                <th><fmt:message bundle="${msg}" key="from"/></th>
                <th><fmt:message bundle="${msg}" key="to"/></th>
                <th><fmt:message bundle="${msg}" key="date"/> </th>
            </tr>
            <c:forEach items="${flights}" var="flight"><tr>
                <form method="post">
                <td>${flight.getDeparture()}</td>
                <td>${flight.getDestination()}</td>
                <td>${flight.getDate()}</td>
                <td>
                    <input type="hidden" name="command" value="tickets" />
                    <input type="hidden" name="cost" value="${flight.getStartCost()}"/>
                    <input type ="hidden" name="allTicketCount" value="${flight.getTicketCount()}">
                    <input type="hidden" name="flight_id" value="${flight.getId()}"/>
                    <input type="hidden" name="date" value="${flight.getDate()}"/>
                    <button type="submit"  ><fmt:message bundle="${msg}" key="showTickets"/></button>
                </td>
                </form>
            </tr></c:forEach>
        </table>
    </c:if>
    <c:if test="${flights.isEmpty()}">
        <h2 class="text-center"><fmt:message bundle="${msg}" key="noFlights"/></h2>
    </c:if>
    </form>
    <form method="post">
        <input type="hidden" name="command" value="userTicket"/>
        <button type="submit"><fmt:message bundle="${msg}" key="usersTicket"/></button>
    </form>
    <form method="post">
        <input type="hidden" name="command" value="addWalletRedirection"/>
        <button type="submit" ><fmt:message bundle="${msg}" key="addWallet"/></button>
    </form>
    </c:if>
    <c:if test="${error!=null}">${error}</c:if>
    </div>

</body>
</html>
