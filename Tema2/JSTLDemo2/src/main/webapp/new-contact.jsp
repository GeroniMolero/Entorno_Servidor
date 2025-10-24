<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Nuevo contacto</title>
</head>
<body>
	<h2>Datos del contacto:</h2>
	<form action="ContactServlet?option=new" method="post">
		<ul>
			<li><label>Nombre:</label> <input type="text" name="name" required /></li>
			<li><label>Teléfono:</label> <input type="tel" name="phone"	required /></li>
		</ul>
		<input type="submit" />
	</form>
	<c:if test="${result != null && !result.isEmpty()}">
		<p><c:out value="${result}"/></p>
		<a href="ContactServlet?option=list">Mostrar la lista de contactos</a>
	</c:if>
	<p><a href="ContactServlet?option=index">Volver a la página principal</a></p>
</body>
</html>