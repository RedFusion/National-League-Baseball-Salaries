<%@ page language="java" pageEncoding="UTF-8" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title></title>
</head>
<body>
<a href="${pageContext.servletContext.contextPath}/CreatePlayer.jsp">Добавить игрока</a>

<form action="${pageContext.servletContext.contextPath}/upload" enctype="multipart/form-data" method="POST">
    <input type="file" name="file">
    <input type="SUBMIT">
</form>

<table border="1">
    <tr>
        <td>
            <a href="${pageContext.servletContext.contextPath}/view?sort=${key eq 'team'?(sort eq 'abc'?'cba':'abc') :'abc'}&key=team">Команда</a>
        </td>
        <td>
            <a href="${pageContext.servletContext.contextPath}/view?sort=${key eq 'name' ? (sort eq 'abc'?'cba':'abc') :'abc'}&key=name">Игрок</a>
        </td>
        <td>
            <a href="${pageContext.servletContext.contextPath}/view?sort=${key eq 'salary' ? (sort eq 'abc' ? 'cba' : 'abc') : 'abc'}&key=salary">Зарплата</a>
        </td>
        <td>
            <a href="${pageContext.servletContext.contextPath}/view?sort=${key eq 'position' ? (sort eq 'abc' ? 'cba':'abc'):'abc'}&key=position">Роль</a>
        </td>
    </tr>
    <c:forEach items="${players}" var="player" varStatus="status">
        <tr align="top">
            <td>${player.team}</td>
            <td> ${player.name} </td>
            <td> ${player.salary} </td>
            <td> ${player.position}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>