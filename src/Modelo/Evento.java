package Modelo;

import java.time.LocalDate;
import java.time.LocalTime;

/** La clase Evento representa un evento que existe dento de la universidad.
 * Puede realizar reservas de aulas.
 * Puede ser externo y derivado de la subclase Externo, si no es externo, es interno y no se deriva a ninguna subclase.
 * @author Sofia Cordoba, Tatiana Malano Merlino, Josefina Garriz Scarpelli
 */

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

