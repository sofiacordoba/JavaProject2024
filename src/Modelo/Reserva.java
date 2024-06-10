package modelo;

import java.time.LocalDate;
import java.time.LocalTime;


public class Reserva {
   //Atributos:
    private int codReserva;
    private static int ultimaReserva = 0;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Reservador reservador;

    //Métodos:

    //Constructor
    public Reserva (LocalDate f, LocalTime hi, LocalTime hf, Reservador res)
    {
        codReserva= ++ultimaReserva;
        fecha=f;
        horaInicio=hi;
        horaFin=hf;
        reservador=res;
    }

    //getters
    public int getCodReserva(){ return codReserva; }
    public int getUltReserva(){ return ultimaReserva; }
    public LocalDate getFecha(){ return fecha; }
    public LocalTime getHoraInicio(){ return horaInicio; }
    public LocalTime getHoraFin(){ return horaFin; }
}


