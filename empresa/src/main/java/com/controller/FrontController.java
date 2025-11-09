package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Patrón Front Controller - Punto único de entrada para todas las peticiones.
 * Centraliza el enrutamiento hacia los controladores específicos (EmpleadosController o NominasController).
 * 
 * Los controladores internos usan interfaces (IEmpleadoDAO, INominaDAO) siguiendo
 * el principio de Dependency Inversion, facilitando testing y mantenimiento.
 * 
 * Rutas soportadas:
 * - /app/empleados?action=... -> EmpleadosController
 * - /app/nominas?action=... -> NominasController
 */
@WebServlet("/app/*")
public class FrontController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private EmpleadosController empleadosController;
    private NominasController nominasController;
    
    @Override
    public void init() throws ServletException {
        super.init();
        
        // Inicializar los controladores específicos
        empleadosController = new EmpleadosController();
        empleadosController.init();
        
        nominasController = new NominasController();
        nominasController.init();
        
        log("FrontController inicializado correctamente");
    }
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String pathInfo = request.getPathInfo();
        
        // Logging para debugging
        log("Petición recibida: " + request.getMethod() + " " + pathInfo);
        
        try {
            // Validar que existe pathInfo
            if (pathInfo == null || pathInfo.equals("/")) {
                redirigirAHome(request, response);
                return;
            }
            
            // Enrutar según el path
            if (pathInfo.startsWith("/empleados")) {
                log("Redirigiendo a EmpleadosController");
                empleadosController.service(request, response);
                
            } else if (pathInfo.startsWith("/nominas")) {
                log("Redirigiendo a NominasController");
                nominasController.service(request, response);
                
            } else {
                // Ruta no reconocida
                response.sendError(HttpServletResponse.SC_NOT_FOUND, 
                    "Recurso no encontrado: " + pathInfo);
            }
            
        } catch (Exception e) {
            log("Error en FrontController: " + e.getMessage(), e);
            manejarError(e, request, response);
        }
    }
    
    /**
     * Redirige a la página de inicio cuando no se especifica un recurso
     */
    private void redirigirAHome(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
    
    /**
     * Manejo centralizado de errores con sanitización según entorno
     */
    private void manejarError(Exception e, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String environment = getServletContext().getInitParameter("app.environment");
        boolean isDevelopment = "development".equals(environment);
        
        // Sanitizar mensaje para usuario final
        String userMessage = sanitizeErrorMessage(e.getMessage());
        request.setAttribute("error", userMessage);
        
        // Solo en desarrollo: exponer detalles técnicos
        if (isDevelopment) {
            request.setAttribute("errorType", e.getClass().getSimpleName());
            request.setAttribute("errorStackTrace", getStackTraceAsString(e));
        }
        
        // Siempre loggear el error completo para administradores
        log("Error en aplicación: " + e.getMessage(), e);
        
        request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
    }
    
    /**
     * Sanitiza mensajes de error para evitar exponer información sensible.
     * Permite mensajes de negocio pero oculta detalles técnicos (SQL, rutas, etc.)
     */
    private String sanitizeErrorMessage(String message) {
        if (message == null || message.trim().isEmpty()) {
            return "Se ha producido un error inesperado.";
        }
        
        // Lista blanca: mensajes de negocio permitidos
        String lowerMessage = message.toLowerCase();
        if (lowerMessage.contains("dni") || 
            lowerMessage.contains("empleado") || 
            lowerMessage.contains("nómina") ||
            lowerMessage.contains("nomina") ||
            lowerMessage.contains("categoría") ||
            lowerMessage.contains("años")) {
            return message;
        }
        
        // Lista negra: ocultar mensajes técnicos
        if (lowerMessage.contains("sql") || 
            lowerMessage.contains("connection") || 
            lowerMessage.contains("database") ||
            lowerMessage.contains("table") || 
            lowerMessage.contains("column") ||
            lowerMessage.contains("jdbc") ||
            lowerMessage.contains("exception") ||
            lowerMessage.contains("null pointer") ||
            lowerMessage.contains("class") ||
            lowerMessage.contains("stack")) {
            return "Error al procesar la solicitud. Por favor, contacte al administrador del sistema.";
        }
        
        // Mensaje genérico para otros casos
        return "Se ha producido un error. Por favor, inténtelo de nuevo.";
    }
    
    /**
     * Convierte el stack trace en String para mostrar en la página de error
     */
    private String getStackTraceAsString(Exception e) {
        StringBuilder sb = new StringBuilder();
        sb.append(e.toString()).append("\n");
        for (StackTraceElement element : e.getStackTrace()) {
            sb.append("\tat ").append(element.toString()).append("\n");
        }
        return sb.toString();
    }
    
    @Override
    public void destroy() {
        log("FrontController destruido");
        super.destroy();
    }
}
