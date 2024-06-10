package modelo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Evento extends Reservador {
    //Atributos:
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    //Métodos:

    //Constructor
    public Evento (LocalDate f, LocalTime hi, LocalTime hf, String cod, String desc, int cantpart)
    {
        super(cod, desc, cantpart);
        fecha=f;
        horaInicio=hi;
        horaFin=hf;
    }

    //getters
    public LocalDate getFecha(){ return fecha; }
    public LocalTime getHoraInicio(){ return horaInicio; }
    public LocalTime getHoraFin(){ return horaFin; }
}

