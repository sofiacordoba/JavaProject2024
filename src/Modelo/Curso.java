package modelo;

public class Curso extends Reservador {
    //Atributos:
    private int cantClases;
    private double costo;

    //Métodos:

    //Constructor
    public Curso (int cantclas, double c, String cod, String desc, int cantpart)
    {
        super(cod, desc, cantpart);
        cantClases=cantclas;
        costo=c;
    }

    //getters
    public int getCantClases(){ return cantClases; }
    public double getCosto(){ return costo; }
}
