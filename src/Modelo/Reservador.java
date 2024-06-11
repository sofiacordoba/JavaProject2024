package Modelo;

import java.io.Serializable;

public abstract class Reservador implements Serializable {
    //Atributos:
    private String codigo;
    private String descripcion;

    //Métodos:

    //Constructor
    public Reservador (String cod, String desc)
    {
        codigo=cod;
        descripcion=desc;
    }

    //getters
    public String getCodigo() { return codigo; }
    public String getDescripcion(){ return descripcion; }
    public double getMonto() { // calcula el monto basado en el tipo de reserva (Reportes)
        if (this instanceof Externo) {
            return ((Externo) this).getCobroAl();
        } else if (this instanceof Curso) {
            Curso curso = (Curso) this;
            return curso.getCosto() * curso.getCantAl(); // costo por alumno y la cantidad de alumnos
        } else { // En caso de otros tipos de reserva
            return 0.0;
        }
    }

}
