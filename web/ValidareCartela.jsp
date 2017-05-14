<%--
  Created by IntelliJ IDEA.
  User: bobby
  Date: 12-05-2017
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MetroExpress - Valideaza cartela</title>
</head>
<body>

<form action="ValidareCartela" method="post">
    Introduceti seria cartelei de metrou: <input type="text" name="seria">
    <input type="submit" value="Valideaza">
</form>

<p>${message}</p>

<a href="index.jsp">Back</a>

</body>
</html>
