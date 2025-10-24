<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Session Demo</title>
</head>
<body>
<h2>Demo de uso de sesiones</h2>
<p>
	Pincha en el siguiente <a href="SessionServlet?option=noLogin">enlace</a> para crear una sesión sin hacer login
</p>
<br/>
<p>
	Pincha en el siguiente <a href="SessionServlet?option=counter">enlace</a> para crear una sesión que almacena un contador de visitas de cada usuario
</p>
<br/>
<p>
	Pincha en el siguiente <a href="login-form.jsp">enlace</a> para crear una sesión haciendo un login de usuario
</p>
</body>
</html>