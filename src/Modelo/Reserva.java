package Modelo;

import java.io.Serializable;
import java.util.*;

public class Reserva implements Serializable {
    //Atributos:
    private int codReserva;
    private static int ultimaReserva = 0;
    private Calendar fechaHora;//fecha de la reserva y hora de inicio
    private int duracion; //minutos que dura la reserva
    private Reservador reservador;
    //tipo de reserva?

    //Métodos:

    //Constructor
    public Reserva (Calendar fh, int dur, Reservador res)
    {
        codReserva= ++ultimaReserva;
        fechaHora=fh;
        duracion=dur;
        reservador=res;
    }

    //getters
    public int getCodReserva(){ return codReserva; }
    public int getUltReserva(){ return ultimaReserva; }
    public Calendar getFechaHora(){ return fechaHora; }
    public int getDuracion(){ return duracion; }
}


