<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Confirmación de la compra</title>
</head>
<body>
	<h2>Resumen de la compra</h2>
	<!-- Accedemos a la propiedad numTickets (mediante una llamada implícita al 
	     método getNumTickets) del objeto identificado con la cadena "cart" -->
	<p>Vas a comprar <c:out value="${cart.numTickets}"/> entradas 
	
	<!-- También accedemos al parámetro firstName del objeto request con la siguiente notación -->
	
	   a nombre de <c:out value="${param['firstName']}"/> <c:out value="${param['lastName']}"/> 
	   
	   con DNI <c:out value="${param['id']}"/>  
	</p>
	<table border="solid">
		<tr>
			<th>Categoría</th>
			<th>Unidades</th>
			<th>Importe</th>
		</tr>
		<tr>
			<td>Adultos</td>
			<td><c:out value="${cart.numAdult}"/></td>
			<td><c:out value="${cart.totalAdult}"/> euros</td>
		</tr>
		<tr>
			<td>Menores de edad</td>
			<td><c:out value="${cart.numYounger}"/></td>
			<td><c:out value="${cart.totalYounger}"/> euros</td>
		</tr>
		<tr>
			<td>Tercera edad</td>
			<td><c:out value="${cart.numSenior}"/></td>
			<td><c:out value="${cart.totalSenior}"/> euros</td>
		</tr>
		<tr>
			<td></td>
			<th>TOTAL</th>
			<!-- También podemos hacer cálculos -->
			<th><c:out value="${cart.totalSenior + cart.totalYounger + cart.totalAdult}"/> euros</th>
		</tr>
	</table>
	<p><a href="index.jsp">Volver atrás</a></p>
</body>
</html>