package org.yourcompany.laboral;

public class Empleado extends Persona{
    
    private int categoria;
    public int anyos;

    public Empleado(String nombre, String dni, char sexo) {
        super(nombre, dni, sexo);
        this.categoria = 1;
        this.anyos = 0;
    }

    public Empleado(int categoria, int anyos, String nombre, String dni, char sexo) throws DatosNoCorrectosException {
        super(nombre, dni, sexo);
        if (categoria > 0 && categoria <= 10){
        this.categoria = categoria;
        }else{
            throw new DatosNoCorrectosException("La categoría debe ser un entero entre 1 y 10");
        }
        if (anyos > 0){
        this.anyos = anyos;
        }else{
            throw new DatosNoCorrectosException("El empleado debe haber trabajado antes");
        }
    }

    public void setCategorias(int categoria) throws DatosNoCorrectosException{
        if (categoria > 0 && categoria <= 10){
        this.categoria = categoria;
        }else{
            throw new DatosNoCorrectosException("La categoría debe ser un entero entre 1 y 10");
        }
    }

    public int getCategoria(){
        return categoria;
    }

    public void incAnyo(){
        this.anyos ++;
    }

    public void imprime(){
        System.out.println("\nDatos del empleado\n--------------------\nNombre: "+nombre+"\nDni: "+dni+"\nsexo: "+sexo+"\ncategoría: "+categoria+"\nAños trabajados: "+anyos+"\n----------------------");
    }

    @Override
    public String toString() {
        return super.toString();
    }

    

    
}
