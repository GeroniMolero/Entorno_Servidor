<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.model.Nomina" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Listado de Nóminas</title>
    <link rel="stylesheet" href="styles/style.css">
</head>
<body>
    <h2>Listado de Nóminas</h2>
    <a href="index.jsp">Volver</a>
    <table>
        <thead>
            <tr><th>DNI</th><th>Sueldo (€)</th></tr>
        </thead>
        <tbody>
            <%
                List<Nomina> listaNominas = (List<Nomina>) request.getAttribute("listaNominas");
                if (listaNominas != null) {
                    for (Nomina n : listaNominas) {
            %>
                        <tr>
                            <td><%= n.getDni() %></td>
                            <td><%= String.format("%.2f", n.getSueldo()) %></td>
                        </tr>
            <%
                    }
                } else {
            %>
                    <tr><td colspan="2">No hay nóminas registradas.</td></tr>
            <%
                }
            %>
        </tbody>
    </table>
</body>
</html>
