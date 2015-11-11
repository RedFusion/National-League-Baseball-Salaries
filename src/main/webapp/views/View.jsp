<%@ page language="java" pageEncoding="UTF-8" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
    <title></title>
</head>
<body>
<a href="${pageContext.servletContext.contextPath}/views/CreatePlayer.jsp">Добавить игрока</a>

<form action="${pageContext.servletContext.contextPath}/api/view/upload"
      enctype="multipart/form-data"
      method="POST">
    <input type="file" name="file">
    <input type="submit" value="Upload">
</form>

<table border="1">
    <tr>
        <td>
            <a href="${pageContext.servletContext.contextPath}/api/view/list?sort=${key eq 'team'?(sort eq 'abs'?'desc':'abs') :'abs'}&key=team">Команда</a>
        </td>
        <td>
            <a href="${pageContext.servletContext.contextPath}/api/view/list?sort=${key eq 'name' ? (sort eq 'abs'?'desc':'abs') :'abs'}&key=name">Игрок</a>
        </td>
        <td>
            <a href="${pageContext.servletContext.contextPath}/api/view/list?sort=${key eq 'salary' ? (sort eq 'abs' ? 'desc' : 'abs') : 'abs'}&key=salary">Зарплата</a>
        </td>
        <td>
            <a href="${pageContext.servletContext.contextPath}/api/view/list?sort=${key eq 'position' ? (sort eq 'abs' ? 'desc':'abs'):'abs'}&key=position">Роль</a>
        </td>
    </tr>
    <c:forEach items="${players}" var="player" varStatus="status">
        <tr align="top">
            <td>${player.team}</td>
            <td> ${player.name} </td>
            <td> ${player.salary} </td>
            <td> ${player.position}</td>
            <td>
                &nbsp; <a href="${pageContext.servletContext.contextPath}/edit?id=${player.id}">Редактировать</a>
                &nbsp; <a href="${pageContext.servletContext.contextPath}/delete?id=${player.id}">Удалить</a> &nbsp;
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>