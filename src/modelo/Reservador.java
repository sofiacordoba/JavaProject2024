package Modelo;

public abstract class Reservador {
    private String codigo;
    private String descripcion;

    public Reservador (String cod, String desc)
    {
        codigo = cod;
        descripcion = desc;
    }

    public String getCodigo() { return codigo; }
    public String getDescripcion(){ return descripcion; }
}
