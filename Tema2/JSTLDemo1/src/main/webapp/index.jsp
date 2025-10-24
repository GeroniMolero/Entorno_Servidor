<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!-- Establecemos el valor de una variable -->
<c:set var = "ticketPrice" value = "5"/>
<html>
<body>
<h2>Compra tu entrada al Museo online</h2>
<form action="TicketServlet" method="post">
	<label>Indica tus datos personales:</label>
	<ul>
		<li><label>Nombre:</label>
		    <input type="text" name="firstName" required/>
		</li>
		<li><label>Apellidos:</label>
		    <input type="text" name="lastName" required/>
		</li>
		<li><label>DNI:</label>
		    <input type="text" name="id" required/>
		</li>
	</ul>
	<label>Indica el número de entradas en cada caso:</label><br/>
	<ul>
		<!-- Utilizamos el valor de la variable -->
		<li><label>Adulto (18-64 años) - <c:out value="${ticketPrice}"/> EUR por entrada:</label>
		    <input type="number" name="adult" value="0" min="0" required/>
		</li>
		<li><label>Menor de edad (0-17 años) - Gratis:</label>
		    <input type="number" name="younger" value="0" min="0" required/>
		</li>
		<li><label>Tercera edad (65 o más años) - <c:out value="${ticketPrice*0.5}"/> EUR por entrada:</label>
		    <input type="number" name="senior" value="0" min="0" required/>
		</li>
	</ul>
	<input type="submit"/>
</form>
</body>
</html>
