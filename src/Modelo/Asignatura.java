package modelo;

import java.time.LocalDate;
import java.time.LocalTime;

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

}


