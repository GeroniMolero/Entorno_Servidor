<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="header.jsp"%>

<div class="main-container">
	<div class="container">
		<div class="main-content">
			<h1>Bienvenido al módulo de clientes</h1>
			<p>
				Esto es una demostración de cómo organizar el código con el patrón
				MVC. Para ello hemos implementado un <em>CRUD</em> (Create,
				Retrieve, Update, Delete) de una entidad que representa a un cliente
				de una empresa.
			</p>
			<p>
				Se está utilizando una base de datos <em>H2</em> que reside en
				memoria y se añade al proyecto simplemente con una librería. La
				hemos iniciado creando una tabla "CUSTOMERS" y algunos registros.
			</p>
			<p>
				También hemos "troceado" las vistas, separando el <em>header</em> y
				el <em>footer</em> de cada página en ficheros distintos. En cada
				vista usamos la directiva <em>include</em> de JSP para importar ambos
				ficheros, evitando así tener que duplicar código.
			</p>
			<p>Pincha en los enlaces de la barra de navegación para probar
				las distintas opciones.</p>
		</div>

		<div class="aside">
			<img src="images/Teamwork.jpg">
		</div>
	</div>
</div>

<%@ include file="footer.jsp"%>

