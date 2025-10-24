<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="header.jsp"%>

<div class="main-container">
	<div class="container">
		<div class="main-content">
			<h1>Nuevo cliente</h1>
			<form action="CustomerController?option=newCustomer" method="POST">
				<div class="customer-form-line">
					<label for="name">Nombre:</label> <input type="text" id="name"
						name="name" required />
				</div>
				<div class="customer-form-line">
					<label for="address">Dirección postal:</label> <input type="text"
						id="address" name="address" required />
				</div>
				<div class="customer-form-line">
					<label for="website">URL sitio web:</label> <input type="url"
						id="website" name="website" placeholder="http://yoursite.com" />
				</div>
				<div class="customer-form-line">
					<label for="credit-limit">Límite inicial de crédito:</label> <input
						type="number" id="credit-limit" name="credit-limit" value="1000"
						min="0" max="10000" />
				</div>
				<input type="submit" />

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