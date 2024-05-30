package Modelo;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        List<Aula> aulas = new ArrayList<>();

        //ejemplo prototipo de llamar a cancelarreservaaula
        cancelarReservaAula(aulas, 123, 456);

    }

    public static void cancelarReservaAula(List<Aula> aulas, int idAula, int codRes) //cancela una reserva en un aula
    {
        Aula aulaCancelar = null;
        for(Aula aulaAct : aulas) //busca el aula con la reserva a cancelar
        {
            if (aulaAct.getID() == idAula)
            {
                aulaCancelar= aulaAct;
                break;
            }
        }

        if (aulaCancelar != null) //encontró el aula con la reserva a cancelar
            aulaCancelar.cancelarReserva(codres);
        else //no encontró el aula
            System.out.println("No se encontró el aula "+ idAula);

    }


}