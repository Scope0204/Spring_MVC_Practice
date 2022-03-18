<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/index.html">메인</a>
<table>
    <thead>
    <th>id</th>
    <th>username</th>
    <th>age</th>
    </thead>
    <tbody>
    <c:forEach var ="item" items="${members}">
        <tr>
            <td>${item.id}</id>
            <td>${item.username}</id>
            <td>${item.age}</id>
    </c:forEach>
    </tbody>
</table>
</body>
</html>