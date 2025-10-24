<%@ page language="java" contentType="text/html;charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Lista de Empleados</title>
</head>
<body>
<h2>Lista de Empleados</h2>

<table border="1" cellpadding="5" cellspacing="0">
    <tr>
        <th>Nombre</th>
        <th>DNI</th>
        <th>Sexo</th>
        <th>Categoria</th>
        <th>Anios</th>
        <th>Acciones</th>
    </tr>
    <tr>
            <td>${e.nombre}</td>
            <td>${e.dni}</td>
            <td>${e.sexo}</td>
            <td>${e.categoria}</td>
            <td>${e.anyos}</td>
    </tr>
</table>

<br>
<a href="index.jsp">Volver al menu</a>
</body>
</html>
