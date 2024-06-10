package Excepciones;

public class ExcepcionCodNoEncontrado extends Exception{
    public ExcepcionCodNoEncontrado(String mensaje)
    {
        super("Problema: "+mensaje);
    }
}
