package com.dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import com.conexion.Conexion;
import com.model.Nomina;
 
public class NominasDAO {
 private Connection connection;
 private PreparedStatement statement;
 private boolean estadoOperacion;
 
 // obtener nomina
 public Map<String, Object> obtenerNomina(String dni) throws SQLException {
  ResultSet resultSet = null;
   
  String sql = null;
  estadoOperacion = false;
  connection = obtenerConexion();
 
  try {
   sql = "SELECT * FROM nominas WHERE dni =?";
   statement = connection.prepareStatement(sql);
   statement.setString(1, dni);
 
   resultSet = statement.executeQuery();
 
   if (resultSet.next()) {
    Map<String, Object> resultado = new HashMap<>();
    resultado.put("dni", resultSet.getString("dni"));
    resultado.put("sueldo", resultSet.getDouble("sueldo"));
    return resultado;
   }
 
  } catch (SQLException e) {
   e.printStackTrace();
  }
 
  return null;
 }
 

 // obtener conexion pool
 private Connection obtenerConexion() throws SQLException {
  return Conexion.getConnection();
 }
 
}
