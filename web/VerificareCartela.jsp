<%--
  Created by IntelliJ IDEA.
  User: bobby
  Date: 15-05-2017
  Time: 03:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MetroExpress - Valideaza cartela</title>
</head>
<body>

<form action="VerificareCartela" method="get">
    Introduceti seria cartelei de metrou pentru verificare: <input type="text" name="seria">
    <input type="submit" value="Valideaza">
</form>

<p>${message}</p>
<p>Calatorii ramase: ${message1}</p>
<p>Timp ramas ${message2}</p>

<a href="index.jsp">Back</a>

</body>
</html>
