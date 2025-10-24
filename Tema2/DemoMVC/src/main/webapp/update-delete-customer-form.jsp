<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="header.jsp"%>

<div class="main-container">
	<div class="container">
		<div class="main-content">
			<h1>Datos del cliente</h1>
			<form method="POST">
				<div class="customer-form-line">
					<label for="id">Número de registro:</label> <input type="number" id="id"
						name="id" readonly value="<c:out value="${customer.customerId}"/>" />
				</div>
				<div class="customer-form-line">
					<label for="name">Nombre:</label> <input type="text" id="name"
						name="name" value="<c:out value="${customer.name}"/>" required />
				</div>
				<div class="customer-form-line">
					<label for="address">Dirección postal:</label> <input type="text"
						id="address" name="address" value="<c:out value="${customer.address}"/>" required />
				</div>
				<div class="customer-form-line">
					<label for="website">URL sitio web:</label> <input type="url"
						id="website" name="website" value="<c:out value="${customer.website}"/>" />
				</div>
				<div class="customer-form-line">
					<label for="credit-limit">Límite inicial de crédito:</label> <input
						type="number" id="credit-limit" name="credit-limit" value="<c:out value="${customer.creditLimit}"/>"
						min="0" max="10000" />
				</div>
				<input type="submit" formaction="CustomerController?option=updateCustomer" value="Actualizar"/>
				<input type="submit" formaction="CustomerController?option=deleteCustomer" value="Eliminar"/>

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