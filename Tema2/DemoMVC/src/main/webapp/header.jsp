<!DOCTYPE HTML>
<html lang="es">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Demo MVC</title>
<script src="https://kit.fontawesome.com/4358b9453c.js"
	crossorigin="anonymous"></script>
<link href="https://fonts.googleapis.com/css?family=Roboto"
	rel="stylesheet">
<link rel="stylesheet" href="styles/style.css">
</head>

<body>

<!-- Esta etiqueta se cerrará en el fichero footer.jsp -->
<div class="global-container">

	<div class="header">
		<div class="container">
			<div class="col-izq">
				<img src="images/logo-icon.png" />
				<h1>My Company</h1>
			</div>
			<div class="col-der">
				<a href="#"><i class="fas fa-search"></i></a> <a href="#">Registro</a>
				<a href="#">Login</a>
			</div>
		</div>
	</div>

	<div class="nav-bar">
		<div class="container">
			<a href="index.jsp">Inicio</a>
			<a href="CustomerController?option=findAll">Todos</a>
			<a href="new-customer-form.jsp">Crear</a>
			<a href="find-customer-form.jsp">Actualizar/Eliminar</a>
		</div>
	</div>