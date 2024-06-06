package modelo;

import java.util.*;
import Excepciones.*;

public class Main {
    public static void main(String[] args) {

        List<Aula> aulas = new ArrayList<>();

        //Ejemplo para insertar aula y luego filtrar por piso
        aulas.add(new Aula(301, 100));
        aulas.add(new Aula(306, 100));
        aulas.add(new Aula(201, 100));
        int piso = 3;
        List<Aula> aulasEnPiso = buscarAulasPorPiso(aulas, piso);
        for (Aula aula : aulasEnPiso) {
            System.out.println(aula.getID());
            //falta un muestra para todas sus reservas
        }

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
            throw new ExcepcionCodNoEncontrado("No se enconró el código del aula " + codRes+".");

    }

    public static List<Aula> buscarAulasPorPiso(List<Aula> aulas, int piso){ //le paso el piso
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


}
