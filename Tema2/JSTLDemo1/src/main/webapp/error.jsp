<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Oups! Se produjo un error</title>
</head>
<body>
	<!-- Podemos acceder directamente a un atributo del objeto request  -->
	<h2>Se produjo un error: <c:out value="${error}"/></h2>
</body>
</html>