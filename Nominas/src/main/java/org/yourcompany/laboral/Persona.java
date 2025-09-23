package org.yourcompany.laboral;

public class Persona {

    public String nombre;
    public String dni;
    public char sexo;

    public Persona(String nombre, String dni, char sexo) {
        this.nombre = nombre;
        this.dni = dni;
        this.sexo = sexo;
    }

    public Persona(String nombre, char sexo) {
        this.nombre = nombre;
        this.sexo = sexo;
    }

    public void setDni(String dni){
        this.dni = dni;
    }

    public void Imprime(){
        System.out.print("Nombre: "+nombre+", Dni: "+dni);
    }

    
}
