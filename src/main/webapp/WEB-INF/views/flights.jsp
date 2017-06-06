
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<html>
<head>
    <title>flights </title>
</head>
<body>
<c:if test="${sessionScope.user.userRole.roleName=='client'}">
<div class="container-fluid">
    <form method="post">

        <label for="from-box" >from</label><br/>
        <input name="from" id="from-box" type="text" size="40"/><br/>
        <label for="to-box" >to</label><br/>
        <input name="to" id="to-box" type="text" size="40"/><br/>
        <input type="hidden" name="command" value="search"/>
        <input type="submit" value="submit"/>
    </form>

    <c:if test="${not flights.isEmpty()}" >
        <table class="table">
            <caption>Flights</caption>
            <tr>
                <th>From</th>
                <th>To</th>
                <th>date </th>
                <th>price </th>
                <th>count </th>
            </tr>
            <c:forEach items="${flights}" var="flight"><tr>
                <form method="post">
                <td>${flight.getDeparture()}</td>
                <td>${flight.getDestination()}</td>
                <td>${flight.getDate()}</td>
                <td>${flight.getStartCost()}</td>
                <td>
                    ${flight.getTicketCount()}
                </td>
                <td>
                    <input type="hidden" name="command" value="tickets" />
                    <input type="hidden" name="cost" value="${flight.getStartCost()}"/>
                    <input type ="hidden" name="allTicketCount" value="${flight.getTicketCount()}">
                    <input type="hidden" name="flight_id" value="${flight.getId()}"/>
                    <input type="hidden" name="date" value="${flight.getDate()}"/>
                    <button type="submit"  >Show Ticket</button>
                </td>
                </form>
            </tr></c:forEach>
        </table>
    </c:if>
    <c:if test="${flights.isEmpty()}">
        <h2 class="text-center">noFlight</h2>
    </c:if>
    </form>
    <form method="post">
        <input type="hidden" name="command" value="userTicket"/>
        <button type="submit">user tickets</button>
    </form>
    <form method="post">
        <input type="hidden" name="command" value="addWalletRedirection"/>
        <button type="submit" >add  Wallet</button>
    </form>
    </c:if>
    <c:if test="${error!=null}">${error}</c:if>
    </div>

</body>
</html>
