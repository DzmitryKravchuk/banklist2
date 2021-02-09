<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>EXTRA INFORMATION</title>
</head>
<body>

<h2>Extra information</h2>


<table>
    <tr>
        <th>richest user</th>
        <th>richest user's balance</th>
        <th>total balance</th>
    </tr>
        <tr>
        <td>${richestUser}</td>
        <td>${richestUserBalance}</td>
        <td>${totalBalance}</td>
    </tr>

</table>

<c:url value="/" var="users"/>
<a href="/">See all users</a>
<br>
<c:url value="/accounts" var="accounts"/>
<a href="${accounts}">See all accounts</a>
</body>
</html>