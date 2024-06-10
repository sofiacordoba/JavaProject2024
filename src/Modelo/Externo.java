package modelo;

import java.time.LocalDate;
import java.time.LocalTime;

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
