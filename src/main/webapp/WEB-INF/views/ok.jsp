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
    <title><fmt:message bundle="${msg}" key="Ok"/></title>
</head>
<body>
<form method="post">
    <input type="hidden" name="command" value="LogOut"/>
    <button type="submit"><fmt:message bundle="${msg}" key="LogOut"/></button>
</form>
<fmt:message bundle="${msg}" key="totalCost"/> =${cost}, <fmt:message bundle="${msg}" key="congratulate"/>!
<form method="post">
<input type="hidden" name="command" value="showFlight">
<button type="submit"><fmt:message bundle="${msg}" key="toFlights"/></button>
</form>
</body>
</html>
