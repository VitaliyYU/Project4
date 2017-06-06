<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${not wallets.isEmpty()}" >
    <table class="table">
        <caption>Wallets</caption>
        <tr>
            <th>Code</th>
            <th>Count</th>
        </tr>
        <c:forEach items="${wallets}" var="wallet"><tr>
            <form method="post">
                <td>${wallet.code}</td>
                <td>${wallet.count}</td>
                <td>
                    <input type="hidden" name="command" value="refill" />
                    <input type="hidden" name="code" value="${wallet.code}"/>
                    <button  type="submit"  >refil wallet</button>
                </td>
            </form>
        </tr>
        </c:forEach>
    </table>
</c:if>
<c:if test="${wallets.isEmpty()}">
    <h2 class="text-center">noWallets</h2>
</c:if>
</form>
<c:if test="${error!=null}">${error}</c:if>
</body>
</html>
