<%@ page language="java" pageEncoding="UTF-8" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title></title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/api/view/create" method="POST">
    <table>
        <tr>
            <td align="right">Team :</td>
            <td><input type="text" name="team"></td>
        </tr>
        <tr>
            <td align="right">Name :</td>
            <td><input type="text" name="name"></td>
        </tr>
        <tr>
            <td align="right">Salary :</td>
            <td><input type="text" name="salary"></td>
        </tr>
        <tr>
            <td align="right">Position :</td>
            <td><input type="text" name="position"></td>
        </tr>

        <tr>
            <td><input type="submit" align="center" value="Add player"/></td>
        </tr>
    </table>
</form>
${team} <br>${name}<br> <br>${salary} <br>${position}
</body>
</html>