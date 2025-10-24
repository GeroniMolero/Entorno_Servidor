<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="header.jsp"%>

<div class="main-container">
	<div class="container">
		<div class="main-content">
			<h2>
				¡Oups! <c:out value="${error}" />
			</h2>
		</div>
	</div>
</div>
<%@ include file="footer.jsp"%>