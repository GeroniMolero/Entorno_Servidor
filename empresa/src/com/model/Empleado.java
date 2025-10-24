package com.model;

import com.exceptions.DatosNoCorrectosException;

public class Empleado extends Persona{
    
    /**
     * En caso de erroR con algun parametro como categoria o anyos, se lanza la excepcion
     * {@link DatosNoCorrectosException}
     */
    private int categoria;
    public int anyos;

    /**
     * Constructor qu recoge todos los atributos del empleado, que en caso de que la
     * categoria no este entre 1 y 10 o anyos no sea positivo, lanzara la excepcon {@link DatosNoCorrectosException}
     * @param nombre String
     * @param dni String
     * @param sexo char
     * @param categoria int
     * @param anyos int
     * @throws DatosNoCorrectosException excepcion
     */
    public Empleado(String nombre,  String dni, char sexo, int categoria, int anyos) throws DatosNoCorrectosException {
        super(nombre, dni, sexo);
        
        if(categoria >=1 && categoria <= 10 && anyos >= 0){
            this.categoria = categoria;
            this.anyos = anyos;
        }
        else{
            throw new DatosNoCorrectosException("Datos no correctos");
        }
    }   

    /**
     * Constructor que solo recoge el nombre, dni y sexo
     * @param nombre String
     * @param dni String
     * @param sexo char
     */
    public Empleado(String nombre, String dni, char sexo) {
        super(nombre, dni, sexo);
        this.categoria = 1;
        this.anyos = 0;
    }

    /**
     * Contructor vacio para usar en DAO
     * @param categoria
     */
    public Empleado() {
        super();
    };

    /**
     * Para cambiar categoria
     * @param categoria int
     */
    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    /**
     * Devuelve categoria
     * @return 
     */
    public int getCategoria() {
        return categoria;
    }
    
    /**
     * Incrementa anyos en 1
     */
    public void incrAnyo(){
        this.anyos++;
    }
    
    /**
     * Imprime todos los datos del empleado
     */
    public void imprime(){
        System.out.println("Nombre: " + nombre + ", Dni: " + dni + ", Sexo: " + sexo + ", Categoria: " + categoria + ", Anyos trabajados: " + anyos);
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public char getSexo() {
        return sexo;
    }

    public int getAnyos() {
        return anyos;
    }

    
}