package Modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Universidad {
    private List<Aula> aulas = new ArrayList<>();

    public void cancelarReservaAula(List<Aula> aulas, int idAula, int codRes) //cancela una reserva en un aula
    {
        Iterator<Aula> iterador = aulas.iterator(); //auxiliar para recorrer lista, es un iterador,no un contenedor
        Aula aulaAct=iterador.next(); //el aula actual, se usa en el while

        while (iterador.hasNext() && aulaAct.getID() < idAula) //mientras que el actual tenga siguiente y el ID del aula actual sea menor al ID del aula que buscamos
        {
            aulaAct=iterador.next(); //va ciclando
        }

        if (aulaAct != null && aulaAct.getID() == idAula) //si lo encuentra, lo cancela
            aulaAct.cancelarReserva(codRes);
        else //no encontró el aula
            System.out.println("No se encontró el aula "+ idAula+ ".");

    }

    public List<Aula> buscarAulasPorPiso(List<Aula> aulas, int piso){ //le paso el piso
        //private int centena =  aulas.stream().filter(x -> x.getID())
        //itero sobre todas las aulas y armo un array con las centenas

        List<Aula> aulasEnPiso = new ArrayList<>();

        for(Aula aula: aulas){
            if(aula.getID() / 100 == piso){
                aulasEnPiso.add(aula);
            }
        }

        return aulasEnPiso;
    }

    public List<Aula> buscarAulasPorCodReserva(List<Reservador> reservadores, String codReservador){
        List<Reservador> listaReservadores = new ArrayList<>();

        for(Reservador reservador: reservadores){
            if(reservador.getCodigo().equals(codReservador)){
                listaReservadores.add(reservador);
            }
        }
        //return listaReservadores; devuelve una lista de aulas reservadas para ese codigo
    }
}
