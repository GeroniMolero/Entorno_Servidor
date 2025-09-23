/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.yourcompany.nominas;

import org.yourcompany.laboral.Empleado;
import org.yourcompany.laboral.Nomina;
import org.yourcompany.laboral.Persona;
import org.yourcompany.laboral.DatosNoCorrectosException;
/**
 *
 * @author usuario_
 */
public class CalculaNominas {

    public static void main(String[] args) {
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
    }

    private static void escribe(Empleado e1, Empleado e2){
        Nomina n = new Nomina();
        e1.imprime();
        System.out.println("Sueldo: "+n.sueldo(e1));
        e2.imprime();
        System.out.println("Sueldo: "+n.sueldo(e2));
    }
}
