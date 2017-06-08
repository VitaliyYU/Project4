<%@ page import="ua.training.constant.Attributes" %>
<%@ page session="true"%>
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
<form method="post"  action="/airport/" >
    <input type="hidden" name="command" value="login"/>
    <label for="username-box" class="control-label"><fmt:message bundle="${msg}" key="login"/></label><br/>
    <input id="username-box" name="login" type="text" size="40"/><br/>
    <label for="username-box" class="control-label"><fmt:message bundle="${msg}" key="password"/></label><br/>
    <input id="password-box" name="password" type="password" size="40"/><br/>

    <button type="submit"  ><fmt:message bundle="${msg}" key="submit"/></button>
</form>
<form name="SignUp" method="post" action="/airport/">
    <input type="hidden" name="command" value="toRegister">
    <button type="submit" ><fmt:message bundle="${msg}" key="SignUp"/></button>
    <br/>
</form>
<form method="post" action="/airport/">
    <input type="hidden"  name="<%=Attributes.LOCALE%>" value="en_En"/>
    <input type="hidden" name="command" value="changeLocale"/>
    <button type="submit">En</button>
</form>
<form method="post" action="/airport/">
    <input type="hidden" name="command" value="changeLocale"/>
    <input type="hidden" name="<%=Attributes.LOCALE%>" value="ua_Ua"/>
    <button type="submit">Ua</button>
</form>

</body>
</html>
