package modelo;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        List<Aula> aulas = new ArrayList<>();

        //ejemplo prototipo de llamar a cancelarreservaaula
        cancelarReservaAula(aulas, 123, 456);

    }

    public static void cancelarReservaAula(List<Aula> aulas, int idAula, int codRes) //cancela una reserva en un aula
    {
       Iterator<Aula>iterador = aulas.iterator(); //auxiliar para recorrer lista, es un iterador,no un contenedor
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
