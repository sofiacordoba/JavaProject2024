package modelo;

public abstract class Reservador {
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

    //geters
    public String getCodigo() { return codigo; }
    public String getDescripcion(){ return descripcion; }

}
