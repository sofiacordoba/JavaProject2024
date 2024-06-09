package Modelo;

import java.util.Calendar;

public class Asignatura extends Reservador {
    private Calendar fechaHoraInicio; //fecha de inicio de clases y a qué hora comienza
    private Calendar fechaHoraFin; //fecha de fin de clases y a qué hora termina
    private String diaSemana; //usar ENUM
    private int cantidadAlumnos ;

    public Asignatura (Calendar fhi, Calendar fhf, String ds, int cantal, String codigo, String descripcion)
    {
        super(codigo, descripcion);
        fechaHoraInicio = fhi;
        fechaHoraFin = fhf;
        diaSemana = ds;
        cantidadAlumnos = cantal ;
    }

    public Calendar getFechaHoraI(){return fechaHoraInicio; }
    public Calendar getFechaHoraF(){return fechaHoraFin; }
    public String getDiaSemana(){ return diaSemana; }
    public int getCantAlumnos(){ return cantidadAlumnos; }

}


