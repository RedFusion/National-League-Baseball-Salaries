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
            <c:set var="sort" value="abc"/>
            <c:set var="key" value="team"/>
            <c:if test="${key eq 'team' && sort eq 'cba'}">
                <a href="${pageContext.servletContext.contextPath}/view?sort=abc&key=team">Команда</a>
            </c:if>

            <c:if test="${key eq 'team' && sort eq 'abc'}">
                <a href="${pageContext.servletContext.contextPath}/view?sort=cba&key=team">Команда</a>
            </c:if>

            <a href="${pageContext.servletContext.contextPath}/view?sort=abc&key=name">Игрок</a>
            <a href="${pageContext.servletContext.contextPath}/view?sort=abc&key=salary">Зарплата</a>
            <a href="${pageContext.servletContext.contextPath}/view?sort=abc&key=position">Роль</a>


        </td>
    </tr>
    <c:forEach items="${players}" var="player" varStatus="status">
        <tr align="top">
            <td>${player.team} ${player.name} ${player.salary} ${player.position}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
