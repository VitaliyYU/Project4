<%@ page import="ua.training.constant.Attributes" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="<%=session.getAttribute(Attributes.LOCALE)%>"/>
<fmt:setBundle basename="localization" var="msg"/>


<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post">
    <input type="hidden" name="command" value="LogOut"/>
    <button type="submit"><fmt:message bundle="${msg}" key="LogOut"/></button>
</form>
<c:if test="${not wallets.isEmpty()}" >
    <table class="table">
        <caption><fmt:message bundle="${msg}" key="wallets"/></caption>
        <tr>
            <th><fmt:message bundle="${msg}" key="code"/></th>
            <th><fmt:message bundle="${msg}" key="count"/></th>
        </tr>
        <c:forEach items="${wallets}" var="wallet"><tr>
            <form method="post">
                <td>${wallet.code}</td>
                <td>${wallet.count}</td>
                <td>
                    <input type="hidden" name="command" value="refill" />
                    <input type="hidden" name="code" value="${wallet.code}"/>
                    <button  type="submit"  ><fmt:message bundle="${msg}" key="reffil"/></button>
                </td>
            </form>
        </tr>
        </c:forEach>
    </table>
</c:if>
<c:if test="${wallets.isEmpty()}">
    <h2 class="text-center"><fmt:message bundle="${msg}" key="noWallets"/></h2>
</c:if>
</form>
<c:if test="${error!=null}">${error}</c:if>
</body>
</html>
