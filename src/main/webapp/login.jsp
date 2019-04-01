<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: andrii
  Date: 3/30/19
  Time: 17:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<H2>Login or register</H2>
<div class="pure-u-2-5">
    <form class="pure-form pure-form-stacked pure-u-2-5" action="<c:url value="/login"/>" method="POST">
        User name <label>
        <input type="text" name="name">
    </label>
        Password <label>
        <input type="text" name="password">
    </label>
        <input type="submit" value="Submit"/>
    </form>
</div>
</body>
</html>
