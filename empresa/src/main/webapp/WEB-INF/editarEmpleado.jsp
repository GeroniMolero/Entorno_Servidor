<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar Empleado</title>
    <link rel="stylesheet" href="<c:url value='/styles/global.css'/>">
</head>
<body>
    <header>
        <h1>Editar Empleado</h1>
    </header>

    <main>
        <section class="form-container">
            <c:choose>
                <c:when test="${empty empleado}">
                    <p class="mensaje">No se encontró el empleado especificado.</p>
                    <div class="acciones">
                        <a href="<c:url value='/EmpleadosController?action=listar'/>" class="btn-secundario">Volver</a>
                    </div>
                </c:when>

                <c:otherwise>
                    <form action="<c:url value='/EmpleadosController'/>" method="get" class="form-dark">
                        <input type="hidden" name="action" value="actualizar" />
                        <input type="hidden" name="dni" value="${empleado.dni}" />

                        <label for="nombre">Nombre:</label>
                        <input type="text" id="nombre" name="nombre" value="${empleado.nombre}" required>

                        <label for="sexo">Sexo:</label>
                        <input type="text" id="sexo" name="sexo" value="${empleado.sexo}" maxlength="1" required>

                        <label for="categoria">Categoría:</label>
                        <input type="number" id="categoria" name="categoria" value="${empleado.categoria}" min="1" max="10" required>

                        <label for="anyos">Años trabajados:</label>
                        <input type="number" id="anyos" name="anyos" value="${empleado.anyos}" min="0" required>

                        <c:if test="${not empty salario}">
                            <p><b>Sueldo actual:</b> 
                                <fmt:formatNumber value="${salario}" type="currency" currencySymbol="€" />
                            </p>
                        </c:if>

                        <input type="submit" value="Guardar cambios" class="btn">
                    </form>

                    <div class="acciones">
                        <a href="<c:url value='/EmpleadosController?action=listar'/>" class="btn-secundario">Volver</a>
                    </div>
                </c:otherwise>
            </c:choose>
        </section>
    </main>

    <footer>
        <p>© 2025 Gestión de Nóminas</p>
    </footer>
</body>
</html>
