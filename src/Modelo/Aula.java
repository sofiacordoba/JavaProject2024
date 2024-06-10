package Modelo;

import java.io.Serializable;
import java.util.*;

/** Este es mi primer programa
 * Simplemente escribe la frase "iHola, mundo!"
 * @author Sofia Cordoba
 * @version 1. O
 */

public class Aula implements Serializable {
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

    //getters
    public int getID(){ return ID; }
    public int getCapacidad(){ return capacidad; }

    public boolean disponible (Calendar fechaHoraNuevo, int duracionNuevo) //ve si el aula está disponible en determinado día y horario
    {
        Calendar finNuevaReserva = (Calendar) fechaHoraNuevo.clone(); // Calcula el tiempo de finalización de la nueva reserva
        finNuevaReserva.add(Calendar.MINUTE, duracionNuevo);

        Iterator<Reserva> iterador = reservas.iterator(); //auxiliar para recorrer lista, es un iterador,no un contenedor
        boolean disponible= true; //aumimos que el aula está disponible
        Reserva reservaAct; //la reserva actual, se usa en el while

        while (iterador.hasNext() && disponible)
        {
            reservaAct=iterador.next();
            Calendar finReserva = (Calendar) reservaAct.getFechaHora().clone(); // Calcula el tiempo de finalización de la reserva actual
            finReserva.add(Calendar.MINUTE, reservaAct.getDuracion());

            // Verifica si la nueva reserva se solapa con la reserva actual
            if (reservaAct.getFechaHora().before(finNuevaReserva) && fechaHoraNuevo.before(finReserva)) // Si hay solapamiento, el aula no está disponible
                disponible = false;
        }

        return disponible;
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
        Reserva reservaCancelar = null;    //auxiliar que guarda reserva para borrar
        Iterator<Reserva> iterador = reservas.iterator();  //auxiliar para recorrer lista, es un iterador,no un contenedor
        Reserva reservaAct; //la reserva actual, se usa en el while

        while (iterador.hasNext() && reservaCancelar == null) //mientras que el actual tenga siguiente y todavía no se encontró la reserva, sigue buscando
        {
            reservaAct=iterador.next();  //va ciclando
            if (reservaAct.getCodReserva() == codRes) //si lo encuentra, lo asigna para cancelar
            {
                reservaCancelar = reservaAct;
            }
        }

        try {
            if (reservaCancelar != null) {    // Si reservaCancelar no es nulo, se elimina la reserva de la lista reservas
                reservas.remove(reservaCancelar);
                System.out.println("Se canceló la reserva " + codRes + " exitosamente.");
            } else {         // Si reservaCancelar es nulo, se lanza una nueva excepción
                throw new Exception("No se encontró la reserva " + codRes + ".");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

/*
        if (reservaCancelar != null) {
            try {
                reservas.remove(reservaCancelar);
            } catch (Excepcion e) {
                System.out.println("Error al cancelar reserva: " + e.getMessage());
            }
        } else {
            System.out.println("No se encontró la reserva " + codRes +".");
        }*/
    }

    @Override
    public String toString() //sobreescrito toString
    {
        return "AULA "+ ID +", capacidad máxima: "+ capacidad;
    }



}


