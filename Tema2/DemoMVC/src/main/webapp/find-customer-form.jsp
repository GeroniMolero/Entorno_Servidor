<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="header.jsp"%>

<div class="main-container">
	<div class="container">
		<div class="main-content">
			<h1>Búsqueda del cliente a actualizar/eliminar</h1>
			<form action="CustomerController?option=findById" method="POST">
				<div class="customer-form-line">
					<label for="customer-id">Indica el número de cliente:</label> <input type="text" id="customer-id"
						name="customer-id" required />
				</div>
				<input type="submit" value="Buscar" />
			</form>
			<c:if test="${result != null}">
				<div class="customer-form-message">
					<c:out value="${result}" />
				</div>
			</c:if>
		</div>
	</div>
</div>

<%@ include file="footer.jsp"%>