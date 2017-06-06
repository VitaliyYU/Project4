
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<html>
<head>
    <title>Tickets</title>
</head>
<body>
<div class="container-fluid">

        <c:if test="${not tickets.isEmpty()}" >
            <table class="table">
                <caption>Tickets</caption>
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
            <h2 class="text-center">no Ticket</h2>
        </c:if>
    </form>
    <c:if test="${error!=null}">${error}</c:if>
</div>
</body>
</html>
