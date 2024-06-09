package Modelo;

import java.util.Calendar;

public class Externo extends Evento {
    private double costoAlquiler;
    private String organizacion;

    public Externo(String codigo, String descripcion, int maxParticipantes, Calendar fechaHora, int duracion, String org, double costoAl)
    {
        super(codigo, descripcion, maxParticipantes, fechaHora, duracion);
        costoAlquiler = costoAl;
        organizacion = org;
    }

    public double getCostoAlquiler(){ return costoAlquiler; }
    public String getOrganizacion(){ return organizacion; }
}
