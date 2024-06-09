package Modelo;

import java.util.*;

public class Universidad {
    private List<Aula> aulas;

    public Universidad() {
        this.aulas = new ArrayList<>();
    }

    public List<Aula> getAulas() {
        return aulas;
    }

    public void agregarAula(Aula aula) {
        this.aulas.add(aula);
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
}
