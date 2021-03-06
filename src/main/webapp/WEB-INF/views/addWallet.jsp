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
<form>
    <input type="hidden" name="command" value="LogOut"/>
    <button type="submit"><fmt:message bundle="${msg}" key="LogOut"/></button>
</form>
<form name="addWallet" method="post"  >
    <label for="name-box" ><fmt:message bundle="${msg}" key="code"/></label><br/>
    <input name="code" id="name-box" type="text" size="40"/><br/>
    <input type="hidden" name="command" value="addWallet"/>
    <button type="submit"><fmt:message bundle="${msg}" key="submit"/></button>
</form>
<c:if test="${error!=null}">${error}</c:if>
</body>
</html>
