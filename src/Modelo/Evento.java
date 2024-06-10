package Modelo;

import java.util.Calendar;

public class Evento extends Reservador {
    //Atributos:
    private Calendar fechaHora; //fecha del evento y hora de inicio
    private int duracion; //minutos que dura el evento
    private int cantMaxParticipantes;

    //Métodos:

    //Constructor
    public Evento (Calendar fh, int dur, int cantmax, String cod, String desc)
    {
        super(cod, desc);
        fechaHora=fh;
        duracion=dur;
        cantMaxParticipantes=cantmax;
    }

    //getters
    public Calendar getFechaHora(){ return fechaHora; }
    public int getDuracion(){ return duracion; }
    public int getCantMax(){ return cantMaxParticipantes; }
}

