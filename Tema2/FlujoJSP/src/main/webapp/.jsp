<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>Lista Pensamientos</title>
</head>
<body>
	<form action="ThoughtsServlet" method="POST">
		<label>Escribe una idea:</label>
	    <input type="text" name="idea" placeholder="Escribe tus ideas aquí"/>
	    <input type="submit"/>
	</form>
</body>
</html>