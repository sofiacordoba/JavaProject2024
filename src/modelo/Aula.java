package modelo;

import java.util.*;

/** Este es mi primer programa
 * Simplemente escribe la frase "iHola, mundo!"
 * @author Sofia Cordoba
 * @version 1. O
 */

public class Aula {
    //Atributos:
    private int ID;
    private int capacidad;
    private List<Reserva> reservas = new LinkedList<>();

    //Métodos:

    //Constructor
    public Aula (int iden, int cap)
    {
        ID=iden;
        capacidad=cap;
    }

    //geters
    public int getID(){ return ID; }
    public int getCapacidad(){ return capacidad; }

    public boolean disponible (Calendar fechaHoraNuevo, int duracionNuevo) //ve si el aula está disponible en determinado día y horario
    {
        Calendar finNuevaReserva = (Calendar) fechaHoraNuevo.clone(); // Calcula el tiempo de finalización de la nueva reserva
        finNuevaReserva.add(Calendar.MINUTE, duracionNuevo);

        for (Reserva reservaAct: reservas)
        {
            Calendar finReserva = (Calendar) reservaAct.getFechaHora().clone(); // Calcula el tiempo de finalización de la reserva actual
            finReserva.add(Calendar.MINUTE, reservaAct.getDuracion());

            // Verifica si la nueva reserva se solapa con la reserva actual
            if (reservaAct.getFechaHora().before(finNuevaReserva) && fechaHoraNuevo.before(finReserva)) // Si hay solapamiento, el aula no está disponible
            {
                return false;
            }
        }
        return true; // Si no hay solapamiento con ninguna reserva, el aula está disponible
    }

    public boolean capaz (int c) //ve si el aula es capaz de tener determinado numero de personas
    {
        return c <= capacidad;
    }

    public void agregarReserva(Reserva reserva) //agrega reserva, no ordenado
    {
        reservas.add(reserva);
    }

    public void cancelarReserva(int codRes) //cancela una reserva con su código dado
    {
        Reserva reservaCancelar = null;
        for (Reserva reservaAct : reservas) //busca la reserva a cancelar
        {
            if(reservaAct.getCodReserva() == codRes)
            {
                reservaCancelar = reservaAct;
                break; //ver si se puede
            }
        }

        if (reservaCancelar != null) //encontró la reserva a cancelar
        {
            reservas.remove(reservaCancelar);
            System.out.println ("Se canceló la reserva " + codRes + " exitosamente."); //NOOOO. try catch
        }
        else //no encontró la reserva
            System.out.println("No se encontró la reserva "+ codRes + ".");
    }

    @Override
    public String toString() //sobreescrito toString
    {
        return "AULA "+ ID +", capacidad máxima: "+ capacidad;
    }



}


