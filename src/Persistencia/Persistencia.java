package Persistencia;

import Modelo.*;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/** La clase Persistencia se usa para serializar y deserializar.
 * @autor Sofia Cordoba, Tatiana Malano Merlino, Josefina Garriz Scarpelli
 */

public class Persistencia {

    public static boolean SerializarUniversidad(Universidad universidad) {
        try (FileOutputStream file = new FileOutputStream("Universidad.dat");
             ObjectOutputStream objectOut = new ObjectOutputStream(file)) {
            objectOut.writeObject(universidad);
        } catch (IOException ex) {
            System.out.println("Se ha presentado un error al serializar la universidad: " + ex.getMessage());
            return false;
        }
        return true;
    }

    public static Universidad DeserializarUniversidad() {
        Universidad uni = null;
        try (FileInputStream file = new FileInputStream("Universidad.dat");
             ObjectInputStream objectIn = new ObjectInputStream(file)) {
            uni = (Universidad) objectIn.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Se ha presentado un error al deserializar la universidad: " + ex.getMessage());
        }
        return uni;
    }

}