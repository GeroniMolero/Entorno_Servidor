package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.EmpleadosDAO;
import com.dao.IEmpleadoDAO;
import com.dao.INominaDAO;
import com.dao.NominasDAO;
import com.model.Empleado;
import com.model.Nomina;

/**
 * Controlador para la gestión de nóminas.
 * Ahora es invocado a través del FrontController en lugar de directamente.
 * Usa interfaces IEmpleadoDAO e INominaDAO en lugar de implementaciones concretas,
 * siguiendo el principio de Dependency Inversion (SOLID).
 * Anteriormente mapeado en: @WebServlet("/NominasController")
 * Puede consultar el salario de un empleado o listar todas las nóminas existentes.
 */
public class NominasController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IEmpleadoDAO empleadosDAO;
    private INominaDAO nominasDAO;

    @Override
    public void init() throws ServletException {
        empleadosDAO = new EmpleadosDAO(); // Inyección manual
        nominasDAO = new NominasDAO();     // Inyección manual
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "formularioSalario";

        try {
            switch (action) {
                case "formularioSalario":
                    // Mostrar el formulario para introducir DNI
                    request.getRequestDispatcher("/salarioForm.jsp").forward(request, response);
                    break;
                case "consultarSalario":
                    // Procesar consulta con DNI
                    consultarSalario(request, response);
                    break;
                case "listarNominas":
                    listarNominas(request, response);
                    break;
                default:
                    request.getRequestDispatcher("/salarioForm.jsp").forward(request, response);
                    break;
            }
        } catch (Exception e) {
            // Log sencillo. En producción usar un logger.
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
        }
    }

    /**
     * Consulta y muestra el salario de un empleado concreto.
     * Primero intenta obtenerlo desde la base de datos.
     * Si no existe, lo calcula con la clase Nomina.
     */
    private void consultarSalario(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String dni = request.getParameter("dni");
        
        if (dni == null || dni.trim().isEmpty()) {
            throw new IllegalArgumentException("El DNI proporcionado es nulo o vacío.");
        }
        
        Empleado empleado = empleadosDAO.obtenerEmpleado(dni);

        if (empleado == null) {
            request.setAttribute("error", "No se encontró el empleado con DNI " + dni);
        } else {
            // Intentar obtener sueldo desde la tabla nominas
            Map<String, Object> registro = nominasDAO.obtenerNomina(dni);
            double salario;

            if (registro != null) {
                salario = (double) registro.get("sueldo");
            } else {
                // Si no hay registro, calcular el salario
                Nomina nomina = new Nomina();
                salario = nomina.sueldo(empleado);
            }

            request.setAttribute("empleado", empleado);
            request.setAttribute("salario", salario);
        }

    request.getRequestDispatcher("/salarioResultado.jsp").forward(request, response);
    }

    /**
     * Lista todas las nóminas registradas en la base de datos.
     */
    private void listarNominas(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        List<Empleado> empleados = empleadosDAO.listar();
        List<Map<String, Object>> listaNominas = new ArrayList<>();

        for (Empleado e : empleados) {
            Map<String, Object> registro = nominasDAO.obtenerNomina(e.getDni());

            Map<String, Object> datos = new HashMap<>();
            datos.put("empleado", e);

            if (registro != null) {
                datos.put("salario", registro.get("sueldo"));
            } else {
                // Si no existe en BD, calcularlo
                Nomina n = new Nomina();
                datos.put("salario", n.sueldo(e));
            }

            listaNominas.add(datos);
        }

        request.setAttribute("listaNominas", listaNominas);
    request.getRequestDispatcher("/nominas.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
