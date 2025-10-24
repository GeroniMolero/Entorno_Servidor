<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="javax.servlet.http.HttpSession"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Session Info</title>
</head>
<body>
	<%
	// Si el usuario no se ha logado pero intenta acceder a esta página no
	// existirá el atributo "user" y podremos darle un mensaje de error
	String user = (String) session.getAttribute("user");
	if (user  == null) {
		request.setAttribute("error", "Usuario no identificado");
		RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
		rd.forward(request, response);
	} else { %> 
		<h2>Hola <c:out value="${user}"/></h2>
		<p><a href="SessionServlet?option=logout">Logout</a></p>
	<% } %>
</body>
</html>