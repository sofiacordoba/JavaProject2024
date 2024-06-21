package Excepciones;

/** La clase ExcepcionNoReservar representa una excepción.
 * Se usa cuando no se puede reservar un aula, ya sea porque no tiene capacidad suficiente o está ocupada en un horario.
 * @author Sofia Cordoba, Tatiana Malano Merlino, Josefina Garriz Scarpelli
 */
public class ExcepcionNoReservar extends Exception{
    public ExcepcionNoReservar(String mensaje)
    {
        super("Problema: "+mensaje);
    }
}