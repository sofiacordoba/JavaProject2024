package Modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/** La clase Curso representa un curso que existe dento de la universidad.
 * Puede realizar reservas de aulas.
 * @author Sofia Cordoba, Tatiana Malano Merlino, Josefina Garriz Scarpelli
 */

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

    public List<LocalDate> obtenerFechasDeClase(LocalDate fecha) {  //lista de las fechas de cada clase
        List<LocalDate> fechasDeClase = new ArrayList<>();

        // Agregar las fechas de clase a la lista
        for (int i=1; i<=cantClases; i++)
        {
            fechasDeClase.add(fecha);
            fecha = fecha.plusDays(7);
        }

        return fechasDeClase;
    }

    @Override
    public double getMonto() { // calcula el monto recaudado por curso
        return costo * getCantParticipantes() /cantClases;
    }

}
