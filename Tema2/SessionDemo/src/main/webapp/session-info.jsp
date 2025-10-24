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
	<p> 
		<b>Session ID: </b> <c:out value="${sessionId}"/> 
	</p>
	<p> 
		<b>Fecha-hora de creación:</b> <c:out value="${createDate}"/> 
	</p> 
	<p>
		<b>Fecha-hora de último acceso:</b> <c:out value="${lastAccessedDate}"/> 
	</p>
	<p>
		Recarga la página con el siguiente <a href="SessionServlet?option=noLogin">enlace</a> 
		y observa cómo se modifica la fecha del último acceso
	</p>
	<p>
		Invalida la sesión (es como hacer un logout) con el siguiente enlace <a href="SessionServlet?option=invalidate">enlace</a>
	</p>
</body>
</html>