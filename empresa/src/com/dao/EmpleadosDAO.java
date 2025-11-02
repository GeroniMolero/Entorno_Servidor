package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.conexion.Conexion;
import com.exceptions.DatosNoCorrectosException;
import com.model.Empleado;
import com.model.Nomina;

/**
 * DAO mejorado: actúa como Business Delegate.
 * Maneja la lógica de negocio y la interacción con la base de datos.
 */
public class EmpleadosDAO {

    // ===========================================================
    // LISTAR TODOS LOS EMPLEADOS
    // ===========================================================
    public List<Empleado> listar() throws SQLException, DatosNoCorrectosException {
        String sql = "SELECT * FROM empleados";
        List<Empleado> lista = new ArrayList<>();

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapEmpleado(rs));
            }
        }
        return lista;
    }

    // ===========================================================
    // OBTENER UN EMPLEADO POR DNI
    // ===========================================================
    public Empleado obtenerEmpleado(String dni) throws SQLException, DatosNoCorrectosException {
        String sql = "SELECT * FROM empleados WHERE dni=?";
        Empleado e = null;

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dni);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) e = mapEmpleado(rs);
            }
        }
        return e;
    }

    // ===========================================================
    // ACTUALIZAR UN EMPLEADO (usando HttpServletRequest)
    // ===========================================================
    public boolean actualizarEmpleado(HttpServletRequest request)
            throws SQLException, DatosNoCorrectosException {

        Empleado empleado = buildEmpleadoDesdeRequest(request);

        String sqlEmpleado = "UPDATE empleados SET nombre=?, sexo=?, categoria=?, anyos=? WHERE dni=?";
        String sqlNomina = "UPDATE nominas SET sueldo=? WHERE dni=?";

        try (Connection con = Conexion.getConnection()) {
            con.setAutoCommit(false);

            try (PreparedStatement psEmp = con.prepareStatement(sqlEmpleado)) {
                psEmp.setString(1, empleado.getNombre());
                psEmp.setString(2, String.valueOf(empleado.getSexo()));
                psEmp.setInt(3, empleado.getCategoria());
                psEmp.setInt(4, empleado.getAnyos());
                psEmp.setString(5, empleado.getDni());
                psEmp.executeUpdate();
            }

            // Calcular sueldo
            Nomina n = new Nomina();
            double nuevoSueldo = n.sueldo(empleado);

            try (PreparedStatement psNom = con.prepareStatement(sqlNomina)) {
                psNom.setDouble(1, nuevoSueldo);
                psNom.setString(2, empleado.getDni());
                psNom.executeUpdate();
            }

            con.commit();
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Error actualizando empleado: " + ex.getMessage());
        }
    }

    // ===========================================================
    // BUSCAR EMPLEADOS POR CRITERIO
    // ===========================================================
    public List<Empleado> buscarPorCriterio(HttpServletRequest request)
            throws SQLException, DatosNoCorrectosException {

        String campo = request.getParameter("campo");
        String valor = request.getParameter("valor");

        List<String> camposValidos = Arrays.asList("nombre", "dni", "sexo", "categoria", "anyos");
        if (!camposValidos.contains(campo)) {
            throw new SQLException("Campo no válido: " + campo);
        }

        String sql = "SELECT * FROM empleados WHERE " + campo + " LIKE ?";
        List<Empleado> lista = new ArrayList<>();

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + valor + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) lista.add(mapEmpleado(rs));
            }
        }
        return lista;
    }

    // ===========================================================
    // MÉTODOS AUXILIARES (CONSTRUCCIÓN Y MAPEOS)
    // ===========================================================

    /**
     * Crea un objeto Empleado a partir de un ResultSet
     */
    private Empleado mapEmpleado(ResultSet rs) throws SQLException, DatosNoCorrectosException {
        return new Empleado(
                rs.getString("nombre"),
                rs.getString("dni"),
                rs.getString("sexo").charAt(0),
                rs.getInt("categoria"),
                rs.getInt("anyos")
        );
    }

    /**
     * Construye un objeto Empleado desde un HttpServletRequest
     */
    private Empleado buildEmpleadoDesdeRequest(HttpServletRequest request)
            throws DatosNoCorrectosException {

        return new Empleado(
                request.getParameter("nombre"),
                request.getParameter("dni"),
                request.getParameter("sexo").charAt(0),
                Integer.parseInt(request.getParameter("categoria")),
                Integer.parseInt(request.getParameter("anyos"))
        );
    }
}
