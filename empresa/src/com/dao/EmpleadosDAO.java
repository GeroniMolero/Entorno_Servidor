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

//mostrar empleado por dni
public Empleado obtenerEmpleado(String dni) throws SQLException, DatosNoCorrectosException {
  ResultSet resultSet = null;
  Empleado e = new Empleado();

  String sql = null;
  estadoOperacion = false;
  connection = obtenerConexion();
  
  try {
   sql = "SELECT * FROM empleados WHERE dni=?";
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

//Actualizar empleado y recalcula automáticamente su sueldo
public void actualizarEmpleado(Empleado empleado) throws SQLException {
  ResultSet resultSet = null;
  String sql = null;
  boolean estadoOperacion = false;
  Connection connection = obtenerConexion();

  try{
    sql = "UPDATE empleados SET nombre=?, sexo=?, categoria=?, antiguedad=? WHERE dni=?"
    statement = connection.prepareStatement(sql);
    statement.setString(1,e.nombre);
    statement.setString(2,String.valueOf(e.sexo));
    statement.setInt(3,e.getCategoria());
    statement.setInt(4,e.anyos);
    statement.setString(5,e.dni);

    estadoOperacion = statement.executeUpdate() > 0;

    if(estadoOperacion){
      Nomina n = new Nomina();
      double nuevoSueldo = n.sueldo(e);

      sql = "UPDATE nominas SET sueldo=? WHERE dni=?";
      statement = connection.prepareStatement(sql);
      statement.setDouble(1, nuevoSueldo);
      statement.setString(2,e.dni);
      statement.executeUpdate();
    }
    estadoOperacion = true;
  }
  catch (SQLException ex){
    ex.printStackTrace();
    estadoOperacion = false;
  }finally{
    if(statement != null) statement.close();
    if(connection != null) connection.close();
  }
  return estadoOperacion;
}

//Buscar por criterio
public List<Empleado> buscarPorCriterio(String campo, String valor) throws SQLException, DatosNoCorrectosException{
  ResultSet resultSet = null;
  PreparedStatement statement = null;
  List<Empleado> listaEmpleados = new ArrayList<>();

  boolean estadoOperacion = false;
  String sql = null;
  Connection connection = obtenerConexion();

  try{
    sql = "SELECT * from empleados WHERE "+campo+" LIKE ?";
    statement = connection.prepareStatement(sql);
    statement.setString(1,"%"+valor+"%");
    resultSet = statement.executeQuery();

    while(resultSet.next()){
      Empleado e = new Empleado(
        resultSet.getString(1),
        resultSet.getString(2),
        resultSet.getString(3).charAt(0),
        resultSet.getInt(4),
        resultSet.getInt(5)
      );
      listaEmpleados.add(e);
    }
  }catch(SQLException ex){
    ex.printStackTrace();
  }finally{
    if(resultSet != null){resultSet.close()};
    if(statement != null){statement.close()};
    if(connection != null){connection.close()};
  }

  return listaEmpleados;
}

}
