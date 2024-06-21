package Modelo;

import Excepciones.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;
import java.time.LocalDate;
import java.time.LocalTime;

/** La clase Aula representa un aula que puede tener varias reservas.
 * Principalmente proporciona métodos para agregar y cancelar reservas.
 * @author Sofia Cordoba, Tatiana Malano Merlino, Josefina Garriz Scarpelli
 */

public class Aula implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

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

    public void agregarReservaAsig (Asignatura asig) throws ExcepcionNoReservar  //agrega una reserva de asignatura
    {
        if (capaz(asig.getCantParticipantes() ))
        {
            List<LocalDate> fechasDeClases = asig.obtenerFechasDeClase();

            Iterator<LocalDate> iterador = fechasDeClases.iterator(); //auxiliar para recorrer lista, es un iterador,no un contenedor
            LocalDate fechaAct; //la fecha actual actual, se usa en el while

            boolean dispo=true;

            while (iterador.hasNext() && dispo) //mientras que el actual tenga siguiente y las fechas analizadas estén disponibles
            {
                fechaAct=iterador.next(); //va ciclando
                dispo= disponible(fechaAct, asig.getHoraInicio(), asig.getHoraFin()); //ve si está disponible
            }

            if (dispo) //si se puede reservar en todos los horarios, reserva la asignatura
            {
                iterador = fechasDeClases.iterator();// Reiniciar el iterador para recorrer la lista desde el comienzo

                while (iterador.hasNext()) {
                    fechaAct = iterador.next();
                    Reserva reserva = new Reserva(fechaAct, asig.getHoraInicio(), asig.getHoraFin(), asig);
                    agregarReserva(reserva);
                }

            }
            else
                throw new ExcepcionNoReservar("No se puede reservar el aula.");

        }
        else
            throw new ExcepcionNoReservar("No se puede reservar el aula.");
    }

    public void agregarReservaCurso(Curso curso, LocalDate fecha, LocalTime horaI, LocalTime horaF) throws ExcepcionNoReservar //agrega una reserva de curso
    {
        if (capaz(curso.getCantParticipantes() ))
        {
            List<LocalDate> fechasDeClases = curso.obtenerFechasDeClase(fecha);

            Iterator<LocalDate> iterador = fechasDeClases.iterator(); //auxiliar para recorrer lista, es un iterador,no un contenedor
            LocalDate fechaAct; //la fecha actual actual, se usa en el while

            boolean dispo=true;

            while (iterador.hasNext() && dispo) //mientras que el actual tenga siguiente y las fechas analizadas estén disponibles
            {
                fechaAct=iterador.next(); //va ciclando
                dispo= disponible(fechaAct, horaI, horaF); //ve si está disponible
            }

            if (dispo) //si se puede reservar en todos los horarios, reserva el curso
            {
                iterador = fechasDeClases.iterator();// Reiniciar el iterador para recorrer la lista desde el comienzo

                while (iterador.hasNext()) {
                    fechaAct = iterador.next();
                    Reserva reserva = new Reserva(fechaAct, horaI, horaF, curso);
                    agregarReserva(reserva);
                }

            }
            else
                throw new ExcepcionNoReservar("No se puede reservar el aula.");

        }
        else
            throw new ExcepcionNoReservar("No se puede reservar el aula.");
    }

    public void agregarReservaEventoI(Evento event) throws ExcepcionNoReservar //agrega una reserva de evento interno
    {
        if (capaz(event.getCantParticipantes() ))
        {
            if (disponible (event.getFecha(), event.getHoraInicio(), event.getHoraFin())) //si se puede reservar en el horario, reserva el evento
            {
                Reserva reserva = new Reserva(event.getFecha(), event.getHoraInicio(), event.getHoraFin(), event);
                agregarReserva(reserva);
            }
            else
                throw new ExcepcionNoReservar("No se puede reservar el aula.");

        }
        else
            throw new ExcepcionNoReservar("No se puede reservar el aula.");
    }

    public void agregarReservaEventoE(Externo externo, String nomOrg, double costo) throws ExcepcionNoReservar //agrega una reserva de evento externo
    {
        if (capaz(externo.getCantParticipantes() ))
        {
            if (disponible (externo.getFecha(), externo.getHoraInicio(), externo.getHoraFin())) //si se puede reservar en el horario, reserva el evento
            {
                externo.setOrg(nomOrg);
                externo.setCobroAl(costo);
                Reserva reserva = new Reserva(externo.getFecha(), externo.getHoraInicio(), externo.getHoraFin(), externo);
                agregarReserva(reserva);
            }
            else
                throw new ExcepcionNoReservar("No se puede reservar el aula.");

        }
        else
            throw new ExcepcionNoReservar("No se puede reservar el aula.");
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

    public double calcularRecaudacion() { // Método para calcular la recaudación total de un aula
        double recaudacion = 0;
        for (Reserva reserva : reservas) {
            recaudacion += reserva.getReservador().getMonto();
        }
        return recaudacion;
    }


    @Override
    public String toString() //sobreescrito toString
    {
        return "ID de aula: "+ ID +"\n Capacidad máxima: "+ capacidad + "\n Reservas: \n" + mostrarReservas();
    }

    public String mostrarReservas()
    {
        StringBuilder sb = new StringBuilder();
        for (Reserva resAct : reservas) {
            sb.append(resAct.toString()).append("\n");
        }
        return sb.toString();
    }

    public List<Reserva> getReservas() {
        return reservas;
    }
}


