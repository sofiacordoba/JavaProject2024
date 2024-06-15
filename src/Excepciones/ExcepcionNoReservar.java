package Excepciones;

public class ExcepcionNoReservar extends Exception{
    public ExcepcionNoReservar(String mensaje)
    {
        super("Problema: "+mensaje);
    }
}