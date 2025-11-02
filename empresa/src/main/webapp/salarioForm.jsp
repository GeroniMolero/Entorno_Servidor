<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Buscar salario</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <h2>Buscar salario por DNI</h2>
    <form action="EmpleadosController" method="get">
        <input type="hidden" name="action" value="buscar" />
        <label for="dni">DNI:</label>
        <input type="text" id="dni" name="dni" required />
        <input type="submit" value="Buscar" />
    </form>
    <p><a href="index.jsp">Volver</a></p>
</body>
</html>
