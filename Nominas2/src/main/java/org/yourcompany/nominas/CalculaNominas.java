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

/**
 *
 * @author GeroniMolero
 */
public class CalculaNominas {

    private static final String EMPLEADOS_TXT = "empleados.txt";
    private static final String SUELDOS_DAT = "sueldos.dat";

    private static final String DB_URL = "jdbc:mysql://localhost:3306/gestion_de_nominas";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";    

    public static void main(String[] args) {

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

    private static void leerFicheroBinario() {
    try (DataInputStream dis = new DataInputStream(new FileInputStream("sueldos.dat"))) {
        while (true) {
            String dni = dis.readUTF();
            double sueldo = dis.readDouble();
            System.out.println("DNI: " + dni + ", Sueldo: " + sueldo);
        }
    } catch (EOFException e) {
        // Fin del archivo alcanzado, es normal
    } catch (IOException e) {
        System.err.println("Error al leer el archivo binario: " + e.getMessage());
    }
}


}
