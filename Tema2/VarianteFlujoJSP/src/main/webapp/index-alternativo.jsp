<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>Alta de usuarios</title>
</head>
<body>
	
	<form action="NewUserServletAlternativo" method="POST">
		<label>Escribe el nombre del nuevo usuario:</label>
	    <input type="text" name="user" placeholder="Escribe el nombre aquí..."/>
	    <input type="submit"/>
	</form>
		
	<% String result = (String) request.getAttribute("result");
	   if(result!=null && result.equals("OK")) { %>  
		<p style="color:blue;">Usuario dado de alta correctamente...</p>
		
	<% } else if(result!=null && result.equals("ERROR")) { %>
		<p style="color:red;">Error: <%= request.getAttribute("description") %></p>
				
	<% } %>
</body>
</html>