<%@ page import="ua.training.constant.Attributes" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="<%=session.getAttribute(Attributes.LOCALE)%>"/>
<fmt:setBundle basename="localization" var="msg"/>


<html>
<head>
    <title></title>
</head>
<body>
<c:if test="${not users.isEmpty()}" >
    <table class="table">
        <caption><fmt:message bundle="${msg}" key="users"/></caption>
        <form method="post">
            <input type="hidden" name="command" value="LogOut"/>
            <button type="submit"><fmt:message bundle="${msg}" key="LogOut"/></button>
        </form>
        <tr>
            <th><fmt:message bundle="${msg}" key="login"/></th>
            <th><fmt:message bundle="${msg}" key="name"/></th>
            <th><fmt:message bundle="${msg}" key="surname"/> </th>
            <th><fmt:message bundle="${msg}" key="role"/> </th>
        </tr>

        <c:forEach items="${users}" var="user"><tr>
             <form method="post">
            <td>${user.getLogin()}</td>
            <td>${user.getName()}</td>
            <td>${user.getSurname()}</td>
            <td>${user.getUserRole().roleName}</td>

            <td>
                <input type="hidden" name="command" value="deleteUser" />
                <input type="hidden" name="userId" value="${user.id}"/>
                <button  type="submit"  ><fmt:message bundle="${msg}" key="deleteUser"/></button>
            </td>
             </form>
        </tr>
        </c:forEach>
    </table>
</c:if>
<c:if test="${users.isEmpty()}">
    <h2 class="text-center">noUsers</h2>
</c:if>
</form>
<form method="post">
    <input  type="hidden" name="command" value="addFlightRedirection">
    <button type="submit"><fmt:message bundle="${msg}" key="addFlight"/></button>
</form>
<form method="post">
    <input  type="hidden" name="command" value="refillRedirect">
    <button type="submit"><fmt:message bundle="${msg}" key="reffil"/></button>
</form>
<c:if test="${error!=null}">${error}</c:if>
</body>
</html>
