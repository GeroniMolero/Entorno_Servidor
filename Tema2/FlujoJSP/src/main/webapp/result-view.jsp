<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>Alta de usuarios</title>
</head>
<body>
	<h1>Los usuarios dados de alta son...</h1>
	<ul>
		<%
		// La vista va a recoger el objeto que estaba almacenado como atributo
		// en el objeto request. Dado que getAttribute devuelve un Object,
		// es necesario hacer el casting explícito
		List<String> list = (List<String>) request.getAttribute("userlist"); 
		
		for(String elem: list) { %>
			<li><%=elem%></li>
		<% } %>
	</ul>
	<p><a href="index.jsp">Volver</a></p>
</body>
</html>