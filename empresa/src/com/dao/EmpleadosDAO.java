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
 
public class EmpleadosDAO {
 private Connection connection;
 private PreparedStatement statement;
 private boolean estadoOperacion;
 
//mostrar empleados
public List<Empleado> listar() throws SQLException, DatosNoCorrectosException {
    ResultSet resultSet = null;
    List<Empleado> listaEmpleados = new ArrayList<>();
 
  String sql = null;
  estadoOperacion = false;
  connection = obtenerConexion();
 
  try {
   sql = "SELECT * FROM empleados";
   statement = connection.prepareStatement(sql);
   resultSet = statement.executeQuery(sql);

   while (resultSet.next()) {
    Empleado e = new Empleado(
        resultSet.getString(1),
        resultSet.getString(2),
        resultSet.getString(3).charAt(0),
        resultSet.getInt(4),
        resultSet.getInt(5));

    listaEmpleados.add(e);
   }
 
  } catch (SQLException e) {
   e.printStackTrace();
  }
 
  return listaEmpleados;
}

// obtener empleado por dato
public Empleado obtenerEmpleado(String campo) throws SQLException, DatosNoCorrectosException {
  ResultSet resultSet = null;
  Empleado e = new Empleado();
 
  String sql = null;
  estadoOperacion = false;
  connection = obtenerConexion();
 
  try {
   sql = "SELECT * FROM empleados WHERE dni =?";
   statement = connection.prepareStatement(sql);
   statement.setString(1, dni);
 
   resultSet = statement.executeQuery();
 
   if (resultSet.next()) {
    e = new Empleado(
        resultSet.getString(1),
        resultSet.getString(2),
        resultSet.getString(3).charAt(0),
        resultSet.getInt(4),
        resultSet.getInt(5));
   }
 
  } catch (SQLException ex) {
   ex.printStackTrace();
  }
 
  return e;
 }

 
 // obtener conexion pool
 private Connection obtenerConexion() throws SQLException {
  return Conexion.getConnection();
 }
 
}
