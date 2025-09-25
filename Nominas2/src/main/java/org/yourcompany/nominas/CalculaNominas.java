/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.yourcompany.nominas;

import org.yourcompany.laboral.Empleado;
import org.yourcompany.laboral.Nomina;
import org.yourcompany.laboral.Persona;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.yourcompany.laboral.DatosNoCorrectosException;

/**
 *
 * @author GeroniMolero
 */
public class CalculaNominas {

    //Variables para guardar la info de los ficheros con los datos de los empleados
    private static final String EMPLEADOS_TXT = "empleados.txt";
    private static final String SUELDOS_DAT = "sueldos.dat";


    //Configuracion de la conexion con la base de datos
    private static final String DB_URL = "jdbc:mysql://localhost:3306/gestion_nominas";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";
    static Connection conn = null;    

    public static void main(String[] args) throws DatosNoCorrectosException {

        //String EMPLEADOS_TXT = "empleados.txt";
        //String SUELDOS_DAT = "sueldos.dat";
        try {
            Empleado e1 = new Empleado(4,7,"James Cosling", "32000031G", 'H');
            Empleado e2 = new Empleado("Ada Lovelace", "32000032G",'M');
            escribe(e1,e2);
            e2.incAnyo();
            e1.setCategorias(9);
            escribe(e1, e2);
        } catch (org.yourcompany.laboral.DatosNoCorrectosException e) {
            System.out.println(e.getMessage());
            }

        crearFicheroTexto();

        generarFicheroBinario();
        leerFicheroBinario();

        crearDB();
        conexionDB();
        generarFicheroBinarioDesdeDB();
        leerFicheroBinarioDB();

        mostrarMenu();
    }


    private static void conexionDB(){
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            System.out.println("Conexión válida: " + conn.isValid(10));
            System.out.println("Estado del autocommit: " + conn.getAutoCommit());
        } catch (SQLException e) {
            System.out.println("Ocurrió una excepción al conectar a la BD");
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println("Ocurrió una excepción al cerrar la BD");
            }
        }
    }

    private static void escribe(Empleado e1, Empleado e2){
        Nomina n = new Nomina();
        e1.imprime();
        System.out.println("Sueldo: "+n.sueldo(e1));
        e2.imprime();
        System.out.println("Sueldo: "+n.sueldo(e2));
    }

    private static void crearFicheroTexto() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(EMPLEADOS_TXT))) {
            
            writer.write("32000031G,245000\n");
            writer.write("32000032G,55000\n");
            System.out.println("Archivo 'empleados.txt' creado correctamente.");
        } catch (IOException e) {
            System.err.println("Error al crear el archivo de texto: " + e.getMessage());
        }
    }

    @Deprecated
    private static void generarFicheroBinario() {
        try (
            BufferedReader reader = new BufferedReader(new FileReader(EMPLEADOS_TXT));
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(SUELDOS_DAT))
        ) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 4) {
                    String dni = partes[0].trim();
                    double sueldo = Double.parseDouble(partes[3].trim());

                    // Escribimos DNI como UTF y sueldo como double en binario
                    dos.writeUTF(dni);
                    dos.writeDouble(sueldo);
                }
            }
            System.out.println("Archivo binario 'sueldos.dat' creado correctamente.");
        } catch (IOException e) {
            System.err.println("Error al procesar archivos: " + e.getMessage());
        }
    }

    @Deprecated
    private static void leerFicheroBinario() {
    try (DataInputStream dis = new DataInputStream(new FileInputStream("sueldos.dat"))) {
        while (true) {
            String dni = dis.readUTF();
            double sueldo = dis.readDouble();
            System.out.println("DNI: " + dni + ", Sueldo: " + sueldo);
        }
    } catch (IOException e) {
        System.err.println("Error al leer el archivo binario: " + e.getMessage());
    }
    }

    private static void crearDB(){
        String query = "CREATE DATABASE IF NOT EXISTS gestion_nominas;"+"USE gestion_nominas;"+
        "CREATE TABLE Empleados(Dni varchar(9) PRIMARY KEY, Nombre varchar(50), Sexo char(1), Categoria int(11), Antiguedad int(11));"+
        "CREATE TABLE Nominas(Dni varchar(9) Primary KEY, Sueldo int(11));";
        try (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
        ){
            System.out.println("Base de datos creada correctamente");
        } catch (SQLException ex) {
            System.err.println("Error al crear la base de datos");
        }
    }

    private static void generarFicheroBinarioDesdeDB() {
        String query = "SELECT E.dni, N.sueldo " +
                       "FROM Empleados E JOIN Nominas N ON E.dni = N.dni";

        try (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(SUELDOS_DAT))
        ) {
            while (rs.next()) {
                String dni = rs.getString("dni");
                double sueldo = rs.getDouble("sueldo");

                dos.writeUTF(dni);
                dos.writeDouble(sueldo);
            }

            System.out.println("Archivo binario '" + SUELDOS_DAT + "' creado desde MySQL.");

        } catch (SQLException | IOException e) {
            System.err.println("Error generando binario: " + e.getMessage());
        }finally{
            //conn.close();
        }
    }

    private static void leerFicheroBinarioDB() {
        System.out.println("Contenido de sueldos.dat:");
        try (DataInputStream dis = new DataInputStream(new FileInputStream(SUELDOS_DAT))) {
            while (true) {
                String dni = dis.readUTF();
                double sueldo = dis.readDouble();
                System.out.println("DNI: " + dni + ", Sueldo: " + sueldo);
            }
        } catch (EOFException e) {
        } catch (IOException e) {
            System.err.println("Error al leer binario: " + e.getMessage());
        }
    }

    private static void mostrarMenu(){
        
        System.out.println("-----------------------------\n" +
        "MENU USUARIO\n"+
        "-----------------------------\n"+
        "1.Mostrar empleados\n"+
        "2.Mostrar salario por dni\n"+
        "3.Modificar datos de empleados\n"+
        "4.Actualizar sueldo por dni\n"+
        "5.Actualizar todos los sueldos\n"+
        "6.Realizar copia de seguridad\n"+
        "0.Salir\n"+
        "------------------------------\n");
        menuOpciones();
    }

    @SuppressWarnings("resource")
    private static void menuOpciones(){
        int opcion = 9;
        while(opcion!=0){
            System.out.print("Elige una opcion: ");
            opcion = new Scanner(System.in).nextInt();
        switch (opcion) {
            case 1 -> consultaDB("SELECT * FROM Empleados;");
            case 2 -> {
                String Dni;
                System.out.println("Dni del empleado: ");
                Dni = new Scanner(System.in).nextLine();

                consultaDB("SELECT * FROM Empleados where Dni like "+Dni+";");
                }
            case 3 -> consultaDB("UPDATE");
            case 4 -> consultaDB("USER");
            case 5 -> consultaDB("USER");
            case 6 -> consultaDB("USER");
            default -> throw new AssertionError("No has seleccionado una opcion valida");
        }
        }
        System.out.println("Adios");
    }

    private static void consultaDB(String query){

    }


}
