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

}
