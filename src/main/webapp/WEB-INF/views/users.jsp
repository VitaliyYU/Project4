<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${not users.isEmpty()}" >
    <table class="table">
        <caption>Users</caption>
        <tr>
            <th>Login</th>
            <th>Name</th>
            <th>Surname </th>
            <th>Role </th>
        </tr>
        <c:forEach items="${users}" var="user"><tr>
             <form method="post">
            <td>${user.id}</td>
            <td>${user.getLogin()}</td>
            <td>${user.getName()}</td>
            <td>${user.getSurname()}</td>
            <td>${user.getUserRole().roleName}</td>

            <td>
                <input type="hidden" name="command" value="deleteUser" />
                <input type="hidden" name="userId" value="${user.id}"/>
                <button  type="submit"  >delete user</button>
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
    <button type="submit">add Flight</button>
</form>
<form method="post">
    <input  type="hidden" name="command" value="refillRedirect">
    <button type="submit">Refill</button>
</form>
<c:if test="${error!=null}">${error}</c:if>
</body>
</html>
