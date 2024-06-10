package modelo;

public abstract class Reservador {
    //Atributos:
    private String codigo;
    private String descripcion;
    private int cantParticipantes;

    //Métodos:

    //Constructor
    public Reservador (String cod, String desc, int cantpart)
    {
        codigo=cod;
        descripcion=desc;
        cantParticipantes=cantpart;
    }

    //getters
    public String getCodigo() { return codigo; }
    public String getDescripcion(){ return descripcion; }
    public int getCantParticipantes(){ return cantParticipantes; }
}
