<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Resultados de búsqueda</title>
    <link rel="stylesheet" href="<c:url value='/styles/global.css'/>">
</head>
<body>
<header>
    <h1>Resultados de búsqueda</h1>
</header>

<main>
    <section class="tabla-container">
        <c:if test="${empty listaEmpleados}">
            <p>No se encontraron empleados con los criterios indicados.</p>
        </c:if>

        <c:if test="${not empty listaEmpleados}">
            <table class="tabla-dark">
                <thead>
                    <tr>
                        <th>DNI</th>
                        <th>Nombre</th>
                        <th>Sexo</th>
                        <th>Categoría</th>
                        <th>Años</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="e" items="${listaEmpleados}">
                        <tr>
                            <td>${e.dni}</td>
                            <td>${e.nombre}</td>
                            <td>${e.sexo}</td>
                            <td>${e.categoria}</td>
                            <td>${e.anyos}</td>
                            <td>
                                <a href="<c:url value='/EmpleadosController?action=editar&dni=${e.dni}'/>" class="btn">Editar</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </section>

    <div class="acciones">
        <a href="<c:url value='/EmpleadosController?action=buscarForm'/>" class="btn-secundario">Nueva búsqueda</a>
        <a href="<c:url value='/index.jsp'/>" class="btn-secundario">Volver al inicio</a>
    </div>
</main>

<footer>
    <p>© 2025 Gestión de Nóminas</p>
</footer>
</body>
</html>
