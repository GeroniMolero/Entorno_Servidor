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

    // Mostrar empleados
    public List<Empleado> listar() throws SQLException, DatosNoCorrectosException {
        ResultSet resultSet = null;
        List<Empleado> listaEmpleados = new ArrayList<>();
        String sql = "SELECT * FROM empleados";

        try {
            connection = obtenerConexion();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Aquí, los valores deben coincidir con el índice de las columnas de tu base de datos
                // Asumimos que las columnas en la base de datos están en el mismo orden
                Empleado e = new Empleado(
                        resultSet.getString(1),  // nombre
                        resultSet.getString(2),  // dni
                        resultSet.getString(3).charAt(0), // sexo
                        resultSet.getInt(4),    // categoria
                        resultSet.getInt(5)     // anyos
                );
                listaEmpleados.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return listaEmpleados;
    }

    // Mostrar empleado por dni
    public Empleado obtenerEmpleado(String dni) throws SQLException, DatosNoCorrectosException {
        ResultSet resultSet = null;
        Empleado e = null;
        String sql = "SELECT * FROM empleados WHERE dni=?";

        try {
            connection = obtenerConexion();
            statement = connection.prepareStatement(sql);
            statement.setString(1, dni);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                e = new Empleado(
                        resultSet.getString(1),  // nombre
                        resultSet.getString(2),  // dni
                        resultSet.getString(3).charAt(0), // sexo
                        resultSet.getInt(4),    // categoria
                        resultSet.getInt(5)     // anyos
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return e;
    }

    // Actualizar empleado y recalcular su sueldo
    public boolean actualizarEmpleado(Empleado empleado) throws SQLException {
        String sql = "UPDATE empleados SET nombre=?, sexo=?, categoria=?, antiguedad=? WHERE dni=?";
        boolean estadoOperacion = false;

        try {
            connection = obtenerConexion();
            statement = connection.prepareStatement(sql);
            statement.setString(1, empleado.nombre);
            statement.setString(2, String.valueOf(empleado.sexo));
            statement.setInt(3, empleado.getCategoria());
            statement.setInt(4, empleado.anyos);
            statement.setString(5, empleado.dni);

            int filasAfectadas = statement.executeUpdate();
            if (filasAfectadas > 0) {
                Nomina n = new Nomina();
                double nuevoSueldo = n.sueldo(empleado);

                sql = "UPDATE nominas SET sueldo=? WHERE dni=?";
                statement = connection.prepareStatement(sql);
                statement.setDouble(1, nuevoSueldo);
                statement.setString(2, empleado.dni);
                statement.executeUpdate();

                estadoOperacion = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            estadoOperacion = false;
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return estadoOperacion;
    }

    // Buscar por criterio
    public List<Empleado> buscarPorCriterio(String campo, String valor) throws SQLException, DatosNoCorrectosException {
        ResultSet resultSet = null;
        List<Empleado> listaEmpleados = new ArrayList<>();
        String sql = "SELECT * FROM empleados WHERE " + campo + " LIKE ?";

        try {
            connection = obtenerConexion();
            statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + valor + "%");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Empleado e = new Empleado(
                        resultSet.getString(1),  // nombre
                        resultSet.getString(2),  // dni
                        resultSet.getString(3).charAt(0), // sexo
                        resultSet.getInt(4),    // categoria
                        resultSet.getInt(5)     // anyos
                );
                listaEmpleados.add(e);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return listaEmpleados;
    }

    private Connection obtenerConexion() throws SQLException {
        return Conexion.getConnection();
    }
}
