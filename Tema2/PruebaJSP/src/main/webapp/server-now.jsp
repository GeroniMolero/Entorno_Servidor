<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.time.LocalTime"
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Week Day</title>
</head>
<body>
	<h2>Hora del servidor con JSP</h2>
	<%
		String ahora = LocalTime.now().toString();
	%>
	<p>Según el reloj del servidor, ahora mismo son las <%=ahora%> </p>
</body>
</html>

