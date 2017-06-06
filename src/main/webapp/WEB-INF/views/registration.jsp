<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Registration</title>
</head>
<body>
<form name="registration" method="post"  >
    <label for="name-box" >input name</label><br/>
    <input name="name" id="name-box" type="text" size="40"/><br/>
    <label for="surname-box" >input surname</label><br/>
    <input name="surname" id="surname-box" type="text" size="40"/><br/>

    <label for="username-box" >input username</label><br/>
    <input name="login" id="username-box" type="text" size="40"/><br/>
    <label for="username-box" >input password</label><br/>
    <input name="password" id="password-box" type="password" size="40"/><br/>
    <label for="confirm-password-box" >confirm password</label><br/>
    <input id="confirm-password-box" type="password" size="40"/><br/>
    <input type="hidden" name="command" value="registration"/>
    <button type="submit">submit</button>
</form>

<br/>
<c:if test="${error!=null}">${error}</c:if>
</body>
</html>