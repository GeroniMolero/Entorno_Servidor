<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<body>
	<h2>Gestiona tus contactos telefónicos</h2>

	<c:choose>

		<c:when test="${contactList == null || contactList.isEmpty()}">
			<p>No tienes contactos en tu agenda</p>
			<a href="new-contact.jsp">Añadir un contacto</a>
		</c:when>

		<c:otherwise>
			<p>Tienes <c:out value="${contactList.size()}" /> contactos guardados.</p>
			<p><a href="new-contact.jsp">Añadir un contacto</a></p>
			<p><a href="ContactServlet?option=list">Ver tu lista de contactos</a></p>
			<p><a href="delete-confirmation.jsp">Borrar contactos</a></p>
		</c:otherwise>
	</c:choose>

</body>
</html>
