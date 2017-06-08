<%@ page import="ua.training.constant.Attributes" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="<%=session.getAttribute(Attributes.LOCALE)%>"/>
<fmt:setBundle basename="localization" var="msg"/>



<html>
<head>
    <title><fmt:message bundle="${msg}" key="title"/></title>
</head>
<body>

<form name="registration" method="post"  >
    <label for="name-box" ><fmt:message bundle="${msg}" key="name"/></label><br/>
    <input name="name" id="name-box" type="text" size="40"/><br/>
    <label for="surname-box" ><fmt:message bundle="${msg}" key="surname"/></label><br/>
    <input name="surname" id="surname-box" type="text" size="40"/><br/>

    <label for="username-box" ><fmt:message bundle="${msg}" key="login"/></label><br/>
    <input name="login" id="username-box" type="text" size="40"/><br/>
    <label for="username-box" ><fmt:message bundle="${msg}" key="password"/></label><br/>
    <input name="password" id="password-box" type="password" size="40"/><br/>
    <label for="confirm-password-box" ><fmt:message bundle="${msg}" key="confirmPassword"/></label><br/>
    <input id="confirm-password-box" type="password" size="40"/><br/>
    <input type="hidden" name="command" value="registration"/>
    <button type="submit"><fmt:message bundle="${msg}" key="submit"/></button>
</form>

<br/>
<c:if test="${error!=null}">${error}</c:if>
</body>
</html>