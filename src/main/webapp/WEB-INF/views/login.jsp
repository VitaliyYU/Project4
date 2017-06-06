
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:requestEncoding value="UTF-8" />

<html>
<head>
    <title>Login2 </title>
</head>
<body>
<form method="post" action="/airport" >
    <input type="hidden" name="command" value="login"/>
    <label for="username-box" class="control-label">input login</label><br/>
    <input id="username-box" name="login" type="text" size="40"/><br/>
    <label for="username-box" class="control-label">input password</label><br/>
    <input id="password-box" name="password" type="password" size="40"/><br/>

    <button type="submit"  >submit</button>
</form>
<form name="SignUp" method="post" action="/airport/">
    <input type="hidden" name="command" value="toRegister">
    <button type="submit" >SignUp</button>
<br/>
</form>
<c:if test="${error!=null}">${error}</c:if>
</body>
</html>
