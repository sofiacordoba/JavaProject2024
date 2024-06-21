package Persistencia;

import Modelo.*;
import Excepciones.ExcepcionArchivoInvalido;
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

/** La clase Persistencia se usa para cargar los datos del sistema.
 * @autor Sofia Cordoba, Tatiana Malano Merlino, Josefina Garriz Scarpelli
 */

public class Persistencia {

    public static boolean SerializarUniversidad(Universidad universidad) {
        try {
            FileOutputStream file = new FileOutputStream("Universidad.dat");
            ObjectOutputStream objectOut = new ObjectOutputStream(file);
            objectOut.writeObject(universidad);
            objectOut.close();
            file.close();
        } catch (Exception ex) {
            System.out.println("Se ha presentado un error al serializar la universidad");
            return false;
        }
        return true;
    }

    public static Universidad DeserializarUniversidad() {
        Universidad uni = null;
        try {
            FileInputStream file = new FileInputStream("Universidad.dat");
            ObjectInputStream objectIn = new ObjectInputStream(file);
            uni = (Universidad) objectIn.readObject();
            objectIn.close();
            file.close();
        } catch (Exception ex) {
            System.out.println("Se ha presentado un error al deserializar la universidad");
            return null;
        }
        return uni;
    }

    public static void main(String[] args) {
        // Crear una instancia de Universidad
        Universidad universidad = new Universidad();

        // Leer datos desde el archivo XML
        LectorXML lectorXML = new LectorXML();
        try {
            lectorXML.cargarDatos("C:\\Users\\Usuario\\IdeaProjects\\JavaProject2024\\src\\Persistencia\\datos_universidad.xml", universidad);
            System.out.println("Datos XML cargados correctamente.");

            // Serializar la universidad en el archivo de persistencia
            boolean result = SerializarUniversidad(universidad);
            if (result) {
                System.out.println("Datos serializados correctamente.");
            }
        } catch (ExcepcionArchivoInvalido e) {
            e.printStackTrace();
        }

        // Deserializar la universidad para verificar
        Universidad uni2 = DeserializarUniversidad();
        if (uni2 != null) {
            System.out.println("Datos deserializados correctamente.");
            // System.out.println(uni2.ToString());
        }
    }
}