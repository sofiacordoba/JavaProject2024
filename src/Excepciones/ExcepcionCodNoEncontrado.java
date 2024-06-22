package Excepciones;

/** La clase ExcepcionCodNoEncontrado representa una excepción.
 * Se usa cuando no se encuentra un código de aula, de asignatura, un piso, etc.
 * @author Sofia Cordoba, Tatiana Malano Merlino, Josefina Garriz Scarpelli
 */
public class ExcepcionCodNoEncontrado extends Exception{
    public ExcepcionCodNoEncontrado(String mensaje)
    {
        super("Problema: "+mensaje);
    }
}
