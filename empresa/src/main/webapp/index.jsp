<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Gestión de Nóminas</title>
    <link rel="stylesheet" href="<c:url value='/styles/global.css'/>">
</head>
<body>
    <header class="header-main">
        <h1>Gestión de Nóminas</h1>
        <p class="subtitle">Sistema de Empleados y Sueldos</p>
    </header>

    <main>
        <section class="menu-container">
            <div class="menu-card">
                <h2>Opciones principales</h2>

                <nav class="menu-links">
                    <a href="<c:url value='/EmpleadosController?action=listar'/>" class="btn-menu">Ver todos los empleados</a>
                    <a href="<c:url value='/EmpleadosController?action=formSalario'/>" class="btn-menu">Consultar salario</a>
                    <a href="<c:url value='/EmpleadosController?action=listar'/>" class="btn-menu">Modificar empleado</a>
                    <a href="<c:url value='/NominasController?action=listarNominas'/>" class="btn-menu">Ver Nóminas</a>
                </nav>
            </div>
        </section>
    </main>

    <footer>
        <p>© 2025 Gestión de Nóminas — Proyecto JSP / Maven / Tomcat</p>
    </footer>
</body>
</html>
