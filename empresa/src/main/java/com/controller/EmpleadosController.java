package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.EmpleadosDAO;
import com.dao.IEmpleadoDAO;
import com.model.Empleado;

/**
 * Controlador para gestión de empleados.
 * Ahora es invocado a través del FrontController en lugar de directamente.
 * Usa la interfaz IEmpleadoDAO en lugar de la implementación concreta,
 * siguiendo el principio de Dependency Inversion (SOLID).
 * Anteriormente mapeado en: @WebServlet("/EmpleadosController")
 */
public class EmpleadosController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IEmpleadoDAO dao;

    @Override
    public void init() {
        dao = new EmpleadosDAO(); // Inyección manual - podría mejorarse con DI container
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        if (action == null) {
            action = "listar";
        }

        try {
            switch (action) {
                case "listar":
                    List<Empleado> lista = dao.listar();
                    req.setAttribute("listaEmpleados", lista);
                    forward(req, res, "empleados.jsp");
                    break;

                case "buscarForm":
                    forward(req, res, "WEB-INF/buscarEmpleado.jsp");
                    break;

                case "buscarResultado":
                    req.setAttribute("listaEmpleados", dao.buscarPorCriterio(req));
                    forward(req, res, "WEB-INF/resultadoBusqueda.jsp");
                    break;

                case "editar":
                    String dni = req.getParameter("dni");
                    if (dni == null || dni.trim().isEmpty()) {
                        throw new IllegalArgumentException("El DNI del empleado no fue proporcionado para la edición.");
                    }

                    Empleado emp = dao.obtenerEmpleado(dni);
                    if (emp == null) {
                        req.setAttribute("error", "No se encontró el empleado con DNI: " + dni);
                    } else {
                        req.setAttribute("empleado", emp);
                    }

                    forward(req, res, "WEB-INF/editarEmpleado.jsp");
                    break;

                default:
                    res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no reconocida: " + action);
                    break;
            }
        } catch (Exception e) {
            manejarError(e, req, res);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        try {
            if ("actualizar".equals(action)) {
                dao.actualizarEmpleado(req);
                res.sendRedirect(req.getContextPath() + "/app/empleados?action=listar");
            } else {
                res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción POST no reconocida: " + action);
            }
        } catch (Exception e) {
            manejarError(e, req, res);
        }
    }

    private void forward(HttpServletRequest req, HttpServletResponse res, String ruta)
            throws ServletException, IOException {
        // Asegurar ruta relativa al contexto (debe empezar con "/") para evitar /app/*
        String path = (ruta != null && ruta.startsWith("/")) ? ruta : "/" + ruta;
        req.getRequestDispatcher(path).forward(req, res);
    }

    private void manejarError(Exception e, HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        // Log sencillo. En producción utilizar un logger (SLF4J/Log4j/Jul).
        req.setAttribute("error", e.getMessage());
        req.getRequestDispatcher("/WEB-INF/error.jsp").forward(req, res);
    }
}
