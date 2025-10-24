<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Borrar agenda</title>
</head>
<body>
	<form action="ContactServlet?option=delete" method="POST">
		<h2>Si borras los contactos no los podrás recuperar posteriormente</h2>
		<input type="submit" value="Borrar"/>
	</form>
	<p><a href="ContactServlet?option=index">Volver a la página principal</a></p>
</body>
</html>