package modelo;

import java.util.Calendar;

public class Asignatura extends Reservador {
    private Calendar fechaHoraInicio; //inicio del cuatrimestre y hora de clase
    private Calendar fechaHoraFin; // fin del cuatrimestre y hora de clase
    private String diaSemana;
    private int cantAlumnos;
}
