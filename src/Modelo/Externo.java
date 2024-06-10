package Modelo;

import java.util.Calendar;

public class Externo extends Evento {
    //Atributos:
    private double cobroAlquiler;
    private String org;

    //Métodos:

    //Constructor
    public Externo (double ca, String o, Calendar fh, int dur, int cantmax, String cod, String desc)
    {
        super(fh, dur, cantmax, cod, desc);
        cobroAlquiler=ca;
        org=o;
    }

    //getters
    public double getCobroAl(){ return cobroAlquiler; }
    public String getOrg(){ return org; }
}
