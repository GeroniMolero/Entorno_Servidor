<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>Alta de usuarios</title>
</head>
<body>
	<form action="NewUserServlet" method="POST">
		<label>Escribe el nombre del nuevo usuario:</label>
	    <input type="text" name="user" placeholder="Escribe el nombre aquí..."/>
	    <input type="submit"/>
	</form>
</body>
</html>