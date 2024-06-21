package Excepciones;

/** La clase ExcepcionArchivoInvalido representa una excepción.
 * Se usa cuando hay un error al cargar el archivo XML.
 * @author Sofia Cordoba, Tatiana Malano Merlino, Josefina Garriz Scarpelli
 */
public class ExcepcionArchivoInvalido extends Exception{
    public ExcepcionArchivoInvalido(String mensaje){super("Problema: "+mensaje);}
}
