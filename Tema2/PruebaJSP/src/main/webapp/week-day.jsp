<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.time.LocalDate, java.time.DayOfWeek"
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Week Day</title>
</head>

<body>
	<%
		DayOfWeek day = LocalDate.now().getDayOfWeek();
		
		if(day == DayOfWeek.SATURDAY || day==DayOfWeek.SUNDAY) { %>
			<h2>¡¡Hoy es fin de semana!!</h2>
	<%  } else { %>
			<h2>Ya queda menos para el finde... </h2>
	<%  } %>
</body>

</html>

