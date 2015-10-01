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
            <a href="${pageContext.servletContext.contextPath}/view?sort=${key eq 'team' ? (sort eq 'abc' ? 'cba' : 'abc') : 'abc'}&key=team">Команда</a>
        </td>
        <td>
            <c:if test="${key ne 'name'|| sort eq 'cba'}">
                <a href="${pageContext.servletContext.contextPath}/view?sort=abc&key=name">Игрок</a>
            </c:if>
            <c:if test="${key eq 'name' && sort eq 'abc'}">
                <a href="${pageContext.servletContext.contextPath}/view?sort=cba&key=name">Игрок</a>
            </c:if>
        </td>
        <td>
            <c:if test="${key ne 'salary'|| sort eq 'cba'}">
                <a href="${pageContext.servletContext.contextPath}/view?sort=abc&key=salary">Зарплата</a>
            </c:if>
            <c:if test="${key eq 'salary' && sort eq 'abc'}">
                <a href="${pageContext.servletContext.contextPath}/view?sort=cba&key=salary">Зарплата</a>
            </c:if>
        </td>
        <td>
            <c:if test="${key ne 'position'|| sort eq 'cba'}">
                <a href="${pageContext.servletContext.contextPath}/view?sort=abc&key=position">Роль</a>
            </c:if>
            <c:if test="${key eq 'position' && sort eq 'abc'}">
                <a href="${pageContext.servletContext.contextPath}/view?sort=cba&key=position">Роль</a>
            </c:if>

        </td>
    </tr>
    <c:forEach items="${players}" var="player" varStatus="status">
        <tr align="top">
            <td>${player.team}</td> <td> ${player.name} </td> <td> ${player.salary} </td> <td> ${player.position}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
