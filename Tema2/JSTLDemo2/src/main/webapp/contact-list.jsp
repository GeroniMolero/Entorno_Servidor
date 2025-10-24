<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lista de contactos</title>
</head>
<body>
	<h2>Lista de contactos:</h2>
	<c:choose>
		<c:when test="${contactList == null || contactList.isEmpty()}">
			<p>No tienes contactos en tu agenda</p>
			<a href="new-contact.jsp">Añadir un contacto</a>
		</c:when>

		<c:otherwise>
			<table border="solid">
				<tr>
					<th>Nombre</th>
					<th>Teléfono</th>
				</tr>
				<c:forEach var="elem" items="${contactList}">
					<tr><td><c:out value="${elem.name}"/></td>
					    <td><c:out value="${elem.phone}"/></td>
					</tr>
				</c:forEach>
			</table>
			<p><a href="new-contact.jsp">Añadir un contacto</a></p>
			<p><a href="ContactServlet?option=index">Volver a la página principal</a></p>
         </c:otherwise>
	</c:choose>
</body>
</html>