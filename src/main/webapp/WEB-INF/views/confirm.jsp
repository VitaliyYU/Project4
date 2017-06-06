<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Confirm</title>
</head>
<body>
<table class="table">
    <caption>Tickets</caption>
    <form method="post">
    <tr>
        <th>baggage</th>
        <th>priority boarding</th>
        <th>priority registration</th>
    </tr>
        <td><input name="baggage" type="text"/>+${0.05*cost}for kg </td>
        <td>
            <input type="checkbox" name="boarding" value="true">boarding=${cost*0.2}</td>
        </td>
        <td>
            <input type="checkbox" name="registration" value="true">registration=${cost*0.3}</td>
        </td>
    <td>${cost}</td>
        <td>
            <input type="hidden" name="ticketId" value="${ticketId}"/>
            <input type="hidden" name="cost" value="${cost}"/>
            <input type="hidden" name="place" value="${place}"/>
            <input type="hidden" name="command" value="buy">
            <button type="submit"  >Confirm</button>
        </td>
    </tr>
    </form>
    <c:if test="${error!=null}">${error}</c:if>
</table>
</body>
</html>
