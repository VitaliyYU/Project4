<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vitaliy
  Date: 06.06.17
  Time: 11:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<c:if test="${not tickets.isEmpty()}" >
    <table class="table">
        <caption>Tickets</caption>
        <tr>
            <th>place</th>
            <th>departure</th>
            <th>destination</th>
            <th>date</th>
        </tr>
        <c:forEach items="${tickets}" var="ticket"><tr>
            <td>${ticket.getPlace()}</td>
            <td>
                ${flights.get(ticket.userId).departure}
            </td>
            <td>${flights.get(ticket.userId).destination}</td>
            <td>${flights.get(ticket.userId).date}</td>
        </tr></c:forEach>
    </table>
</c:if>
<c:if test="${tickets.isEmpty()}">
    <h2 class="text-center">no Ticket</h2>
</c:if>
</form>
<form method="post">
    <input type="hidden" name="command" value="showFlight">
    <button type="submit" >show flights</button>
</form>
<c:if test="${error!=null}">${error}</c:if>
</body>
</html>
