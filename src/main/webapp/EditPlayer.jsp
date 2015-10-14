<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/edit" METHOD="POST">
    <input type="hidden" name="id" value="${player.id}">
    <table>
        <tr>
            <td align="right">Team :</td>
            <td><input type="text" value="${player.team}" name="team"></td>
        </tr>
        <tr>
            <td align="right">Name :</td>
            <td><input type="text" value="${player.name}" name="name"></td>
        </tr>
        <tr>
            <td align="right">Salary :</td>
            <td><input type="text" value="${player.salary}" name="salary"></td>
        </tr>
        <tr>
            <td align="right">Position :</td>
            <td><input type="text" value="${player.position}" name="position"></td>
        </tr>
        <tr>
            <td><input type="submit" align="center" value="Submit"/></td>
        </tr>
    </table>
</form>
</body>
</html>