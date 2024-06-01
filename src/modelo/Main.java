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
       Aula aulaCancelar = null; //auxiliar que guarda aula con reserva para borrar
        Iterator<Aula>iterador = aulas.iterator(); //auxiliar para recorrer lista, es un iterador,no un contenedor
        Aula aulaAct; //el aula actual, se usa en el while

        while (aulaCancelar == null && iterador.hasNext()) //mientras que el actual tenga siguiente y todavía no se encontró el aula, sigue buscando
        {
            aulaAct=iterador.next(); //va ciclando
            if (aulaAct.getID() == idAula) //si lo encuentra, lo asigna para cancelar
                aulaCancelar = aulaAct;
        }
        
        if (aulaCancelar != null) //encontró el aula con la reserva a cancelar
            aulaCancelar.cancelarReserva(codRes);
        else //no encontró el aula
            System.out.println("No se encontró el aula "+ idAula+ ".");

    }


}
