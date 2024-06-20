package Modelo;

import Excepciones.*;

import java.io.Serializable;
import java.util.*;
import java.time.LocalDate;
import java.time.LocalTime;

/** La clase Universidad representa a la universidad del sistema.
 * Principalmente proporciona métodos para buscar aulas, agregar y cancelar reservas.
 * @author Sofia Cordoba, Tatiana Malano Merlino, Josefina Garriz Scarpelli
 */

public class Universidad implements Serializable {
    private List<Aula> aulas = new ArrayList<>();
    private List<Asignatura> asignaturas = new ArrayList<>();
    private List<Curso> cursos = new ArrayList<>();
    private List<Evento> eventos = new ArrayList<>();

    // Métodos:

    public Aula buscarAula(int codAula)
    {
        Iterator<Aula> iterador = aulas.iterator(); //auxiliar para recorrer lista, es un iterador,no un contenedor
        Aula aulaAct=iterador.next(); //el aula actual, se usa en el while

        while (iterador.hasNext() && aulaAct.getID() < codAula) //mientras que el actual tenga siguiente y el cod del aula actual sea menor al cod del aula que buscamos
        {
            aulaAct=iterador.next(); //va ciclando
        }
        return aulaAct;
    }

    public void agregarAula(Aula aula) { //reportes
        aulas.add(aula);
    }
    public List<Aula> getAulas() { //reportes
        return aulas;
    }

    public List<Aula> buscarAulasPorNumeroDePiso(int piso){ //itero sobre todas las aulas y armo un array con las centenas
        List<Aula> aulasFiltradas = new ArrayList<>();
        for(Aula aula: aulas){
            if(aula.getID() / 100 == piso){
                aulasFiltradas.add(aula);
            }
        }
        return aulasFiltradas;
    }

    public List<Aula> buscarAulasPorReservador(Reservador reservador){
        List<Aula> aulasFiltradas = new ArrayList<>();
        String codigoEntidad = reservador.getCodigo();
        for(Aula aula: aulas){
            for(Reserva reserva: aula.getReservas()){
                if(reserva.getReservador().getCodigo().equals(codigoEntidad)){
                    aulasFiltradas.add(aula);
                    break;
                }
            }
        }
        return aulasFiltradas; //devuelve una lista de aulas reservadas para ese codigo
    }

    public void cancelarReservaAula(int codAula, int codRes) throws ExcepcionCodNoEncontrado //cancela una reserva en un aula
    {
        Aula aulaAct=buscarAula(codAula);

        if (aulaAct != null && aulaAct.getID() == codAula) //si lo encuentra, lo cancela
        {
            try
            {
                aulaAct.cancelarReserva(codRes);
            }
            catch (ExcepcionCodNoEncontrado E)
            {
                throw new ExcepcionCodNoEncontrado("No se enconró el código de la reserva " + codRes+".");
            }
        }
        else //no encontró el aula
            throw new ExcepcionCodNoEncontrado("No se enconró el código del aula " + codAula +".");

    }

    public void agregarReservaAsigAula(int codAula, String codReservador) throws ExcepcionCodNoEncontrado, ExcepcionNoReservar //agrega una reserva de asignatura en un aula
    {
        Iterator<Asignatura> iteradorAsig = asignaturas.iterator(); //auxiliar para recorrer lista, es un iterador,no un contenedor
        Asignatura asigAct=iteradorAsig.next(); //la asignatura actual actual, se usa en el while

        while (iteradorAsig.hasNext() && !asigAct.getCodigo().equals(codReservador)) //mientras que el actual tenga siguiente y el cod de la asignatura actual no sea igual al cod del asignatura que buscamos
        {
            asigAct=iteradorAsig.next(); //va ciclando
        }

        if (asigAct != null && asigAct.getCodigo().equals(codReservador)) //si la encuentra, trata de ver si se puede reservar
        {
            Aula aulaAct=buscarAula(codAula);

            if (aulaAct != null && aulaAct.getID() == codAula) //si lo encuentra, ve si puede reservar
            {
                try
                {
                    aulaAct.agregarReservaAsig(asigAct);
                }
                catch (ExcepcionNoReservar E)
                {
                    throw new ExcepcionNoReservar("No se puede reservar el aula.");
                }
            }
            else //no encontró el aula
                throw new ExcepcionCodNoEncontrado("No se enconró el código del aula " + codAula +".");

        }
        else //no encontró la asignatura
            throw new ExcepcionCodNoEncontrado("No se enconró el código de la asignatura " + codReservador +".");
    }

    public void agregarReservaCursoAula(int codAula, String codReservador, LocalDate fecha, LocalTime horaI, LocalTime horaF) throws ExcepcionCodNoEncontrado, ExcepcionNoReservar //agrega una reserva de curso en un aula
    {
        Iterator<Curso> iteradorCurso = cursos.iterator(); //auxiliar para recorrer lista, es un iterador,no un contenedor
        Curso cursoAct=iteradorCurso.next(); //lel curso actual, se usa en el while

        while (iteradorCurso.hasNext() && !cursoAct.getCodigo().equals(codReservador)) //mientras que el actual tenga siguiente y el cod del curso actual no sea igual al cod del curso que buscamos
        {
            cursoAct=iteradorCurso.next(); //va ciclando
        }

        if (cursoAct != null && cursoAct.getCodigo().equals(codReservador)) //si la encuentra, trata de ver si se puede reservar
        {
            Aula aulaAct=buscarAula(codAula);

            if (aulaAct != null && aulaAct.getID() == codAula) //si lo encuentra, ve si puede reservar
            {
                try
                {
                    aulaAct.agregarReservaCurso(cursoAct, fecha, horaI, horaF);
                }
                catch (ExcepcionNoReservar E)
                {
                    throw new ExcepcionNoReservar("No se puede reservar el aula.");
                }
            }
            else //no encontró el aula
                throw new ExcepcionCodNoEncontrado("No se enconró el código del aula " + codAula +".");

        }
        else //no encontró el curso
            throw new ExcepcionCodNoEncontrado("No se enconró el código del curso " + codReservador +".");
    }

    public void agregarReservaEventoIAula(int codAula, String codReservador) throws ExcepcionCodNoEncontrado, ExcepcionNoReservar //agrega una reserva de un evento interno en un aula
    {
        Iterator<Evento> iteradorEvent = eventos.iterator(); //auxiliar para recorrer lista, es un iterador,no un contenedor
        Evento eventAct=iteradorEvent.next(); //el evento actual, se usa en el while

        while (iteradorEvent.hasNext() && !eventAct.getCodigo().equals(codReservador)) //mientras que el actual tenga siguiente y el cod del evento actual no sea igual al cod del curso que buscamos
        {
            eventAct=iteradorEvent.next(); //va ciclando
        }

        if (eventAct != null && eventAct.getCodigo().equals(codReservador)) //si la encuentra, trata de ver si se puede reservar
        {
            Aula aulaAct=buscarAula(codAula);

            if (aulaAct != null && aulaAct.getID() == codAula) //si lo encuentra, ve si puede reservar
            {
                if (eventAct instanceof Externo) // Verifica si el evento es una instancia de Evento
                    throw new ExcepcionCodNoEncontrado("Ingresó un evento externo.");
                else
                {
                try
                {
                    aulaAct.agregarReservaEventoI(eventAct);
                }
                catch (ExcepcionNoReservar E)
                {
                    throw new ExcepcionNoReservar("No se puede reservar el aula.");
                }
                }
            }
            else //no encontró el aula
                throw new ExcepcionCodNoEncontrado("No se enconró el código del aula " + codAula +".");

        }
        else //no encontró el evento
            throw new ExcepcionCodNoEncontrado("No se enconró el código del evento " + codReservador +".");
    }

    public void agregarReservaEventoEAula(int codAula, String codReservador, String nomOrg, double costo) throws ExcepcionCodNoEncontrado, ExcepcionNoReservar //agrega una reserva de un evento externo en un aula
    {
        Iterator<Evento> iteradorEvent = eventos.iterator(); //auxiliar para recorrer lista, es un iterador,no un contenedor
        Evento eventAct=iteradorEvent.next(); //el evento actual, se usa en el while

        while (iteradorEvent.hasNext() && !eventAct.getCodigo().equals(codReservador)) //mientras que el actual tenga siguiente y el cod del evento actual no sea igual al cod del curso que buscamos
        {
            eventAct=iteradorEvent.next(); //va ciclando
        }

        if (eventAct != null && eventAct.getCodigo().equals(codReservador)) //si la encuentra, trata de ver si se puede reservar
        {
            Aula aulaAct=buscarAula(codAula);

            if (aulaAct != null && aulaAct.getID() == codAula) //si lo encuentra, ve si puede reservar
            {
                if (eventAct instanceof Externo) // Verifica si el evento es una instancia de Externo
                {
                    try {
                        Externo externo = (Externo) eventAct;
                        aulaAct.agregarReservaEventoE(externo, nomOrg, costo);
                    } catch (ExcepcionNoReservar E) {
                        throw new ExcepcionNoReservar("No se puede reservar el aula.");
                    }
                }
                else
                    throw new ExcepcionCodNoEncontrado("Ingresó un evento interno.");
            }
            else //no encontró el aula
                throw new ExcepcionCodNoEncontrado("No se enconró el código del aula " + codAula +".");

        }
        else //no encontró el evento
            throw new ExcepcionCodNoEncontrado("No se enconró el código del evento " + codReservador +".");
    }


    public void agregarAsignatura(Asignatura asig) {
        asignaturas.add(asig);
    }
    public void agregarCurso(Curso curso) {
        cursos.add(curso);
    }
    public void agregarEvento(Evento evento) {
        eventos.add(evento);
    }
    public void agregarExterno(Externo externo) {
        eventos.add(externo);
    }


}
