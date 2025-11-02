<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.model.Empleado" %>
<%@ page import="com.model.Nomina" %>
<html>
<head>
    <title>Editar Empleado</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
    <%
        Empleado e = (Empleado) request.getAttribute("empleado");
        if (e == null) {
    %>
        <p>No se encontró el empleado.</p>
        <p><a href="../index.jsp">Volver</a></p>
    <%
        } else {
    %>
        <h2>Editar datos de <%= e.getNombre() %></h2>
        <form action="../EmpleadosController" method="get">
            <input type="hidden" name="action" value="actualizar" />
            <input type="hidden" name="dni" value="<%= e.getDni() %>" />

            <label>Nombre:</label>
            <input type="text" name="nombre" value="<%= e.getNombre() %>"><br><br>

            <label>Sexo:</label>
            <input type="text" name="sexo" value="<%= e.getSexo() %>"><br><br>

            <label>Categoría:</label>
            <input type="number" name="categoria" value="<%= e.getCategoria() %>"><br><br>

            <label>Años trabajados:</label>
            <input type="number" name="anyos" value="<%= e.getAnyos() %>"><br><br>

            <p><b>Sueldo actual:</b> <%= new Nomina().sueldo(e) %> € (calculado automáticamente)</p>

            <input type="submit" value="Guardar cambios" />
        </form>
        <p><a href="../EmpleadosController?action=listar">Volver</a></p>
    <%
        }
    %>
</body>
</html>
