package com.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.EmpleadosDAO;
import com.exceptions.DatosNoCorrectosException;
import com.model.Empleado;

@WebServlet("/EmpleadosController")
public class EmpleadosController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EmpleadosDAO empleadosDAO;

    @Override
    public void init() throws ServletException {
        empleadosDAO = new EmpleadosDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "listar";

        try {
            switch (action) {
                case "buscar":
                    buscarEmpleado(request, response);
                    break;
                case "editar":
                    mostrarFormularioEditar(request, response);
                    break;
                case "actualizar":
                    actualizarEmpleado(request, response);
                    break;
                case "formSalario":
                    request.getRequestDispatcher("salarioForm.jsp").forward(request, response);
                    break;
                default:
                    listarEmpleados(request, response);
                    break;
            }
        } catch (SQLException | DatosNoCorrectosException e) {
            e.printStackTrace();
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
        }
    }

    // ===========================================================
    // MÉTODOS DE ACCIÓN
    // ===========================================================

    private void listarEmpleados(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException, DatosNoCorrectosException {
        List<Empleado> lista = empleadosDAO.listar();
        request.setAttribute("listaEmpleados", lista);
        request.getRequestDispatcher("empleados.jsp").forward(request, response);
    }

    private void buscarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException, DatosNoCorrectosException {
        String dni = request.getParameter("dni");
        Empleado empleado = empleadosDAO.obtenerEmpleado(dni);

        if (empleado != null) {
            request.setAttribute("empleado", empleado);
            request.getRequestDispatcher("detalleEmpleado.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Empleado no encontrado");
            request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
        }
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException, DatosNoCorrectosException {
        String dni = request.getParameter("dni");
        Empleado empleado = empleadosDAO.obtenerEmpleado(dni);
        request.setAttribute("empleado", empleado);
        request.getRequestDispatcher("WEB-INF/editarEmpleado.jsp").forward(request, response);
    }

    private void actualizarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, DatosNoCorrectosException {
        String nombre = request.getParameter("nombre");
        String dni = request.getParameter("dni");
        char sexo = request.getParameter("sexo").charAt(0);
        int categoria = Integer.parseInt(request.getParameter("categoria"));
        int anyos = Integer.parseInt(request.getParameter("anyos"));

        Empleado empleado = new Empleado(nombre, dni, sexo, categoria, anyos);
        empleadosDAO.actualizarEmpleado(empleado);

        response.sendRedirect("EmpleadosController?action=listar");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
