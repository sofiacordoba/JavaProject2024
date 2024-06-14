package Modelo;

import Excepciones.ExcepcionCodNoEncontrado;

import java.io.Serializable;
import java.util.*;
import java.time.LocalDate;
import java.time.LocalTime;

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
    public int getCantidadReservas() { return reservas.size(); } //reportes

    public boolean disponible (LocalDate fechaNueva, LocalTime horaInicioN, LocalTime horaFinN) //ve si el aula está disponible en determinado día y horario
    {
        Iterator<Reserva> iterador = reservas.iterator(); //auxiliar para recorrer lista, es un iterador,no un contenedor
        boolean disponible= true; //aumimos que el aula está disponible
        Reserva reservaAct; //la reserva actual, se usa en el while

        while (iterador.hasNext() && disponible)
        {
            reservaAct=iterador.next(); //cicla

            if (fechaNueva.isEqual(reservaAct.getFecha()))
            {
                // Verifica si la nueva reserva se solapa con la reserva actual
                if (reservaAct.getHoraInicio().isBefore(horaFinN) && reservaAct.getHoraFin().isAfter(horaInicioN)) // Si hay solapamiento, el aula no está disponible
                    disponible = false;
            }
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

    public void cancelarReserva(int codRes) throws ExcepcionCodNoEncontrado//cancela una reserva con su código dado
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

        if (reservaCancelar != null) // Si reservaCancelar no es nulo, se elimina la reserva de la lista reservas
            reservas.remove(reservaCancelar);
        else
            throw new ExcepcionCodNoEncontrado("No se enconró el código de la reserva " + codRes+".");
    }

    @Override
    public String toString() //sobreescrito toString
    {
        return "AULA "+ ID +", capacidad máxima: "+ capacidad;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }
}


