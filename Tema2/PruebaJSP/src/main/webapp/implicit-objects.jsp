<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.time.LocalDate, java.time.DayOfWeek"
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Implicit Objects</title>
</head>

<body>
	<% 
		String host = request.getHeader("host");
		String browser = request.getHeader("user-agent");
	%>
	<h2>Tu host es <%=host%></h2>
	<h2>Tu navegador es <%=browser%></h2>
	
	<h2>
	Además puedo usar el objeto implícito 'out'
	<% out.println("y escribir en la salida como hacen los Servlets"); %>
	</h2>
</body>

</html>

