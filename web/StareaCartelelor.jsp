<%--
  Created by IntelliJ IDEA.
  User: bobby
  Date: 15-05-2017
  Time: 22:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>MetroExpress - Starea Cartelelor</title>
    <style> td, tr, th {
        border: 1px solid black
    }

    </style>
</head>
<body>

<form action="StareaCartelelor" method="get">
    <input type="checkbox" name="abonamentLunar"> Abonament Lunar
    <br>
    <input type="checkbox" name="abonamentZi"> Abonament Zi
    <br>
    <input type="checkbox" name="cartela"> Cartela
    <br>
    <input type="submit" value="Submit">
</form>

<a href="index.jsp">Back</a>

<%--<c:if test = "${tableAbonamenteLunare.size()}">--%>
<h2>Abonamente Lunare</h2>
<table style="border: 1px solid black">
    <tr>
        <th>Seria</th>
        <th>Validare Initiala</th>
        <th>Data Expirarii</th>
        <th>Ultima Validare</th>
    </tr>
    <c:forEach var="element" items="${tableAbonamenteLunare}">
        <tr>
            <td>${element.getSerial()}</td>
            <td>${element.getValidareInitiala()}</td>
            <td>${element.getDataExpirarii()}</td>
            <td>${element.getUltimaValidare()}</td>
        </tr>
    </c:forEach>
</table>

<h2>Abonamente Zi</h2>
<table style="border: 1px solid black">
    <tr>
        <th>Seria</th>
        <th>Validare Initiala</th>
        <th>Data Expirarii</th>
        <th>Ultima Validare</th>
    </tr>
    <c:forEach var="element" items="${tableAbonamenteZi}">
        <tr>
            <td>${element.getSerial()}</td>
            <td>${element.getValidareInitiala()}</td>
            <td>${element.getDataExpirarii()}</td>
            <td>${element.getUltimaValidare()}</td>
        </tr>
    </c:forEach>
</table>

<h2>Cartele</h2>
<table style="border: 1px solid black">
    <tr>
        <th>Seria</th>
        <th>Calatorii Initiale</th>
        <th>Calatorii Ramase</th>
        <th>Validare Initiala</th>
        <th>Data Expirarii</th>
    </tr>
    <c:forEach var="element" items="${tableCartele}">
        <tr>
            <td>${element.getSerial()}</td>
            <td>${element.getCalatoriiInitiale()}</td>
            <td>${element.getCalatoriiRamase()}</td>
            <td>${element.getValidareInitiala()}</td>
            <td>${element.getDataExpirarii()}</td>
        </tr>
    </c:forEach>
</table>
<%--</c:if>--%>

</body>
</html>
