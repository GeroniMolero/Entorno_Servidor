<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Session Info</title>
</head>
<body>
	<h2>Usuario, identifícate</h2>
	<form action="SessionServlet?option=login" method="POST">
		<label>Usuario (vale cualquiera): </label>
		<input type="text" name="userName" required/>
		<br/>
		<label>Contraseña (debe tener 6 o más caracteres): </label>
		<input type="password" name="password" required/>
		<br/>
		<input type="submit"/>
	</form>
</body>
</html>