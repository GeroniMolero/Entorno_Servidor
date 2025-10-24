<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
	<c:set var="saludo" value="Hola mundo con JSTL"/>
	<c:out value="${saludo}"/>
</body>
</html>