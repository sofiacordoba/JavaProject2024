package Modelo;

import java.util.Calendar;

public class Evento extends Reservador {
    private Calendar fechaHora; //fecha del evento y hora de inicio
    private int duracion; //minutos que dura el evento
    private int cantMaxParticipantes;

    public Evento(String codigo, String descripcion, int cantmax, Calendar fh, int dur)
    {
        super(codigo, descripcion);
        fechaHora = fh;
        duracion = dur;
        cantMaxParticipantes = cantmax;
    }

    public Calendar getFechaHora(){ return fechaHora; }
    public int getDuracion(){ return duracion; }
    public int getCantMax(){ return cantMaxParticipantes; }
}

