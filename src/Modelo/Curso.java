package modelo;

public class Curso extends Reservador {
    //Atributos:
    private int cantAlumnos;
    private int cantClases;
    private double costo;

    //Métodos:

    //Constructor
    public Curso (int cantal, int cantclas, double c, String cod, String desc)
    {
        super(cod, desc);
        cantAlumnos=cantal;
        cantClases=cantclas;
        costo=c;
    }

    //getters
    public int getCantAl(){ return cantAlumnos; }
    public int getCantClases(){ return cantClases; }
    public double getCosto(){ return costo; }
}
