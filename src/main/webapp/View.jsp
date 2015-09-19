<%@ page language="java" pageEncoding="UTF-8" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title></title>
</head>
<body>
<a href="${pageContext.servletContext.contextPath}/CreatePlayer.jsp">Добавить игрока</a>

<form action="${pageContext.servletContext.contextPath}/upload" enctype="multipart/form-data" method="POST">
    <input type="file">
    <input type="SUBMIT">
</form>

<table border="1">
    <tr>
        <td>Команда  Игрок  Зарплата  Роль</td>
    </tr>
    <c:forEach items="${players}" var="player" varStatus="status">
        <tr align="top">
            <td>${player.team} ${player.name} ${player.salary} ${player.position}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
