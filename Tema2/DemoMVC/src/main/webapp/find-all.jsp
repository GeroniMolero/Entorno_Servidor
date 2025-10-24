<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="header.jsp"%>

<div class="main-container">
	<div class="container">
		<div class="main-content">
			<h1>Nuestros clientes</h1>
			<table class="customer-table">
				<thead>
					<tr>
						<th>Num. Cliente</th>
						<th>Nombre</th>
						<th>Dirección</th>
						<th>Sitio web</th>
						<th>Límite de crédito</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${customerList}">
						<tr>
							<td><c:out value="${item.customerId}" /></td>
							<td><c:out value="${item.name}" /></td>
							<td><c:out value="${item.address}" /></td>
							<td><c:out value="${item.website}" /></td>
							<td><c:out value="${item.creditLimit}" /> euros</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>

<%@ include file="footer.jsp"%>