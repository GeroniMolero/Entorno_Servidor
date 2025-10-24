<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>Alta de usuarios</title>
</head>
<body>
	<% String result = (String) request.getAttribute("result");
	   if(result==null || result.isEmpty()) { %> 
		<form action="NewUserServlet" method="POST">
			<label>Escribe el nombre del nuevo usuario:</label>
		    <input type="text" name="user" placeholder="Escribe el nombre aquí..."/>
		    <input type="submit"/>
		</form>
		
	<% } else if(result.equals("OK")) { %>
		<h1>Los usuarios dados de alta son...</h1>
		<ul>
		<% 
		   List<String> list = (List<String>) request.getAttribute("userlist"); 
		   for(String elem: list) { %>
			  <li><%=elem%></li>
		<% } %>
		</ul>
		<p><a href="index.jsp">Volver</a></p>
		
	<% } else if(result.equals("ERROR")) { %>
		<h2>Error: <%= request.getAttribute("description") %></h2>
		<p><a href="index.jsp">Volver</a></p>
		
	<% } %>
</body>
</html>