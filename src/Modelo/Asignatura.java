package Modelo;

import java.util.Calendar;

public class Asignatura extends Reservador {
    //Atributos:
    private Calendar fechaHoraInicio; //fecha de inicio de clases y a qué hora comienza
    private Calendar fechaHoraFin; //fecha de fin de clases y a qué hora termina
    private String diaSemana;
    private int cantAlumnos;


    //Métodos:

    //Constructor
    public Asignatura (Calendar fhi, Calendar fhf, String ds, int cantal, String cod, String desc)
    {
        super(cod, desc);
        fechaHoraInicio=fhi;
        fechaHoraFin=fhf;
        diaSemana=ds;
        cantAlumnos=cantal;
    }

    //getters
    public Calendar getFechaHoraI(){return fechaHoraInicio; }
    public Calendar getFechaHoraF(){return fechaHoraFin; }
    public String getDiaSemana(){ return diaSemana; }
    public int getCantAl(){ return cantAlumnos; }

}


