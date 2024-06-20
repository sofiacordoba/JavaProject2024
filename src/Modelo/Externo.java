package Modelo;

import java.time.LocalDate;
import java.time.LocalTime;

/** La clase Externo representa un evento externo que existe dento de la universidad.
 * Puede realizar reservas de aulas.
 * Es derivado de la clase Evento.
 * @author Sofia Cordoba, Tatiana Malano Merlino, Josefina Garriz Scarpelli
 */

public class Externo extends Evento {
    //Atributos:
    private double cobroAlquiler;
    private String org;

    //Métodos:

    //Constructor
    public Externo (double ca, String o, LocalDate f, LocalTime hi, LocalTime hf, String cod, String desc, int cantpart)
    {
        super(f, hi, hf, cod, desc, cantpart);
        cobroAlquiler=ca;
        org=o;
    }

    //getters
    public double getCobroAl(){ return cobroAlquiler; }
    public String getOrg(){ return org; }
}
