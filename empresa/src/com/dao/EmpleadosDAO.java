package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.conexion.Conexion;
import com.exceptions.DatosNoCorrectosException;
import com.model.Empleado;
import com.model.Nomina;

public class EmpleadosDAO {

    private Connection connection;
    private PreparedStatement statement;

    // ===========================================================
    // LISTAR EMPLEADOS
    // ===========================================================
    public List<Empleado> listar() throws SQLException, DatosNoCorrectosException {
        List<Empleado> listaEmpleados = new ArrayList<>();
        String sql = "SELECT * FROM empleados";
        ResultSet rs = null;

        try {
            connection = obtenerConexion();
            statement = connection.prepareStatement(sql);
            rs = statement.executeQuery();

            while (rs.next()) {
                Empleado e = new Empleado(
                        rs.getString("nombre"),
                        rs.getString("dni"),
                        rs.getString("sexo").charAt(0),
                        rs.getInt("categoria"),
                        rs.getInt("anyos")
                );
                listaEmpleados.add(e);
            }
        } finally {
            if (rs != null) rs.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return listaEmpleados;
    }

    // ===========================================================
    // OBTENER EMPLEADO POR DNI
    // ===========================================================
    public Empleado obtenerEmpleado(String dni) throws SQLException, DatosNoCorrectosException {
        Empleado e = null;
        String sql = "SELECT * FROM empleados WHERE dni=?";
        ResultSet rs = null;

        try {
            connection = obtenerConexion();
            statement = connection.prepareStatement(sql);
            statement.setString(1, dni);
            rs = statement.executeQuery();

            if (rs.next()) {
                e = new Empleado(
                        rs.getString("nombre"),
                        rs.getString("dni"),
                        rs.getString("sexo").charAt(0),
                        rs.getInt("categoria"),
                        rs.getInt("anyos")
                );
            }
        } finally {
            if (rs != null) rs.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return e;
    }

    // ===========================================================
    // ACTUALIZAR EMPLEADO Y SU SUELDO
    // ===========================================================
    public boolean actualizarEmpleado(Empleado empleado) throws SQLException {
        String sql = "UPDATE empleados SET nombre=?, sexo=?, categoria=?, anyos=? WHERE dni=?";
        boolean estadoOperacion = false;

        try {
            connection = obtenerConexion();
            statement = connection.prepareStatement(sql);
            statement.setString(1, empleado.getNombre());
            statement.setString(2, String.valueOf(empleado.getSexo()));
            statement.setInt(3, empleado.getCategoria());
            statement.setInt(4, empleado.getAnyos());
            statement.setString(5, empleado.getDni());

            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                // Recalcular el sueldo
                Nomina n = new Nomina();
                double nuevoSueldo = n.sueldo(empleado);

                sql = "UPDATE nominas SET sueldo=? WHERE dni=?";
                statement = connection.prepareStatement(sql);
                statement.setDouble(1, nuevoSueldo);
                statement.setString(2, empleado.getDni());
                statement.executeUpdate();

                estadoOperacion = true;
            }
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return estadoOperacion;
    }

    // ===========================================================
    // BUSCAR POR CRITERIO (nombre, dni, etc.)
    // ===========================================================
    public List<Empleado> buscarPorCriterio(String campo, String valor) throws SQLException, DatosNoCorrectosException {
        List<Empleado> listaEmpleados = new ArrayList<>();
        String sql = "SELECT * FROM empleados WHERE " + campo + " LIKE ?";
        ResultSet rs = null;

        try {
            connection = obtenerConexion();
            statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + valor + "%");
            rs = statement.executeQuery();

            while (rs.next()) {
                Empleado e = new Empleado(
                        rs.getString("nombre"),
                        rs.getString("dni"),
                        rs.getString("sexo").charAt(0),
                        rs.getInt("categoria"),
                        rs.getInt("anyos")
                );
                listaEmpleados.add(e);
            }
        } finally {
            if (rs != null) rs.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return listaEmpleados;
    }

    // ===========================================================
    // CONEXIÓN A BD
    // ===========================================================
    private Connection obtenerConexion() throws SQLException {
        return Conexion.getConnection();
    }
}
