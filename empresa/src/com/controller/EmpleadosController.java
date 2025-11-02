package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.EmpleadosDAO;
import com.model.Empleado;

@WebServlet("/EmpleadosController")
public class EmpleadosController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EmpleadosDAO dao;

    @Override
    public void init() { dao = new EmpleadosDAO(); }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        if (action == null) action = "listar";

        try {
            switch (action) {
                case "buscarForm":
                    forward(req, res, "WEB-INF/buscarEmpleado.jsp");
                    break;

                case "buscarResultado":
                    req.setAttribute("listaEmpleados", dao.buscarPorCriterio(req));
                    forward(req, res, "WEB-INF/resultadoBusqueda.jsp");
                    break;

                case "editar":
                    req.setAttribute("empleado", dao.obtenerEmpleado(req.getParameter("dni")));
                    forward(req, res, "WEB-INF/editarEmpleado.jsp");
                    break;

                case "actualizar":
                    dao.actualizarEmpleado(req);
                    res.sendRedirect("EmpleadosController?action=listar");
                    break;

                default:
                    List<Empleado> lista = dao.listar();
                    req.setAttribute("listaEmpleados", lista);
                    forward(req, res, "empleados.jsp");
                    break;
            }
        } catch (Exception e) {
            manejarError(e, req, res);
        }
    }

    private void forward(HttpServletRequest req, HttpServletResponse res, String ruta)
            throws ServletException, IOException {
        req.getRequestDispatcher(ruta).forward(req, res);
    }

    private void manejarError(Exception e, HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        e.printStackTrace();
        req.setAttribute("error", e.getMessage());
        req.getRequestDispatcher("WEB-INF/error.jsp").forward(req, res);
    }
}
