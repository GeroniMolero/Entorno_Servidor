<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Empresa - Menú Principal</title>
</head>
<body>
    <h2>Bienvenido</h2>
    <h3>Menú de Opciones</h3>

    <table border="1" cellpadding="10" cellspacing="0">
        <tr>
            <td><a href="EmpleadosController?action=listar">Mostrar empleados</a></td>
        </tr>
        <tr>
            <td><a href="EmpleadosController?action=buscar">Buscar empleado por DNI</a></td>
        </tr>
        <tr>
            <td><a href="EmpleadosController?action=editar">Modificar datos de empleado</a></td>
        </tr>
    </table>
</body>
</html>
