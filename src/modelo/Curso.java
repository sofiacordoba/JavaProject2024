package Modelo;

public class Curso extends Reservador {
    private int cantAlumnos;
    private int cantClases;
    private double costo;

    public Curso (int cantal, int cantclas, double c, String codigo, String descripcion)
    {
        super(codigo, descripcion);
        cantAlumnos = cantal;
        cantClases = cantclas;
        costo = c;
    }

    public int getCantAl(){ return cantAlumnos; }
    public int getCantClases(){ return cantClases; }
    public double getCosto(){ return costo; }
}
