package Modelo;

import java.util.*;
import java.time.LocalDate;
import java.time.LocalTime;

/** La clase Asignatura representa una asignatura que existe dento de la universidad.
 * Puede realizar reservas de aulas.
 * @author Sofia Cordoba, Tatiana Malano Merlino, Josefina Garriz Scarpelli
 */

public class Asignatura extends Reservador {
    //Atributos:

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String diaSemana;


    //Métodos:

    //Constructor
    public Asignatura (LocalDate fi, LocalDate ff, LocalTime hi, LocalTime hf, String ds, String cod, String desc, int cantpart)
    {
        super(cod, desc, cantpart);
        fechaInicio=fi;
        fechaFin=ff;
        horaInicio=hi;
        horaFin=hf;
        diaSemana=ds;
    }

    //getters
    public LocalDate getFechaInicio(){ return fechaInicio; }
    public LocalDate getFechaFin(){ return fechaFin; }
    public LocalTime getHoraInicio(){ return horaInicio; }
    public LocalTime getHoraFin(){ return horaFin; }
    public String getDiaSemana(){ return diaSemana; }

    public List<LocalDate> obtenerFechasDeClase() {  //lista de las fechas de cada clase
        List<LocalDate> fechasDeClase = new ArrayList<>();
        LocalDate fecha = fechaInicio;

        // Agregar las fechas de clase a la lista
        while (!fecha.isAfter(fechaFin)) {
            fechasDeClase.add(fecha);
            fecha = fecha.plusDays(7);
        }
        return fechasDeClase;
    }

    @Override
    public double getMonto() { // calcula el monto recaudado por asignatura
        return 0;
    }

}


