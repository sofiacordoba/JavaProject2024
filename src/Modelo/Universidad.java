package Modelo;

import Excepciones.ExcepcionCodNoEncontrado;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Universidad implements Serializable {
    private List<Aula> aulas = new ArrayList<>();
    private List<Asignatura> asignaturas = new ArrayList<>();
    private List<Curso> cursos = new ArrayList<>();
    private List<Evento> eventos = new ArrayList<>();

    // Métodos:

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
        Iterator<Aula> iterador = aulas.iterator(); //auxiliar para recorrer lista, es un iterador,no un contenedor
        Aula aulaAct=iterador.next(); //el aula actual, se usa en el while

        while (iterador.hasNext() && aulaAct.getID() < codAula) //mientras que el actual tenga siguiente y el cod del aula actual sea menor al cod del aula que buscamos
        {
            aulaAct=iterador.next(); //va ciclando
        }

        if (aulaAct != null && aulaAct.getID() == codAula) //si lo encuentra, lo cancela
        {
            try
            {
                aulaAct.cancelarReserva(codRes);
            }
            catch (ExcepcionCodNoEncontrado E)
            {
                System.out.println(E.getMessage());
            }
        }
        else //no encontró el aula
            throw new ExcepcionCodNoEncontrado("No se enconró el código del aula " + codAula +".");

    }

}
