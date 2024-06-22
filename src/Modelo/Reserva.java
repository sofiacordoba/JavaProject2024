package Modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/** La clase Reserva representa una reserva que existe dento de un aula.
 * @author Sofia Cordoba, Tatiana Malano Merlino, Josefina Garriz Scarpelli
 */

public class Reserva implements Serializable {
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
    public LocalDate getFecha(){ return fecha; }
    public LocalTime getHoraInicio(){ return horaInicio; }
    public LocalTime getHoraFin(){ return horaFin; }
    public Reservador getReservador(){return reservador;}

    @Override
    public String toString() //sobreescrito toString
    {
        return "    Codigo de reserva: "+ codReserva +"\n        Fecha: "+ fecha.toString()+"\n        Hora de inicio: "+ horaInicio.toString()+"\n        Hora de finalización: "+ horaFin.toString()+"\n        Código de reservador: "+ reservador.getCodigo();
    }
}


