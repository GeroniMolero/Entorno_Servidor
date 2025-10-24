<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>Alta de usuarios</title>
</head>
<body>
	<!-- La vista va a recoger el objeto que estaba almacenado como atributo 
	     dentro del objeto request. -->
	<h2>Error: <%= request.getAttribute("description") %></h2>
	<p><a href="index.jsp">Volver</a></p>
</body>
</html>